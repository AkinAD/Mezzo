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

import bean.ReviewBean;

/**
 * 
 * @author alanyork
 *
 */
public class ReviewDAO {
	private DataSource ds;
	
	public ReviewDAO() {
		try {
			//ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/New_Derby");
			ds = (DataSource) (new InitialContext()).lookup("jdbc/Db2-4413");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	
	public Map<String,ReviewBean> retrieveReviews(int aid) throws SQLException{
		Map<String, ReviewBean> returnVal = new HashMap<String, ReviewBean>();
		
		String query = "SELECT * FROM Review WHERE aid=?";
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		p.setInt(1, aid);		
		ResultSet r = p.executeQuery();
		
		while (r.next()) {
			ReviewBean cur = new ReviewBean();
			cur.setAid(r.getInt("aid"));
			cur.setUsername(r.getString("username"));
			cur.setRating(r.getInt("rating"));
			cur.setReview(r.getString("review"));
			
			returnVal.put(cur.getUsername(), cur);
		}
		
		r.close();
		p.close();
		con.close();		
		
		return returnVal;
	}
	
	public int createReview(int aid, String username, int rating, String review) throws SQLException {
		String query = "INSERT INTO Review (aid, username, rating, review) VALUES (?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		p.setInt(1, aid);
		p.setString(2, username);
		p.setInt(3, rating);
		p.setString(4, review);
		
		int execRes = p.executeUpdate();
		
		p.close();
		con.close();
		
		return execRes;
	}
	
	
	
}
