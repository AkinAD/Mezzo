package filter;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProfileBean;
import ctrl.SessionManagement;
import model.UserModel;

/**
 * Servlet Filter implementation class AuthFilter.
 * 
 * Acts as an access control monitor.
 * 
 * @author alanyork
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD }
					, urlPatterns = { "/*" })
public class AuthFilter implements Filter {

	public static final String CALLBACK_PARAM = "callback";
	private static final String ADMIN_PRIV = UserModel.ADMIN_PRIV;
    private static final String CUSTOMER_PRIV = UserModel.CUSTOMER_PRIV;
    private static final String PARTNER_PRIV = UserModel.PARTNER_PRIV;
    
	private static final String AUTH_CAUSE_ENDPOINT = "AuthCauseEndpoint";
	private static final String AUTH_CAUSE_PARAMS = "AuthCauseParams";
	public static final String LOGIN_ENDPOINT = "/login";

	private UserModel userModel = UserModel.getInstance();
	
	/**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		
		String username = (String) SessionManagement.getBoundUsername(httpReq.getSession());
		
		// Hardcoded permissions (I suck)
		boolean authRequired = false;
		
		// We ran out of time to put this in the DB schema
		// Path, Allowed Groups
		Map<String, List<String>> pathAccessMatrix = new HashMap<String,List<String>>();
		
		List<String> adminAccess = new ArrayList<String>();
		adminAccess.add(ADMIN_PRIV);
		pathAccessMatrix.put("/admin", adminAccess);
		pathAccessMatrix.put("/Admin", adminAccess);
		
		// Access controlled paths require auth
		// Fail-unsafe default
		boolean accessControlledPath = pathAccessMatrix.containsKey(httpReq.getServletPath());
		authRequired = authRequired || accessControlledPath;
		
		// Reviewing requires auth
		boolean addReviewPath = httpReq.getServletPath().equals("/ProductPage");
		addReviewPath = addReviewPath && (httpReq.getParameter("review") != null);
		authRequired = authRequired || addReviewPath;
		
		// Check for authorization
		if ((username == null || username.length() == 0) && authRequired) {
			// Not logged in
			System.out.println("AuthFilter: Login required at endpoint "+httpReq.getServletPath());
			String serializedCallback = (new AuthCauseSerializer(httpReq.getServletPath(),httpReq.getParameterMap())).serialize();
			String redirectPath = request.getServletContext().getContextPath()+LOGIN_ENDPOINT+"?"+CALLBACK_PARAM+"=";
			httpResp.sendRedirect(redirectPath+serializedCallback);

		} else if (accessControlledPath) {
			// Access controlled path
			List<String> allowedGroups = pathAccessMatrix.get(httpReq.getServletPath());
			try {
				ProfileBean curUser = userModel.retrieveAccountByUsername(username).entrySet().iterator().next().getValue();
				System.out.println("AuthFilter: " + curUser.getUserName() + " requested endpoint " + httpReq.getServletPath());
				System.out.println("AuthFilter: " + curUser.getUserName() + " is " + curUser.getPrivilege());
				System.out.println("AuthFilter: " + httpReq.getServletPath() + " allows " + allowedGroups);
				if (allowedGroups.contains(curUser.getPrivilege())) {
					System.out.println("AuthFilter: Access Granted");
					chain.doFilter(request, response);
				} else {
					System.out.println("AuthFilter: Access Denied");
					httpResp.setStatus(403);
				}
			} catch (Exception e) {
				System.out.println("AuthFilter: Error");
				httpResp.setStatus(403);
				e.printStackTrace();
			}
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * Serializes authorization requests for handover to client.
	 * Supposed to produce tokens, but we ran out of time.
	 * This presents an XSRF risk
	 * 
	 * @author alanyork
	 *
	 */
	public static class AuthCauseSerializer {
		private String cause;
		private Map<String,String[]> params;
		private JsonObject json;
		
		private AuthCauseSerializer() {}
		
		/**
		 * 
		 * @param authCause
		 * @param authCauseParams
		 * The parameters accompanying the auth cause. Null or empty if none.
		 */
		public AuthCauseSerializer(String authCause, Map<String,String[]> authCauseParams) {
			this.cause = authCause;
			this.params = authCauseParams;
			
			JsonObjectBuilder jsonBuild = Json.createObjectBuilder();
			jsonBuild.add(AUTH_CAUSE_ENDPOINT, this.cause);
			
			if (!(authCauseParams == null || authCauseParams.isEmpty())) {
				JsonObjectBuilder paramsJsonBuild = Json.createObjectBuilder();
				for (Entry<String, String[]> x: this.params.entrySet()) {
					String key = x.getKey();
					String[] values = x.getValue();
					
					JsonArrayBuilder paramJsonArrBuild = Json.createArrayBuilder();
					for (String y : values) {
						paramJsonArrBuild.add(y);
					}
					
					paramsJsonBuild.add(key, paramJsonArrBuild.build());
				}
				
				jsonBuild.add(AUTH_CAUSE_PARAMS, paramsJsonBuild.build());
			}			
			
			json = jsonBuild.build();
		}
		
		/**
		 * 
		 * @return
		 * @throws IllegalStateException
		 * Invoked without correct state. Typically when no cause. 
		 */
		public String serialize() {
			if (cause == null) {
				throw new IllegalStateException();
			}
			return Base64.getUrlEncoder().encodeToString(json.toString().getBytes());
		}

		/**
		 * @return the cause
		 */
		public String getCause() {
			return cause;
		}

		/**
		 * @return the params
		 */
		public Map<String, String[]> getParams() {
			return params;
		}

	}
	
	public static class AuthCauseDeserializer {
		private String cause;
		private Map<String,String[]> params;
		private JsonObject json;
		
		public AuthCauseDeserializer(String input) {
			String jsonInput = new String(Base64.getUrlDecoder().decode(input.getBytes())); 

			this.json = Json.createReader(new StringReader(jsonInput)).readObject();
			this.cause = json.getString(AUTH_CAUSE_ENDPOINT);
			
			JsonObject paramJson = json.getJsonObject(AUTH_CAUSE_PARAMS);
			if (paramJson != null) {
				this.params = new HashMap<String,String[]>();
				
				for (Entry<String, JsonValue> x : paramJson.entrySet()) {
					String curKey = x.getKey();
					JsonArray curValue = (JsonArray) x.getValue();
					
					String[] curParamVal = new String[curValue.size()];
					for (int i = 0; i<curValue.size(); i++) {
						curParamVal[i] = ((JsonString) curValue.get(i)).getString();
					}
					
					this.params.put(curKey, curParamVal);
				}
			}
		}
		
		/**
		 * @return the cause
		 */
		public String getCause() {
			if (json == null || cause == null) {
				throw new IllegalStateException();
			}
			
			return cause;
		}

		/**
		 * @return the params, null if no params
		 */
		public Map<String, String[]> getParams() {
			if (json == null || cause == null) {
				throw new IllegalStateException();
			}
			
			return params;
		}
	}
}
