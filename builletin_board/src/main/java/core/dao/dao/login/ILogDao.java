package core.dao.dao.login;

import java.util.List;


import core.dao.model.login.Login;

/**
 * 
 * @author Sergey interface access data login
 */

public interface ILogDao {

	/**
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
	 * 
	 * @return
	 */
	public int addLogin(Login login);

	/**
	 * delate login in base
	 * 
	 * @param login
	 * @return
	 */
	public void delateLogin(Login login);

	/**
	 * update login in base
	 */
	public Login updateLogin(Login login);

	/**
	 * 
	 * @return Login by name login
	 */
	public Login getByNameLogin(String name);

	/**
	 * 
	 * @return list Login by name user
	 */
	public List<Login> getByNameUser(String nameUser);

}
