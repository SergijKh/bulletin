package core.com.board.search.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.com.board.requestparam.model.RequestParamSerch;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.AdvertisementService;
import core.dao.service.advertisement.IAdvertisementService;

@Component
public class Search implements ISearching {
	private static final Logger logger1 = Logger.getLogger(Search.class);

	private Properties config;
	private InputStream inputStream;

	public Search() {
		this.config = new Properties();
		this.inputStream = getClass().getClassLoader().getResourceAsStream(
				"ajax.properties");

	}

	@Override
	public List<Advertisement> serchCreteriaNameUserRubric(
			Login login, RequestParamSerch serch,
			IAdvertisementService service) {
		try {
			config.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger1.info(e);
		}
	
		List<Advertisement> listAdvertis = null;
		
		if (serch.getSelect_rubric().equals(config.getProperty("all"))
				&& serch.getMyAds().equals(config.getProperty("checkbox1"))
				&& login != null) {
			logger1.info(config.getProperty("all"));
			listAdvertis = service.getAdvertisementByIDLogin(login);
		} else if (!(serch.getSelect_rubric().equals(config.getProperty("all")))
				&& serch.getMyAds().equals(config.getProperty("checkbox1"))
				&& login != null) {
			logger1.info("rubric my all" );
			listAdvertis = service.getAdvertisementByIDLoginRubric(login,
					serch.getSelect_rubric());

		} else if ((serch.getSelect_rubric().equals(config.getProperty("all")))
				&& serch.getMyAds().equals(config.getProperty("checkbox0"))
				&& !(serch.getNameUser().equals("null"))) {
			listAdvertis = service.getAdvertisementByNameUser(serch
					.getNameUser());
			logger1.info("nameUser all");
		} else if (!(serch.getSelect_rubric().equals(config.getProperty("all")))
				&& serch.getMyAds().equals(config.getProperty("checkbox0"))
				&& (!(serch.getNameUser().equals("null")))) {
			listAdvertis = service.getAdvertisementByNameUserRubic(
					serch.getNameUser(), serch.getSelect_rubric());
			logger1.info(!(serch.getNameUser().equals("null")));
		} else if (!(serch.getSelect_rubric().equals(config.getProperty("all")))
				&& serch.getMyAds().equals(config.getProperty("checkbox0"))) {
			listAdvertis = service.getAdvertisementByRubric(serch
					.getSelect_rubric());
			logger1.info(" rubric");
		}
		return listAdvertis;
	}

}
