package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AddressBean;

public class AddressDAO {

	private DataSource ds;

	public AddressDAO() throws ClassNotFoundException {
		try {
			 ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
//			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the billing address of this customer Customers must have a billing
	 * address.
	 * 
	 * @param username
	 * @return the only billing address of this customer
	 * @throws SQLException
	 */
	public AddressBean retrieveBillingAddr(String username) throws SQLException {
		String query = "SELECT * from ADDRESS where USERNAME = '" + username + "' AND ADDRTYPE = 'Billing'";
		// only one billing address for one user
		AddressBean address = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next()) {
			String addrusername = r.getString("USERNAME");
			String street = r.getString("STREET");
			String province = r.getString("PROVINCE");
			String country = r.getString("COUNTRY");
			String zip = r.getString("ZIP");
			String phone = r.getString("PHONE");
			String type = r.getString("ADDRTYPE");
			address = new AddressBean(addrusername, street, province, country, zip, phone, type);
		}
		r.close();
		p.close();
		con.close();
		return address;
	}

	/**
	 * Return the addr's unique int id, if address not exist, return 0
	 * 
	 * @param addr
	 * @return unique int id
	 * @throws SQLException
	 */
	public int retrieveAddrByBean(AddressBean addr) throws SQLException {
		String query = "SELECT A_ID FROM Address WHERE USERNAME = '" + addr.getUsername() + "' AND STREET = '"
				+ addr.getStreet() + "' AND PROVINCE = '" + addr.getProvince() + "' AND COUNTRY = '" + addr.getCountry()
				+ "' AND ZIP = '" + addr.getZip() + "' AND PHONE = '" + addr.getPhone() + "' AND \"ADDRTYPE\"='"
				+ addr.getType() + "'";
		int id = 0;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		if (r.next())
			id = r.getInt("A_ID");
		r.close();
		p.close();
		con.close();
		return id;
	}

	/**
	 * Update a new customer' address
	 * 
	 * @param addr
	 * @throws SQLException
	 */
	public int updateAddr(AddressBean addr) throws SQLException {
		int id = this.retrieveAddrByBean(addr);
		if (id == 0) {// addr not exist
			if (addr.getType().equals("Billing")) {
				// if addr is billing address, one billing for one user
				String query;
				if (retrieveBillingAddr(addr.getUsername()) == null) {// first enter billing
					query = "INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('"
							+ addr.getUsername() + "', '" + addr.getStreet() + "', '" + addr.getProvince() + "', '"
							+ addr.getCountry() + "', '" + addr.getZip() + "', '" + addr.getPhone() + "', '"
							+ addr.getType() + "')";
				} else {// update exist billing
					int a_id = retrieveAddrByBean(retrieveBillingAddr(addr.getUsername()));
					query = "UPDATE Address SET \"STREET\"='" + addr.getStreet() + "', \"PROVINCE\"='"
							+ addr.getProvince() + "', \"COUNTRY\"='" + addr.getCountry() + "', \"ZIP\"='"
							+ addr.getZip() + "', \"PHONE\"='" + addr.getPhone() + "' WHERE A_ID=" + a_id;
				}
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				p.executeUpdate();
				p.close();
				con.close();
			} else {// update shipping

				String query = "INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('"
						+ addr.getUsername() + "', '" + addr.getStreet() + "', '" + addr.getProvince() + "', '"
						+ addr.getCountry() + "', '" + addr.getZip() + "', '" + addr.getPhone() + "', '"
						+ addr.getType() + "')";
				Connection con = this.ds.getConnection();
				PreparedStatement p = con.prepareStatement(query);
				p.executeUpdate();
				p.close();
				con.close();
			}
			return retrieveAddrByBean(addr);
		} else
			return id;
	}

	/**
	 * @return user names and the billing ZIP address associated with the user name
	 * @throws SQLException
	 */
	public Map<String, String> retrieveUserNameZip() throws SQLException {
		Map<String, String> usernameZip = new HashMap<String, String>();
		String query = "SELECT * from ADDRESS where ADDRTYPE = 'Billing'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String userName = r.getString("USERNAME");
			String zip = r.getString("ZIP");
			usernameZip.put(userName, zip);
		}
		r.close();
		p.close();
		con.close();
		return usernameZip;
	}

}
