package web.com.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import core.dao.model.login.Login;



/**
 *  initialized user in site
 * @author Sergey
 *
 */
public class SignUp extends TagSupport {
	private static final Logger logger1 = Logger.getLogger(SignUp.class);

	private static final long serialVersionUID = 1L;

	private Properties config;
	private InputStream inputStream;
	public SignUp(){
		   this.config = new Properties();
		   this.inputStream = getClass().getClassLoader()
								.getResourceAsStream("redirect.properties");
	}
	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			config.load(inputStream);
			out.print("<div id='signUp'> ");
			
			 out.print(" <table class='text'><tr> <td>Вход</td>"
					 +"<td class='rightcol'><a href ='"+config.getProperty("init.jsp")+"'>регистрирация</a></td> </tr </table>"); 
			out.print("<tr> <td><form  action= '"+config.getProperty("initialized")+"'  method='post'>"
					+"<input type = 'hidden' name='initlogin' value= 'inituser'/>"
					+ " логин </br><input type='text' name='login'> </br><p></p></br>"
					+ " пароль</br> <input type='text' name='password'> </br><p></p></br>"
					+ "<input type='submit' value = 'Войти на сайт '></form></td> </tr><div>");

		} catch (IOException ioException) {
			logger1.error("Error: " + ioException.getMessage());
		}
		return SKIP_BODY;
	}

	
	

}