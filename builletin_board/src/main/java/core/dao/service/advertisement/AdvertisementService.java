package core.dao.service.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.dao.advertisement.IAdvertisementDao;
import core.dao.dao.login.ILogDao;
import core.dao.model.advertisement.Advertisement;
@Service
public class AdvertisementService implements IAdvertisementService {
	@Autowired
	   IAdvertisementDao daoAdvertis;
	
	@Override
	public Advertisement getAdvertisementByID(int l) {
		return  daoAdvertis.getAdvertisementByID(l);
	}

	@Override
	public List<Advertisement> getAdvertisement() {
		return daoAdvertis.getAdvertisement();
	}

	@Override
	public int addAdvertisement(Advertisement advert) {
		return daoAdvertis.addAdvertisement(advert);
	}

	@Override
	public void delateAdvertisement(Advertisement advertis) {
		daoAdvertis.delateAdvertisement(advertis);

	}

	@Override
	public Advertisement updateAdvertisement(Advertisement advertis) {
		return daoAdvertis.updateAdvertisement(advertis);
	}

	@Override
	public List<Advertisement> getAdvertisementByIDLogin(int idlogin) {
		return daoAdvertis.getAdvertisementByIDLogin(idlogin);
	}

	@Override
	public List<Advertisement> getAdvertisementByRubric(String rubric) {
		return daoAdvertis.getAdvertisementByRubric(rubric);
	}

	@Override
	public void delateAdvertisementByID(int id) {
		daoAdvertis.delateAdvertisementByID(id);
	}

	

}
