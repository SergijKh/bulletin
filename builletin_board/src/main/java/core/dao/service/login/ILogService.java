package core.dao.service.login;

import java.util.List;

import core.dao.model.login.Login;


/**
 * 
 * @author Sergey
 *service access data login
 */
public interface ILogService {	 /**
	  * 
	  * @return login by id login
	  */
	public Login getLoginByIDLogin(int l);
	/**
	 * 
	 * @return list all login 
	 */
	public List<Login> getAllLogin();
	/**
	 * new login add in database
	 * @param user 
	 * @return
	 */
	public int addLogin(Login login);
	/**
	 * delate login in base
	 * @param login
	 * @return
	 */
	public void delateLogin(Login login);
	/**
	 * update login in base
	 */
	public Login updateLogin(Login login );
	/**
	 * 
	 * @param nameLogin login name 
	 * @return login User
	 */
	public Login getByNameLogin(String nameLogin);
	/**
	 * 
	 * @param nameUser name user
	 * @return list  Login with name  nameUser
	 */
	public List<Login> getByNameUser(String nameUser);
}