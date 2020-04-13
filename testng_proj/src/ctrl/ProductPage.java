package ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Album;
import bean.ReviewBean;
import model.MusicStore;

/**
 * Servlet implementation class ProductPage
 * 
 * @author alanyork
 */
@WebServlet({"/ProductPage"})
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String PRODUCTPAGE = "singleProduct.jsp";
	private static final String REVIEW_RESULTS = "reviewResults";
	private static final String ALBUM_RESULT = "albumResult";	
	private static final String MEZZO_USERNAME = "MezzoUsername";
	
	private MusicStore musicStore;
	
	
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public ProductPage() throws ClassNotFoundException {
        super();
        this.musicStore = MusicStore.getInstance();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String review = request.getParameter("review");
		String rating = request.getParameter("rating");
		String username = (String) request.getSession().getAttribute(MEZZO_USERNAME);
		
		try {
			// Normalize input
			int aidInt = Integer.valueOf(aid);
			
			// Retrieve Album			
			Map<String,Album> retrRes = musicStore.retrieveAlbum(aidInt);
			Album retrResAlbum = retrRes.get(new Integer(aidInt).toString());
			if (retrResAlbum == null) {
				throw new IllegalArgumentException();
			}
			
			// Retrieve Reviews
			Map<String,ReviewBean> retrRevRes = musicStore.retrieveReviews(aidInt);
			
			// Add review logic
			if (review != null) {
				int ratingInt = Integer.valueOf(rating);
				musicStore.putReview(aidInt, username, ratingInt, review);
			}
			
			// Output page content to request context and dispatch page
			request.setAttribute(ALBUM_RESULT, retrResAlbum);
			request.setAttribute(REVIEW_RESULTS, retrRevRes);
			request.getRequestDispatcher(PRODUCTPAGE).forward(request, response);
		} catch (Exception e) {
			// I call this a pro gamer move
			response.setStatus(400); // If something goes wrong it's because you're not dank enough
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
