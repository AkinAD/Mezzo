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

import bean.AccountBean;
import bean.ProfileBean;

/**
 * @author Akin Adewale
 *
 */

public class UserDAO {
	private DataSource ds;
	/**
	 * Clas constructor, set up initial database connection
	 * @throws ClassNotFoundException
	 */
	public UserDAO() throws ClassNotFoundException {
		try {
			//ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/New_Derby");
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413"); // this is for local testing 

			//ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Add new user Entry to Database based on provided AccountBean
	 * @param registerBean
	 * @return String indicating success or failure 
	 * @throws SQLException
	 */

	public String registerUser(AccountBean registerBean) throws SQLException {
		String firstName = registerBean.getFname();
		String lastName = registerBean.getLname();
		String email = registerBean.getEmail();
		String username = registerBean.getUserName();
		String password = registerBean.getPassword();

		try {
			String query = "insert into Account (fname, lname, username, email, password) values (?,?,?,?,?)"; // Insert
			// user
			// details
			// into
			// the
			// table
			// 'USERS'
			Connection con = this.ds.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(query); // Making use of prepared statements here
			// to insert bunch of data
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, username);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, password);

			int i = preparedStatement.executeUpdate();

			if (i != 0) // Just to ensure data has been inserted into the database
			{
				con.close();
				preparedStatement.close();
				if (createCustomer(username) && createProfile(username)) {
					return "SUCCESS";
				} else {
					return "Failed to create CUSTOMER and/or Profile";
				}
			}
			con.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("UserDAO: DB Failure On account creation ");
		}

		return "Oops.. Something went wrong there..!"; // On failure, send a message from here.
	}

	/**
	 *  After Account entry has been made, create entry in Customer table based on existing user in Account  table
	 * @param username
	 * @return true or false  Based on success or failure of the method
	 * @throws SQLException
	 */
	private boolean createCustomer(String username) throws SQLException {
		Connection con = this.ds.getConnection();
		String query = "INSERT INTO Customer (username, password) select account.username, account.password "
				+ "FROM account WHERE account.username = ?";
		PreparedStatement preparedStatement = con.prepareStatement(query); // Making use of prepared statements here to
		// insert bunch of data
		preparedStatement.setString(1, username);

		int i = preparedStatement.executeUpdate();

		if (i != 0) // Just to ensure data has been inserted into the database
		{
			con.close();
			preparedStatement.close();
			return true;
		}
		con.close();
		preparedStatement.close();
		return false;
	}
	
	/**
	 * Retrieve user password for logging in
	 * @param username
	 * @return password string for validation
	 * @throws Exception
	 */
	
	public String getUserPass(String username) throws Exception {
		Connection con = this.ds.getConnection();
		String query = "SELECT password FROM account WHERE username = ?";
		PreparedStatement prepState = con.prepareStatement(query);
		prepState.setString(1, username);

		ResultSet res = prepState.executeQuery();
		if (res.next()) {
			String pass = res.getString("PASSWORD");
			System.out.println("UserDAO: "+pass);
			res.close();
			con.close();
			prepState.close();
			return pass;
		}
		res.close();
		con.close();
		prepState.close();
		throw new Exception("Password not found");
	}
	/**
	 *  Create Profile entry in Database after successful account creation
	 * @param username
	 * @return true or false  Based on success or failure of the method
	 * @throws SQLException
	 */
	private boolean createProfile(String username) throws SQLException {
		Connection con = this.ds.getConnection();
		String query = "INSERT INTO Profile (username, fname, lname, email, privilege) select account.username, account.fname, account.lname, account.email, 'customer'"
				+ " FROM account WHERE account.username = ?"; // Insert user details into the table 'Profile' from
		// account
		PreparedStatement preparedStatement = con.prepareStatement(query); // Making use of prepared statements here to
		// insert bunch of data
		preparedStatement.setString(1, username);
		int i = preparedStatement.executeUpdate();

		if (i != 0) // Just to ensure data has been inserted into the database
		{
			con.close();
			preparedStatement.close();
			return true;
		}
		con.close();
		preparedStatement.close();
		return false;
	}

	/**
	 * Prevent users from registering with an existing email address
	 * @param email
	 * @return true if email is unique false otherwise
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 */
	public boolean uniqueEmail(String email) throws SQLException, IllegalArgumentException {
		Connection con = this.ds.getConnection();
		String query = "SELECT email FROM account WHERE email = ?";
		PreparedStatement prepState = con.prepareStatement(query);
		prepState.setString(1, email);

		ResultSet res = prepState.executeQuery();
		if (res.next()) // if any result is returned, return false( not unique)
		{
			System.out.println("UserDAO: EMAIL NOT UNNIQUE!!!!");
			res.close();
			con.close();
			prepState.close();
			return false;
		}
		res.close();
		con.close();
		prepState.close();
		return true;
	}
	
	/**
	 * Prevent users from registering with an existing username
	 * @param userName
	 * @return true if username is unique false otherwise
	 * @throws SQLException
	 */
	public boolean uniqueUserName(String userName) throws SQLException {
		Connection con = this.ds.getConnection();
		String query = "SELECT username FROM account WHERE username = ?";
		PreparedStatement prepState = con.prepareStatement(query);
		prepState.setString(1, userName);

		ResultSet res = prepState.executeQuery();
		if (res.next()) // if any result is returned, return false( not unique)
		{
			System.out.println("UserDAO: USERNAME NOT UNNIQUE!!!!");
			res.close();
			con.close();
			prepState.close();
			return false;
		}
		res.close();
		con.close();
		prepState.close();
		return true;
	}
	
	/**
	 * Retrieve User Data from provided username
	 * @param username
	 * @return Map with key FULLNAME and value ProfileBean 
	 * @throws SQLException
	 */
	public Map<String, ProfileBean> retrieveByUsername(String username) throws SQLException {
		String query = "select * from Profile where username='" + username + "'";
		Map<String, ProfileBean> rv = new HashMap<String, ProfileBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("fname") + ", " + r.getString("lname");
			rv.put(name, new ProfileBean(r.getString("fname"), r.getString("lname"), r.getString("username"),
					r.getString("email"), r.getString("privilege")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	/**
	 * Retrieve User Data from provided email
	 * @param email
	 * @return Map with key FULLNAME and value ProfileBean 
	 * @throws SQLException
	 */
	
	public Map<String, ProfileBean> retrieveByEmail(String email) throws SQLException {
		String query = "select * from Profile where email='" + email + "'";
		Map<String, ProfileBean> rv = new HashMap<String, ProfileBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String name = r.getString("fname") + ", " + r.getString("lname");
			rv.put(name, new ProfileBean(r.getString("fname"), r.getString("lname"), r.getString("username"),
					r.getString("email"), r.getString("privilege")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	/**
	 * Assign new Priviledge to existing User
	 * @param username
	 * @param role
	 * @return true if successful, false otherwise
	 * @throws SQLException
	 */
	public boolean updatePrivilege(String username, String role) throws SQLException{
		String query = "update Profile SET privilege=? where username=?";
		Map<String, ProfileBean> rv = new HashMap<String, ProfileBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, role);
		p.setString(2, username);

		int i = p.executeUpdate();

		if (i != 0) // SUCCESS: just to ensure data has been inserted into the database
		{
			con.close();
			p.close();
			return true; //SUCCESS
		}
		con.close();
		p.close();
		return false;
	}

}
