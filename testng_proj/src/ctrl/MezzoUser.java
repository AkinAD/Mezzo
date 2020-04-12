package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProfileBean;
import model.UserModel;

/**
 * Servlet implementation class MezzoUser
 */
@WebServlet({"/Login", "/Register", "/login", "/register" })
public class MezzoUser extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
				profile = populateUser(data);
				request.setAttribute("profile", profile);
				request.setAttribute("error", uModel.getError());

				request.getRequestDispatcher(Profile).forward(request, response);
			} 
			else {
				request.setAttribute("error", uModel.getError());
				System.out.println("BOTTOM");
				request.getRequestDispatcher(LoginReg).forward(request, response);
				System.out.println("there Was an Error in Log in : " + uModel.getError());
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

}
