package core.com.board.search.action;

import java.util.List;

import core.com.board.requestparam.model.RequestParamSerch;
import core.dao.model.advertisement.Advertisement;

//search by the different criteria 
public interface ISearching {
	
	public List<Advertisement> serchCreteriaNameUserRubric(RequestParamSerch serch);

}
