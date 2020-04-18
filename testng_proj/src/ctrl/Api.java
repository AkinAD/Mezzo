package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AlbumBean;
import bean.POItemBean;
import model.MusicStore;
import model.PurchaseOrder;

/**
 * REST endpoints
 * 
 * Servlet implementation class Api
 * 
 * @author alanyork
 * 
 */
@WebServlet({"/api","/api/products/*", "/api/orders/*"})
public class Api extends HttpServlet {
	private static final String PARAM_AID = "aid";

	private static final String ERR_NOT_FOUND = "Not found";
	private static final String ERR_MISSING_PARAM = "Too few parameters";
	private static final String ERR_BAD_PARAM = "Invalid parameter";

	public static final String PRODUCT_ENDPOINT = "/api/products";
	private static final String ORDERS_ENDPOINT = "/api/orders";
	
	private static final long serialVersionUID = 1L;
	
	private MusicStore musicStore;
	private PurchaseOrder purchaseOrd;
       
    /**
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Api() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
         musicStore = MusicStore.getInstance();
         purchaseOrd = PurchaseOrder.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		response.setContentType("application/json");
		
		// Determine which endpoint is being accessed
		if (servletPath.equals(PRODUCT_ENDPOINT)) {
			productEndpoint(request,response);
		} else if (servletPath.equals(ORDERS_ENDPOINT)) {
			ordersEndpoint(request,response);
		}
	}

	private String[] getPathInfos(HttpServletRequest request) {
		return request.getPathInfo() != null ? request.getPathInfo().split("/") : new String[0];
	}
	
	/**
	 * The orders endpoint serves /api/orders and produces a JSON document with the following entries:
	 * <ul>
	 * <li>totalOrders - Quantity of orders for this item</li>
	 * <li>orders - Array of PO IDs corresponding to orders of this item</li>
	 * </ul>
	 * 
	 * It must be called with the parameter aid which is the album ID for which the orders are retrieved.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void ordersEndpoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] pathInfos = getPathInfos(request);	
		String aidParam = request.getParameter(PARAM_AID);
		
		if (aidParam == null) {
			response.setStatus(400);
			response.getOutputStream().print(produceErrorDocument(400,ERR_MISSING_PARAM));
		} else if (aidParam.isEmpty()) {
			response.setStatus(400);
			response.getOutputStream().print(produceErrorDocument(400,ERR_BAD_PARAM));
		} else if (pathInfos.length > 1) {
			// Non-existent endpoint
			response.setStatus(404);
			response.getOutputStream().print(produceErrorDocument(404,ERR_NOT_FOUND));
		} else {
			boolean validQuery = true;
			
			try {
				int aid = Integer.parseInt(aidParam);
				if (validQuery) {
					Map<String, POItemBean> ords = purchaseOrd.retrieveItemsByAlbum(aid);
					String responseDoc = produceOrdersArrayDocument(ords.keySet().toArray(new String[ords.size()]));
					response.getOutputStream().print(responseDoc);
				}
			} catch (Exception e) {
				e.printStackTrace();
				response.setStatus(404);
				response.getOutputStream().print(produceErrorDocument(404,ERR_NOT_FOUND));
			}			
		}
	}

	/**
	 * 
	 * The product endpoint itself has nothing.
	 * Its first-level subpaths (album IDs) correspond to the respective album.
	 * The JSON document produced by these endpoints contain the following entries:
	 * <ul>
	 * <li>aid - Album ID</li>
	 * <li>artist - Album artist name</li>
	 * <li>title - Album title</li>
	 * <li>category - Album genre</li>
	 * <li>price - Album price</li>
	 * <li>picture - Album cover art</li>
	 * </ul>
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void productEndpoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] pathInfos = getPathInfos(request);
		int alb;
		
		boolean validQuery = true;
		
		try {			
			if (pathInfos.length > 3) {
				validQuery = false;
				throw new IllegalArgumentException();
			} else if (pathInfos.length == 3) {
				if (!pathInfos[2].isEmpty()) {
					validQuery = false;
					throw new IllegalArgumentException();
				}
			}
			
			alb = Integer.parseInt(pathInfos[1]);
			if (alb < 0 || alb > 10000) {
				validQuery = false;
				throw new IllegalArgumentException();
			}
			
			if (validQuery) {
				Map<String,AlbumBean> albumRes = musicStore.retrievAlbum(alb);
				AlbumBean resultAlbum = albumRes.values().iterator().next();
			
				String responseDoc = produceAlbumDocument(resultAlbum.getAid(),resultAlbum.getArtist(),resultAlbum.getTitle(),resultAlbum.getCategory(),resultAlbum.getPrice(),resultAlbum.getPicture());
				response.getOutputStream().print(responseDoc);
			}
		} catch(Exception e) {
			// Invalid query
			e.printStackTrace();
			response.setStatus(404);
			response.getOutputStream().print(produceErrorDocument(404,ERR_NOT_FOUND));
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
	
	private static String produceErrorDocument(int responseCode, String message) {
		JsonObjectBuilder bob = Json.createObjectBuilder(); // Bob the builder
		bob.add("status", responseCode);
		bob.add("error", message);
		
		return bob.build().toString();
	}
	
	/**
	 * 
	 * @param orders
	 * Array of PO ids
	 * @return
	 */
	private static String produceOrdersArrayDocument (String[] orders) {
		JsonObjectBuilder bob = Json.createObjectBuilder(); // Bob the builder
		bob.add("totalOrders", orders.length);
		
		JsonArrayBuilder arrBuild = Json.createArrayBuilder();
		for (String x : orders) {
			arrBuild.add(x);
		}		
		bob.add("orders", arrBuild.build());
		
		return bob.build().toString();
	}
	
	
}
