package core.com.board.search.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import core.com.board.requestparam.model.RequestParamSerch;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.IAdvertisementService;

//search by the different criteria 
public interface ISearching {

	/**
	 * search advertisement by criteria
	 * 
	 * @param serch
	 *            RequestParamSerch
	 * @see RequestParamSerch
	 * @return
	 */
	public List<Advertisement> serchCreteriaNameUserRubric(
			Login login, RequestParamSerch serch,
			IAdvertisementService service);

}
