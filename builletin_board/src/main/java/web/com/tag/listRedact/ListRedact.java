package web.com.tag.listRedact;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import web.com.tag.SignUp;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;

public class ListRedact extends TagSupport {
	private static final Logger logger1 = Logger.getLogger(SignUp.class);

	private static final long serialVersionUID = 1L;
	/**
	 * format date
	 */
	private SimpleDateFormat sdf;
	/**
	 * list Advertisement
	 */
	private List<Advertisement> listAdvert;

	public ListRedact() {
		sdf = new SimpleDateFormat("MM dd,yyyy ");
	}

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		try {
			JspWriter out = pageContext.getOut();
			for (int i = 0; i < listAdvert.size(); i++) {
				out.print("<div class ='name_content id"
						+ listAdvert.get(i).getIdAdvertisement() + "'>"
						+ "<input id ='hidden' type='hidden' value='"
						+ listAdvert.get(i).getIdAdvertisement()
						+ "'><table class='tabl'>");
				out.print("<tr><td id ='rubricEd'>"
						+ listAdvert.get(i).getRubric() + "</td> </tr>"
						+ "<tr><td id = 'nameUserEd' width='90%'>"
						+ listAdvert.get(i).getLogin().getNameUser()
						+ " </td> ");
				if (login != null) {
					if (login.getIdLogin() == listAdvert.get(i).getLogin()
							.getIdLogin()) {
						out.print("<td> <button class='deleteAdvertism' type='button' value= "
								+ listAdvert.get(i).getIdAdvertisement()
								+ ">удалить</button> "
								+ "<button class='redactAdvertism' type='button' value= "
								+ listAdvert.get(i).getIdAdvertisement()
								+ "> редактировать</button>" + "</td> </tr>");
					} else {
						out.print("</tr>");
					}
				}
				Date resultdate = new Date(listAdvert.get(i).getModifiedDate());
				out.print("<tr><td>" + sdf.format(resultdate) + "</td> </tr>");
				out.print("<tr><td id = 'titleEd'>"
						+ listAdvert.get(i).getTitle() + " </td> </tr>");
				out.print("<tr><td id = 'textrEd'>"
						+ listAdvert.get(i).getText() + " </td> </tr>");

				out.print("</table> </div>");

			}

		} catch (IOException ioException) {
			logger1.error("Error: " + ioException.getMessage());
		}
		return SKIP_BODY;
	}

	/**
	 * @return the listAdvert
	 */
	public List<Advertisement> getListAdvert() {
		return listAdvert;
	}

	/**
	 * @param listAdvert
	 *            the listAdvert to set
	 */
	public void setListAdvert(List<Advertisement> listAdvert) {
		this.listAdvert = listAdvert;
	}

}
