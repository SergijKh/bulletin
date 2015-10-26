package core.dao.service.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import core.dao.dao.advertisement.IAdvertisementDao;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;

@Service
public class AdvertisementService implements IAdvertisementService {
	@Autowired
	IAdvertisementDao daoAdvertis;

	@Override
	public Advertisement getAdvertisementByID(int l) {
		return daoAdvertis.getAdvertisementByID(l);
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
	public List<Advertisement> getAdvertisementByIDLogin(Login login) {
		return daoAdvertis.getAdvertisementByIDLogin(login);
	}

	@Override
	public List<Advertisement> getAdvertisementByRubric(String rubric) {
		return daoAdvertis.getAdvertisementByRubric(rubric);
	}

	@Override
	public void delateAdvertisementByID(int id) {
		daoAdvertis.delateAdvertisementByID(id);
	}

	@Override
	public List<Advertisement> getAdvertisementByIDLoginRubric(Login login,
			String rubric) {

		return daoAdvertis.getAdvertisementByIDLoginRubric(login, rubric);
	}

	@Override
	public List<Advertisement> getAdvertisementByNameUser(String nameUser) {

		return daoAdvertis.getAdvertisementByNameUser(nameUser);
	}

	@Override
	public List<Advertisement> getAdvertisementByNameUserRubic(String nameUser,
			String rubric) {

		return daoAdvertis.getAdvertisementByNameUserRubic(nameUser, rubric);
	}

}
