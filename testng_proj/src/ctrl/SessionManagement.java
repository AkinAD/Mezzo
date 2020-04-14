package ctrl;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author alanyork
 *
 */
public class SessionManagement {
	private static final String MEZZO_USERNAME = "MezzoUsername";
	
	public static void bindUser(HttpSession session, String username) {
		session.setAttribute(MEZZO_USERNAME, username);
	}
	
	public static void unbindUser(HttpSession session) {
		session.removeAttribute(MEZZO_USERNAME);
		session.invalidate();
	}
	
	public static String getBoundUsername(HttpSession session) {
		return (String) session.getAttribute(MEZZO_USERNAME);
	}

}
