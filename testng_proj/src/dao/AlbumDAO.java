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

import bean.Album;

public class AlbumDAO {

	private DataSource ds;

	/**
	 * Initializes the albul data access object for data retrieval
	 * 
	 * @throws ClassNotFoundException
	 */
	public AlbumDAO() throws ClassNotFoundException {
		try {
			// ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/New_Derby");
//			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/Db2-4413"); // USE THIS TO DEBUG LOCALLY
			ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the album id and the album object
	 * 
	 * @param aid
	 * @return the album id as String and the album object
	 * @throws SQLException
	 */
	public Map<String, Album> retrieveAll(int aid) throws SQLException {
		String query = "select * from album where aid=" + aid;
		Map<String, Album> rv = new HashMap<String, Album>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String albumID = Integer.toString(r.getInt("AID"));
			rv.put(albumID, new Album(r.getInt("AID"), r.getString("artist"), r.getString("title"),
					r.getString("category"), r.getFloat("price"), r.getString("picture")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	/**
	 * Retrieves a specific album with the given album id
	 * 
	 * @param aid
	 * @return the album object with the album id
	 * @throws SQLException
	 */
	public Album retrieveAlbum(int aid) throws SQLException {
		String query = "select * from album where aid=" + aid;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		Album al = null;

		while (r.next()) {
			al = new Album(r.getInt("AID"), r.getString("artist"), r.getString("title"), r.getString("category"),
					r.getFloat("price"), r.getString("picture"));
		}

		r.close();
		p.close();
		con.close();
		return al;
	}

	public Map<String, Album> retrieveAlbumByCat(String cat) throws SQLException {
		String query = "select * from album where category='" + cat + "'";
		Map<String, Album> rv = new HashMap<String, Album>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()) {
			String albumID = Integer.toString(r.getInt("AID"));
			rv.put(albumID, new Album(r.getInt("AID"), r.getString("artist"), r.getString("title"),
					r.getString("category"), r.getFloat("price"), r.getString("picture")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public Map<String, Album> retrieveBySearch(String parameter) throws SQLException {
		// since we are not aware of what the users will search the store with, we will
		// populate the results with every and anything that matches params
		Map<String, Album> rv = new HashMap<String, Album>();
		Connection con = this.ds.getConnection();
		String query = "select * from album where (category like ? OR artist like ?  OR title like ?)"; // check
																										// against,
																										// title
																										// category and
																										// artist
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, parameter);
		p.setString(2, parameter);
		p.setString(3, parameter);

		ResultSet r = p.executeQuery();
		while (r.next()) {
			String albumID = Integer.toString(r.getInt("AID"));
			rv.put(albumID, new Album(r.getInt("AID"), r.getString("artist"), r.getString("title"),
					r.getString("category"), r.getFloat("price"), r.getString("picture")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}

	public void addAlbum(Album album) throws Exception {
		String artist = album.getArtist();
		String title = album.getTitle();
		String category = album.getCategory();
		Float price = album.getPrice();
		String picture = album.getPicture();
		int aid = getLastAid() + 1;

		if (preventDiuplicates(artist, title) == true) { // check if album already exists, preventDiuplicates will
															// return false if album exists
			try {
				String query = "insert into Album (aid, artist, title, category, price, picture) values (?,?,?,?,?,?)"; // Insert

				Connection con = this.ds.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(query); // Making use of prepared statements
				// to insert bunch of data
				preparedStatement.setInt(1, aid);
				preparedStatement.setString(2, artist);
				preparedStatement.setString(3, title);
				preparedStatement.setString(4, category);
				preparedStatement.setFloat(5, price);
				preparedStatement.setString(6, picture);

				int i = preparedStatement.executeUpdate();
				if (i != 0) // Just to ensure data has been inserted into the database
				{
					con.close();
					preparedStatement.close();
					System.out.println("successfully updated db with new album " + title);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DB Failure On Album addition");
//			return "DB Failure On Album addition";
			}
		} else {
			throw new Exception("Album Already exists!");
		}

	}

	private int getLastAid() throws SQLException {
		String query = "SELECT * FROM ALBUM ORDER BY aid DESC LIMIT 1";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		int lastAid = 0;
		while (r.next()) {
			lastAid = r.getInt("AID");
		}
		r.close();
		p.close();
		con.close();
		return lastAid;
	}

	public boolean preventDiuplicates(String artist, String title) throws SQLException {
		Connection con = this.ds.getConnection();
		String query = "SELECT * FROM album WHERE title = ? AND artist = ?";
		PreparedStatement prepState = con.prepareStatement(query);
		prepState.setString(1, title);
		prepState.setString(2, artist);

		ResultSet res = prepState.executeQuery();
		if (res.next()) // if any result is returned, return false( a duplicate)
		{
			System.out.println("album already exists in DB!!!!");
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

}
