package core.dao.service.advertisement;

import java.util.List;

import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;

public interface IAdvertisementService {
	/**
	 * 
	 * @return advertisement by id 
	 */
	public Advertisement getAdvertisementByID(int l);

	/**
	 * 
	 * @return list all Advertisement
	 */
	public List<Advertisement> getAdvertisement();

	/**
	 * new advertisement add in database
	 * @return id advertisement
	 */
	public int addAdvertisement(Advertisement advert);

	/**
	 * delete Advertisement in base
	 * @param Advertisement
	 * 
	 */
	public void delateAdvertisement(Advertisement advertis);

	/**
	 * update Advertisement in base
	 */
	public Advertisement updateAdvertisement(Advertisement advertis);

	/**
	 * @param id author
	 * @return Advertisement by id author
	 */
	public List<Advertisement>  getAdvertisementByIDLogin(Login login);
    
	
	/**
	 * @param  name rubric 
	 * @return Advertisement by name rubric
	 */
	public List<Advertisement>  getAdvertisementByRubric(String rubric);
	/**
	 * delete advertisement  by id 
	 * @param id
	 */
	public void delateAdvertisementByID(int id);
	 /* 
	 * @param login login user
	 * @param rubric name  search rubric 
	 * @return
	 */
	public List<Advertisement> getAdvertisementByIDLoginRubric(Login login,String rubric);
	/**
	 *  search list Advertisement  by name User name
	 * @param nameUsery name user 
	 * @return  list advertisement
	 */
	
	public List<Advertisement> getAdvertisementByNameUser(String nameUser);
	/**
	 *  search list Advertisement  by name User name and  rubric
	 * @param nameUsery name user 
	 * @param  rubric  name rubric advertisement
	 * @return  list advertisement
	 */
	
	public List<Advertisement> getAdvertisementByNameUserRubic(String nameUser, String rubric);
}
