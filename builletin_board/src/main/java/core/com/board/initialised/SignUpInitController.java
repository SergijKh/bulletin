package core.com.board.initialised;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.dao.model.login.Login;
import core.dao.service.login.LogService;

/**
 * class sign-up user add data in base and initialized user
 * 
 * 
 * @author Sergey
 *
 */
@Component("signUpInitController")
public class SignUpInitController {
	@Autowired
	LogService serLog;

	/**
	 * add in base user and login if parameter login,password, email don't empty
	 * and not null create cookie and create session attribute Login
	 * 
	 * @param httpRequest
	 *            HttpServletRequest
	 */
	public void addBaseLogin( Login loginN, HttpServletRequest httpRequest,
			HttpServletResponse response) {
		HttpSession session = (((HttpServletRequest) httpRequest)
				.getSession(true));
		
		/*String login = (String) httpRequest.getParameter("login");
		String password = (String) httpRequest.getParameter("password");
		String nameUSer = (String) httpRequest.getParameter("nameUser");*/
		String login = loginN.getLogin();
		String password = loginN.getPassword();
		String nameUSer = loginN.getNameUser();
		if (!((login.equals("")) && (!(password.equals("")) && (!(nameUSer
				.equals("")))))) {
			Login log = new Login(login, password, nameUSer);
			int idLogin = serLog.addLogin(log);
			log = new Login(idLogin, login, password, nameUSer);
			Cookie CIdLogin = new Cookie("IdLogin", "" + idLogin);
			Cookie CLogin = new Cookie("login", login);
			Cookie CPassword = new Cookie("password", password);
			Cookie CNameUser = new Cookie("nameUSer", nameUSer);
			response.addCookie(CLogin);
			response.addCookie(CPassword);
			response.addCookie(CIdLogin);
			response.addCookie(CNameUser);
			session.setAttribute("login", log);
		}
	}

	/**
	 * 
	 * method equals initialized parameter name login and password with data
	 * base if true create cookie and session attribute login (Login object)
	 * 
	 * @param httpRequest
	 *            HttpServletRequest
	 * @param response
	 *            HttpServletResponse
	 */
	public void initialized(Login loginN, HttpServletRequest httpRequest,
			HttpServletResponse response) {
		//String login = (String) httpRequest.getParameter("login");
		//String password = (String) httpRequest.getParameter("password");
		String login = loginN.getLogin();
		String password = loginN.getPassword() ;
		HttpSession session = (((HttpServletRequest) httpRequest)
				.getSession(true));
		Login log = null;
		if (!(login.equals("")) && (!(password.equals("")))) {
			log = serLog.getByNameLogin(login);
			if (log != null) {
				if ((login.equals(log.getLogin()) && (password.equals(log
						.getPassword())))) {
					Cookie CLogin = new Cookie("login", login);
					Cookie CPassword = new Cookie("password", password);
					Cookie CIdLogin = new Cookie("idLogin", ""
							+ log.getIdLogin());
					Cookie CnameUser = new Cookie("nameUser", log.getNameUser());
					response.addCookie(CLogin);
					response.addCookie(CPassword);
					response.addCookie(CIdLogin);
					response.addCookie(CnameUser);
					session.setAttribute("login", log);
				}

			}

		}

	}

}
