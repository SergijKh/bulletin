package core.dao.service.advertisement;

import java.util.List;

import core.dao.model.advertisement.Advertisement;

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
	public List<Advertisement>  getAdvertisementByIDLogin(int idlogin);
    
	
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
}
