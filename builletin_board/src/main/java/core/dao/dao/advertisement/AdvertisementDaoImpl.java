package core.dao.dao.advertisement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.login.ILogService;

@Repository
public class AdvertisementDaoImpl implements IAdvertisementDao {
	@Autowired
	private HibernateTemplate hibernateT;
	@Autowired
	ILogService service;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Advertisement getAdvertisementByID(int l) {
		if (l <= 0)
			throw new IllegalArgumentException();
		Advertisement advertis = null;
		advertis = hibernateT.get(Advertisement.class, l);
		return advertis;

	}

	@Override
	@Transactional(readOnly = true)
	public List<Advertisement> getAdvertisement() {
		@SuppressWarnings("unchecked")
		List<Advertisement> listAdvertisement = (List<Advertisement>) hibernateT
				.find("from Advertisement");

		return listAdvertisement;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int addAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		int idAdvertise = (int) hibernateT.save(advertis);
		return idAdvertise;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delateAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		hibernateT.delete(advertis);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Advertisement updateAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		return hibernateT.merge(advertis);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Advertisement> getAdvertisementByIDLogin(Login login) {
		if (login == null) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.login= ?", login);

		return advertisList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Advertisement> getAdvertisementByRubric(String rubric) {
		if (rubric == null || rubric.equals("")) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.rubric= ?", rubric);

		return advertisList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delateAdvertisementByID(int id) {
		Advertisement advert = null;
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		advert = hibernateT.load(Advertisement.class, id);
		hibernateT.delete(advert);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Advertisement> getAdvertisementByIDLoginRubric(Login login,
			String rubric) {
		if (login == null && rubric == null && rubric.equals("")) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.login= ? and p.rubric= ? ",
						login, rubric);

		return advertisList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Advertisement> getAdvertisementByNameUser(String nameUser) {
		if (nameUser == null && nameUser.equals("")) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.login.nameUser = ?)",
						nameUser);

		return advertisList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Advertisement> getAdvertisementByNameUserRubic(String nameUser,
			String rubric) {
		if (nameUser == null && nameUser.equals("") && rubric == null
				&& rubric.equals("")) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.rubric=? and p.idLogin=(select j.idLogin from Login j where j.nameUser = ?)",
						rubric, nameUser);

		return advertisList;
	}

}
