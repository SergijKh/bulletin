package core.dao.dao.advertisement;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;

@Repository
public class AdvertisementDaoImpl implements IAdvertisementDao {
	@Autowired
	private HibernateTemplate hibernateT;

	@Override
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Advertisement getAdvertisementByID(int l) {
		if (l <= 0)
			throw new IllegalArgumentException();
		Advertisement advertis = null;
		advertis = hibernateT.get(Advertisement.class, l);
		return advertis;

	}

	@Override
	 @Transactional( readOnly=true)
	public List<Advertisement> getAdvertisement() {
		@SuppressWarnings("unchecked")
		List<Advertisement> listAdvertisement = (List<Advertisement>) hibernateT
				.find("from Advertisement");
		
		return listAdvertisement;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public int addAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		int idAdvertise = (int) hibernateT.save(advertis);
		return idAdvertise;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delateAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		hibernateT.delete(advertis);

	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public Advertisement updateAdvertisement(Advertisement advertis) {
		if (advertis == null)
			throw new IllegalArgumentException();
		return hibernateT.merge(advertis);
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Advertisement> getAdvertisementByIDLogin(int idLogin) {
		if (idLogin <= 0) {
			throw new IllegalArgumentException();
		}
		@SuppressWarnings("unchecked")
		List<Advertisement> advertisList = (List<Advertisement>) hibernateT
				.find("from Advertisement p where p.idLogin= ?", idLogin);

		return advertisList;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
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
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delateAdvertisementByID(int id) {
		Advertisement advert = null ;
		if(id <= 0 ){
			 throw new IllegalArgumentException();
		 }
		 advert = hibernateT.load(Advertisement.class, id);
		 hibernateT.delete(advert);
	}


	

}
