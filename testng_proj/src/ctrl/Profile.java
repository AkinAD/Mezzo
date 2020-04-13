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
 * Servlet implementation class Profile
 */
@WebServlet({ "/Profile", "/profile" })
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		//this.getServletContext().setAttribute("MS", MusicStore.getInstance());
		this.getServletContext().setAttribute("UM", UserModel.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		UserModel uModel = (UserModel) this.getServletContext().getAttribute("UM");
		Map<String, ProfileBean> data = new HashMap<String, ProfileBean>();
		
		if(true) // CHANGE THIS CHANGE THIS CHANGE THIS 
		{
			String username = SessionManagement.getBoundUsername(request.getSession());
			try {
				data = uModel.retrieveAccountByUsername(username);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("FAILED TO LOGIN USER: " + uModel.getError());
			}
		}
		else
		{
			//redirect to login
		}
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
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
