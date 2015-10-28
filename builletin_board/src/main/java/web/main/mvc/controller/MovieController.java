package web.main.mvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
	 public static final String PAGE_INDEX = "index";
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
	/**
	 * registration user on site add Login object in base table login
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/signlogin", method = RequestMethod.POST)
	public String getSingUp(@Valid Login login, HttpServletRequest request,
			HttpServletResponse response, BindingResult bindingResult) {
		HttpSession session = request.getSession(true);
		String nameCon = (String) session.getAttribute("nameContent");
		if (bindingResult.hasErrors()){
			return  nameCon;
		}
		else{
		signupInit.addBaseLogin( login, request, response);
		return   nameCon;
		}
	}

	/**
	 * initialization user on site
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initlogin", method = RequestMethod.POST)
	public String getSInitLogin( Login login, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String nameCon = (String) session.getAttribute("nameContent");
		 signupInit.initialized(login,request, response);
		 return  nameCon;
	
	}

	// redirect page list advertisement
	@RequestMapping(value = "/listAds", method = RequestMethod.GET)
	public String getListAdvertisementService(HttpServletRequest request, Model model) {
		List<Advertisement> listAdver = impl.getAdvertisement();
		HttpSession session = request.getSession(true);
		session.setAttribute("listAdver", listAdver);
		return "ListAds";
	}

	// search Advertisement by name user , rubric
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = "application/json ; charset=UTF-8")
	public @ResponseBody List<Advertisement> getListSerchAdvertisementByNameRubric(
			RequestParamSerch requstParam, HttpServletRequest request) {
		   Login login = (Login) request.getSession(true).getAttribute("login");
		return searchServise.serchCreteriaNameUserRubric(login, requstParam,
				impl);

	}

	// delete advertisement by id
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String getProfile(@PathVariable("id") int id) {
		impl.delateAdvertisementByID(id);
		return "ok";
	}

	//   Editor.jsp add list user  advertisement  
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public String getEditor(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		List<Advertisement> listAdver = null;
		if (login != null) {
			listAdver = impl.getAdvertisementByIDLogin(login);
			model.addAttribute("myListAdvertisment", listAdver);
			
		}
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
		 logger.info("test myList");
		 logger.info( login==null);
		if (login != null) {
			listAdver = impl.getAdvertisementByIDLogin(login);
			session.setAttribute("myListAdvertisment", listAdver);
		}

		return listAdver;
	}

	// update Advertisement
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody String getUpdate(@Valid Advertisement advartisement,
			HttpServletRequest request,BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return "notCorrect";
		}else{
		HttpSession session = request.getSession(true);
		Login login = (Login) session.getAttribute("login");
		advartisement.setLogin(login);
		advartisement.setModifiedDate(new Date().getTime());
		impl.updateAdvertisement(advartisement);
		return "ok";
		}
	}

	// create Advertisement
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody String getcreate(@Valid Advertisement advartisement,
			HttpServletRequest request,BindingResult bindingResult) {
		if (bindingResult.hasErrors()){
			return "notCorrect";
		}
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
