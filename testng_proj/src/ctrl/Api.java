package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Album;
import model.MusicStore;

/**
 * Servlet implementation class Api
 */
@WebServlet({"/api","/api/products/*"})
public class Api extends HttpServlet {
	public static final String PRODUCT_ENDPOINT = "/api/products";
	
	private static final long serialVersionUID = 1L;
	
	private MusicStore musicStore;
       
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Api() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
         musicStore = MusicStore.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		response.setContentType("application/json");
		
		if (servletPath.equals(PRODUCT_ENDPOINT)) {
			productEndpoint(request,response);
		}
	}

	private void productEndpoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int alb;
		String[] pathInfos = request.getPathInfo().split("/");
		
		System.out.println(request.getPathInfo());
		
		try {
			if (pathInfos.length != 2) {
				throw new IllegalArgumentException();
			}
			alb = Integer.parseInt(pathInfos[1]);
			if (alb < 0 || alb > 10000) {
				throw new IllegalArgumentException();
			}
			
			Map<String,Album> albumRes = musicStore.retrievAlbum(alb);
			Album resultAlbum = albumRes.values().iterator().next();
			
			String responseDoc = produceAlbumDocument(resultAlbum.getAid(),resultAlbum.getArtist(),resultAlbum.getTitle(),resultAlbum.getCategory(),resultAlbum.getPrice(),resultAlbum.getPicture());
			response.getOutputStream().print(responseDoc);
		} catch(Exception e) {
			// Invalid query
			e.printStackTrace();
			response.sendError(404);
		}
	}

	
	private static String produceAlbumDocument(int aid, String artist, String title, String category, double price, String picture) {
		JsonObjectBuilder bob = Json.createObjectBuilder(); // Bob the builder
		bob.add("aid", aid);
		bob.add("artist", artist);
		bob.add("title", title);
		bob.add("category", category);
		bob.add("price", price);
		bob.add("picture", picture);
		
		return bob.build().toString();
	}
}
