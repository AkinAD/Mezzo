package ctrl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Album;
import model.MusicStore;

/**
 * Servlet implementation class ProductPage
 * 
 * @author alanyork
 */
@WebServlet("/ProductPage")
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
		
		try {
			// Normalize input
			int aidInt = Integer.valueOf(aid);
			
			// Retrieve Album			
			Map<String,Album> retrRes = musicStore.retrievAlbum(aidInt);
			Album retrResAlbum = retrRes.get(new Integer(aidInt).toString());
			if (retrResAlbum == null) {
				throw new IllegalArgumentException();
			}
			
		} catch (Exception e) {
			// I call this a pro gamer move
			response.setStatus(400);
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
