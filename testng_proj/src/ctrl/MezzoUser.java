package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import bean.ProfileBean;
import filter.AuthFilter;
import filter.AuthFilter.AuthCauseDeserializer;
import model.UserModel;

/**
 * Servlet implementation class MezzoUser
 * @author akinad1
 * @author alanyork
 */
@WebServlet({"/Login", "/Register", "/login", "/register" })
public class MezzoUser extends HttpServlet {
	private static final String SERIALIZED_CALLBACK = "serializedCallback";
	private static final long serialVersionUID = 1L;
	// Copying all the input parameters in to local variables
	private static String LoginReg = "/LoginReg.jsp";
	private static String Profile = "/profile.jsp";

	public MezzoUser() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//this.getServletContext().setAttribute("MS", MusicStore.getInstance());
		this.getServletContext().setAttribute("UM", UserModel.getInstance());
	}

	// GET is marginally less safe than POST
	// Browser history stores POST params
	// We ran out of time to fix this
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String callbackParam = request.getParameter(AuthFilter.CALLBACK_PARAM);
		if (request.getParameter(AuthFilter.CALLBACK_PARAM) != null) {
			// Access control challenge flow
			// Lel XSS
			AuthFilter.AuthCauseDeserializer callbackDeser = new AuthCauseDeserializer(callbackParam);
			callbackParam = (new AuthFilter.AuthCauseSerializer(callbackDeser.getCause(), callbackDeser.getParams())).serialize();
			request.setAttribute(SERIALIZED_CALLBACK, callbackParam);
		}
		
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		if (request.getParameter("search")!= null)
		{
			System.out.println("HTML works");
			System.out.println(request.getParameter("search"));
		}
		System.out.println("Enter Doget");
		System.out.println("Params " + request.getParameterNames().toString());
		System.out.println("Request URI: " + request.getRequestURI() );
		System.out.println("USERNAME: " + request.getParameter("username"));
		if (request.getParameter("signup") == null && request.getParameter("signin") == null) {
			request.getRequestDispatcher(LoginReg).forward(request, response);
			System.out.println("LOADING SIGN IN/ SIGN UP PAGE");
		} 
		else if (request.getParameter("signin") == null && (request.getParameter("signup")!= null || request.getParameter("signup").equals("Register"))) {
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String email = request.getParameter("email");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			//MusicStore MS = (MusicStore) this.getServletContext().getAttribute("MS");
			UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
			System.out.println("THIS SHOULD ONLY PRINT ONCE");
			try {
				uModel.registerUser(fname, lname, username, email, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FAILED TO REGISTER USER: " + uModel.getError());
			}
			Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
			Map<String, List<String>> profile = new HashMap<String, List<String>>();

			try {
				data = uModel.retrieveAccountByUsername(username);
			} catch (Exception e) {
				System.out.println("Failure to populate user.. goodluck tracing this one");
				e.printStackTrace();
			}
				// this the part where you display error. or do nothing tbh
			if (uModel.getError().equals("")) {
				profile = populateUser(data);
				request.setAttribute("profile", profile);
				request.setAttribute("error", uModel.getError());

				request.getRequestDispatcher(Profile).forward(request, response);
			} else {
				request.setAttribute("error", uModel.getError());
				request.getRequestDispatcher(LoginReg).forward(request, response);
				System.out.println("there Was an Error in Register: ##");
				System.out.println(uModel.getError());
			}
		}
		
		else if (request.getParameter("signup") == null && (request.getParameter("signin")!= null || request.getParameter("signin").equals("Login"))) {
			System.out.println("SIGN IN CLICKED");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//MusicStore MS = (MusicStore) this.getServletContext().getAttribute("MS");
			UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
			System.out.println("THIS SHOULD also ONLY PRINT ONCE");

			Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
			Map<String, List<String>> profile = new HashMap<String, List<String>>();

			try {
				data = uModel.loginUser(username, password);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FAILED TO LOGIN USER: " + uModel.getError());
			}
			if (uModel.getError().equals("")) {
				String curLoggedUser = data.values().iterator().next().getUserName();
				SessionManagement.bindUser(request.getSession(), curLoggedUser);
				
				if (callbackParam != null && !callbackParam.isEmpty()) {
					// Access control challenge login
					AuthFilter.AuthCauseDeserializer callbackDeserial = new AuthFilter.AuthCauseDeserializer(callbackParam);
					String callbackEndpoint = callbackDeserial.getCause();
					String callbackQueryString = "?";
					if (callbackDeserial.getParams() != null) {
						// Reintroduce the params from the auth cause
						for (Entry<String, String[]> x : callbackDeserial.getParams().entrySet()) {
							callbackQueryString += x.getKey();
							boolean hasVal = (x.getValue() != null && x.getValue().length > 0);
							hasVal = hasVal && x.getValue()[0] != null && !x.getValue()[0].isEmpty();
							callbackQueryString += (hasVal ? "=" + x.getValue()[0] + "&" : "&");
							for (int i=1; x.getValue() != null && i<x.getValue().length; i++) {
								callbackQueryString += x.getKey();
								hasVal = x.getValue()[i] != null && !x.getValue()[i].isEmpty();
								callbackQueryString += (hasVal ? "=" + x.getValue()[i] + "&" : "&");
							}
						}
						callbackQueryString = callbackQueryString.substring(0, callbackQueryString.length()-1);
					}
					String redirectPath = request.getServletContext().getContextPath();
					redirectPath += callbackEndpoint;
					redirectPath += callbackQueryString;
					response.sendRedirect(redirectPath);
					
					
				} else {
					// General login
					profile = populateUser(data);
					request.setAttribute("profile", profile);
					request.setAttribute("error", uModel.getError());
					request.getRequestDispatcher(Profile).forward(request, response);
				}				
				
			} 
			else {
				request.setAttribute("error", uModel.getError());
				System.out.println("BOTTOM");
				System.out.println("there Was an Error in Log in : " + uModel.getError());
				response.setStatus(403);
				request.getRequestDispatcher(LoginReg).forward(request, response);
				
			}
		}
		System.out.println("==== END DOGET ====");
		

	}

	private Map<String, List<String>> populateUser(Map<String, ProfileBean> data) {
		Map<String, List<String>> profile = new HashMap<String, List<String>>();
		for (String stuff : data.keySet()) {
			List<String> tmp = new ArrayList<String>();
			tmp.add(data.get(stuff).getUserName());
			// delete line below
			System.out.println(data.get(stuff).getUserName());

			tmp.add(data.get(stuff).getFname());
			System.out.println(data.get(stuff).getFname());

			tmp.add(data.get(stuff).getLname());
			System.out.println(data.get(stuff).getLname());

			tmp.add(data.get(stuff).getEmail());
			System.out.println(data.get(stuff).getEmail());

			tmp.add(data.get(stuff).getPrivilege());
			System.out.println(data.get(stuff).getPrivilege());

			profile.put(stuff, tmp);
		}
		return profile;
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
	/**
	 * HTTPServletRequestWrapper with mutable parameters.
	 * This is a totally safe hack I swear.
	 * 
	 * This is probably dead code
	 * 
	 * @author alanyork
	 *
	 */
	public static class DangerousHttpServletRequest extends HttpServletRequestWrapper {
		private Map<String, String[]> paramMap;
		
		public DangerousHttpServletRequest(HttpServletRequest request) {
			super(request);
			
			if (super.getParameterMap() != null) {
				//Store a mutable version of the parameter map of the wrapped request
				this.paramMap = new HashMap<String,String[]>();
				
				for (String x : super.getParameterMap().keySet()) {
					String[] curValues = super.getParameterMap().get(x).clone();
					this.paramMap.put(x, curValues);
				}
			}
		}
		
		/**
		 * Returns a java.util.Map of the parameters of this request per the parent.
		 * Request parameters are extra information sent with the request.
		 * For HTTP servlets, parameters are contained in the query string or posted form data.
		 * 
		 * @return
		 * Same per parent, that is a java.util.Map containing parameter names as keys.
		 * Parameter values are map values. Mutable, unlike parent.
		 */
		public Map<String, String[]> getParameterMap() {
			return paramMap;
		}
		
		public String[] getParameterValues(String name) {
			return getParameterMap().get(name);
		}
		
		public String getParameter(String name) {
			return (getParameterValues(name) == null) ? null : getParameterValues(name)[0];
		}
		
		public Enumeration<String> getParameterNames() {
			return new StringSetEnumeration(getParameterMap().keySet());
		}
		
		public Map<String, String[]> getRealParameterMap(){
			return super.getParameterMap();
		}
		
		private static class StringSetEnumeration implements Enumeration<String> {
			private Iterator<String> strSetIter;
			
			@SuppressWarnings("unused")
			private StringSetEnumeration() {
			}
			
			public StringSetEnumeration(Set<String> strSet) {
				this.strSetIter = strSet.iterator();
			}
			
			@Override
			public boolean hasMoreElements() {
				return strSetIter.hasNext();
			}

			@Override
			public String nextElement() {
				return strSetIter.next();
			}
			
		}
	}

}
