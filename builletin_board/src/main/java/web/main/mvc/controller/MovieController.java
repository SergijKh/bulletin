package web.main.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import core.com.board.search.action.ISearching;
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
	@Autowired
	ISearching searchServise;

	/**
	 * registration user on site add Login object in base table login
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
		return "redirect:" + nameCon;
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
		return "redirect:" + nameCon;
	}

	// redirect page list ads
	@RequestMapping(value = "/listAds", method = RequestMethod.GET)
	public String getService(HttpServletRequest request, Model model) {
		List<Advertisement> listAdver = impl.getAdvertisement();
		HttpSession session = request.getSession(true);
		session.setAttribute("listAdver", listAdver);
		return "ListAds";
	}

	// search Advertisement by name user , rubric
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json ; charset=UTF-8")
	public @ResponseBody List<Advertisement> getPostBooking(
			RequestParamSerch requstParam, HttpServletRequest request) {
		return searchServise.serchCreteriaNameUserRubric(request, requstParam,
				impl);

	}

	// delete advertisement by id
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String getProfile(@PathVariable("id") int id) {
		impl.delateAdvertisementByID(id);
		return "ok";
	}

	// redirect jsp page Editor.jsp
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String getEditor(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		List<Advertisement> listAdver = null;
		if (login != null) {
			listAdver = impl.getAdvertisementByIDLogin(login);
		}
		session.setAttribute("myListAdvertisment", listAdver);
		return "Editor";
	}

	// return all Advertisement user
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/myList", method = RequestMethod.POST, produces = "application/json ; charset=UTF-8")
	public @ResponseBody List<Advertisement> getListAdvertismentUser(
			HttpServletRequest request) {
		List<Advertisement> listAdver = new ArrayList<Advertisement>();
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			listAdver = impl.getAdvertisementByIDLogin(login);
			session.setAttribute("myListAdvertisment", listAdver);
		}

		return listAdver;
	}

	// update Advertisement
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String getUpdate(Advertisement advartisement,
			HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		advartisement.setLogin(login);
		advartisement.setModifiedDate(new Date().getTime());
		impl.updateAdvertisement(advartisement);
		return "ok";

	}

	// create Advertisement
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String getcreate(Advertisement advartisement,
			HttpServletRequest request) {
		logger.info("////////////" + advartisement.getText());
		logger.info("////////////" + advartisement.getRubric());
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			advartisement.setLogin(login);
			advartisement.setModifiedDate(new Date().getTime());
			impl.addAdvertisement(advartisement);
			return "ok";
		}
		return "error";
	}
}
