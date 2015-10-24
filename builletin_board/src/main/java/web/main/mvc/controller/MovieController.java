package web.main.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import core.com.board.initialised.SignUpInitController;
import core.com.board.requestparam.model.RequestParamSerch;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.IAdvertisementService;

/*
 * controller return 
 */

@Controller
@RequestMapping
public class MovieController {
	private static final Logger logger = Logger
			.getLogger(MovieController.class);
	final static int ID_IMAGE_EMPTY_AVATAR = 1;
	@Autowired
	SignUpInitController signupInit;
	@Autowired
	IAdvertisementService impl;

	/**
	 * registration user on site add Login object in base table  login
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/signlogin", method = RequestMethod.POST)
	public String getSingUp(HttpServletRequest request,
			HttpServletResponse response) {
		signupInit.addBaseLogin(request, response);
		HttpSession session = request.getSession(true);
		String nameCon = (String) session.getAttribute("nameContent");
		return nameCon;
	}

	/**
	 * initialization user on site
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initlogin", method = RequestMethod.POST)
	public String getSInitLogin(HttpServletRequest request,
			HttpServletResponse response) {
		signupInit.initialized(request, response);
		HttpSession session = request.getSession(true);
		String nameCon = (String) session.getAttribute("nameContent");
		return nameCon;
	}

	// redirect page list ads
	@RequestMapping(value = "/listAds", method = RequestMethod.GET)
	public String getService(HttpServletRequest request,Model model) {
		List <Advertisement> listAdver =  impl.getAdvertisement();
		model.addAttribute("listAdver", listAdver);
		return "ListAds";
	}

	// search Advertisement  by name user , rubric  
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces="application/json ; charset=UTF-8")
	 public @ResponseBody List<Advertisement> getPostBooking( RequestParamSerch requstParam){
			 //@RequestParam("select_rubric") String selectRubric,
			// @RequestParam("nameUser") String nameUser,	 @RequestParam("myAds") String myAds) {
		logger.info("nameUser "+requstParam.getNameUser()+",selectRubric  "+requstParam.getSelect_rubric()+"myAds "+requstParam.getMyAds());
		 List<Advertisement>  listAdver = null;
		if (requstParam.getSelect_rubric().equals("все")){
			 listAdver = impl.getAdvertisement();
		 }
		
		return listAdver;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String getProfile(@PathVariable("id") int id) {
		impl.delateAdvertisementByID(id);
		
         return "ok";
		
	}


}
