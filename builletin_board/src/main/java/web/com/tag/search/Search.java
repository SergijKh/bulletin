package web.com.tag.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import web.com.tag.SignUp;

public class Search extends TagSupport{
	private static final Logger logger1 = Logger.getLogger(Search.class);

	private static final long serialVersionUID = 1L;
    private static final String[] ARRAY_RUBRIC = {"все","продажа","покупка","аренда","услуги","знакомства"};
	private Properties config;
	private InputStream inputStream;
	public  Search(){
		   this.config = new Properties();
		   this.inputStream = getClass().getClassLoader()
								.getResourceAsStream("redirect.properties");
	}
	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			config.load(inputStream);
			out.print(" <div id='serchAds'> <form id ='formx' action='javascript:void(null);' onsubmit='showValues()' method='POST'><table id='tabl'>"
				
	                   +"<tr>  <th colspan='2'> поиск</th> </tr><tr>"
					+ "<td> рубрика :</td><td><select id='selectRubric' size='1' name='select_rubric'>");
			  for (int i =0; i < ARRAY_RUBRIC.length ;i++){
					out.print(" <option value="+ARRAY_RUBRIC[i]+">"+ARRAY_RUBRIC[i]+"</option>");
                }
    
            out.print("</select></td> </tr><tr><td>  имя :</td><td><input  type='text' id ='testtextfield' size='15' name='nameUser' /></td></tr>"
					+ "<tr><td>мои объявления:</td><td><input  type='checkbox' id='testcheckbox' name='myAds'  value='0'/></td>"
					+ "</tr><tr><td><input type='submit' name='searchButton'  value='поиск'/></td> </tr></table></form></div> ");

		} catch (IOException ioException) {
			logger1.error("Error: " + ioException.getMessage());
		}
		return SKIP_BODY;
	}

	
	
}


