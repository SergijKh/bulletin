package core.test.mvc.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GeneralUtils {
	public static HttpSession getHttpSession() {

	    return ((HttpServletRequest) FacesContext.getCurrentInstance()
	            .getExternalContext().getRequest()).getSession();
	}

}
