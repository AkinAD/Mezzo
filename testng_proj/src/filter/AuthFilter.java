package filter;

import java.io.IOException;
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

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/ProductPage" }, servletNames = { "ProductPage" })
public class AuthFilter implements Filter {

    private static final String AUTH_CAUSE = "AuthCause";
	private static final String LOGIN_ENDPOINT = "/login";
	private static final String MEZZO_USERNAME = "MezzoUsername";

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
		
		String username = (String) httpReq.getSession().getAttribute(MEZZO_USERNAME);
		
		boolean authRequired = false;
		
		// Reviewing requires auth
		boolean addReviewPath = httpReq.getServletPath().equals("/ProductPage");
		addReviewPath = addReviewPath && (httpReq.getParameter("review") != null);
		authRequired = authRequired || addReviewPath;
		
		if ((username == null || username.length() == 0) && authRequired) {
			// Not logged in
			httpReq.setAttribute(AUTH_CAUSE, httpReq.getRequestURI()+httpReq.getQueryString());
			httpReq.getRequestDispatcher(LOGIN_ENDPOINT).forward(httpReq, httpResp);
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

}
