package web.com.filter.initalized;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import core.dao.model.login.Login;

/**
 * Initialized filter check if have cookie and create session login(name,
 * password)
 */
@WebFilter(servletNames = "InitFilter", urlPatterns ="/*")
public class InitFilter implements Filter {
	private static final Logger logger = Logger.getLogger(InitFilter.class);

	/**
	 * Default constructor.
	 */
	public InitFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Cookie[] cook = (((HttpServletRequest) request).getCookies());
		HttpSession session = (((HttpServletRequest) request).getSession(true));
		 researchCookie(cook, session);
		chain.doFilter(request, response);
	}

	/**
	 * method  research cookie 
	 * @param cook
	 * @param session
	 * @param loginUser
	 */
	public void researchCookie(Cookie[] cook, HttpSession session) {
		String login = "";
		String password = "";
		String idLogin = "";
		String email = "";
		Login loginUser = null;
		if (cook != null && cook.length > 0) {
			for (int i = 0; i < cook.length; i++) {
				if (cook[i].getName().equals("login")) {
					login = cook[i].getValue();
				}
				if (cook[i].getName().equals("password")) {
					password = cook[i].getValue();
				}
				if (cook[i].getName().equals("idLogin")) {
					idLogin = cook[i].getValue();
				}
				if (cook[i].getName().equals("email")) {
					email = cook[i].getValue();
				}
				
			}
			if ((!(login.equals(""))) && (!(password.equals("")))&&(!((idLogin.equals(""))))) {
				int id = Integer.parseInt(idLogin);
				loginUser = new Login(id,login, password,email);
				session.setAttribute("login", loginUser);
				logger.info("filterLogin");
			}
			
		}
	}



	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
