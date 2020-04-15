package ctrl;

import java.io.IOException;
import java.util.Arrays;
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
@WebServlet({ "/ProductPage" })
public class ProductPage extends HttpServlet {

	private static final String REV_STAR_FILLS = "revStarFills";

	private static final long serialVersionUID = 1L;

	private static final String PRODUCTPAGE = "singleProduct.jsp";

	private static final String REVIEW_RESULTS = "reviewResults";
	private static final String ALBUM_RESULT = "albumResult";
	private static final String AVG_REV_SCORE = "avgRevScore";
	private static final String REV_PROPORTIONS = "revProportions";
	private static final String REV_COUNTS = "revCounts";
	private static final String REV_COUNT = "revCount";

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aid = request.getParameter("aid");
		String review = request.getParameter("review") == null || request.getParameter("review").isEmpty() ? ""
				: request.getParameter("review");
		String rating = request.getParameter("rating");
		String username = SessionManagement.getBoundUsername(request.getSession());

		try {
			// Normalize input
			int aidInt = Integer.valueOf(aid);

			// Retrieve Album
			Map<String, Album> retrRes = musicStore.retrievAlbum(aidInt);
			Album retrResAlbum = retrRes.get(new Integer(aidInt).toString());
			if (retrResAlbum == null) {
				throw new IllegalArgumentException();
			}

			// Add review logic
			if (rating != null) {
				int ratingInt = Integer.valueOf(rating);
				System.out.println("PP " + username);
				musicStore.putReview(aidInt, username, ratingInt, review);
			}

			// Retrieve Reviews
			Map<String, ReviewBean> retrRevRes = musicStore.retrieveReviews(aidInt);
			int revCount = retrRevRes.size();
			int reviewSum = 0;
			int revCounts[] = new int[5];
			int revProportions[] = new int[5];
			Arrays.fill(revCounts, 0);
			for (ReviewBean x : retrRevRes.values()) {
				reviewSum += x.getRating();
				revCounts[x.getRating() - 1] = revCounts[x.getRating() - 1] + 1;
			}
			for (int i = 0; i < 5; i++) {
				revProportions[i] = (int) (100 * ((double) revCounts[i] / (double) revCount));
			}
			double avgRev = (double) reviewSum / (double) revCount;
			boolean revStarFills[] = new boolean[5];
			Arrays.fill(revStarFills, false);
			for (int i = 0; i < (int) avgRev; i++) {
				revStarFills[i] = true;
			}

			// Output page content to request context and dispatch page
			request.setAttribute(ALBUM_RESULT, retrResAlbum);
			request.setAttribute(REVIEW_RESULTS, retrRevRes);
			request.setAttribute(AVG_REV_SCORE, avgRev);
			request.setAttribute(REV_COUNT, revCount);
			request.setAttribute(REV_COUNTS, revCounts);
			request.setAttribute(REV_PROPORTIONS, revProportions);
			request.setAttribute(REV_STAR_FILLS, revStarFills);

			request.getRequestDispatcher(PRODUCTPAGE).forward(request, response);
		} catch (Exception e) {
			// I call this a pro gamer move
			response.setStatus(400); // If something goes wrong it's because you're not dank enough
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
