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

	public AlbumDAO() throws ClassNotFoundException{
		try {
//			  ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/New_Derby");
			  ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}	}
	public Map<String, Album> retrieve(int aid) throws SQLException
	{
		String query = "select * from album where aid=" + aid;
		Map<String, Album> rv = new HashMap<String, Album>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while (r.next()){
			String albumID = Integer.toString(r.getInt("AID"));
			rv.put(albumID, new Album(r.getInt("AID"), r.getString("artist"), r.getString("title"), r.getString("category"), r.getFloat("price"), r.getString("picture")));
		}
		r.close();
		p.close();
		con.close();
		return rv;
		 }
}
