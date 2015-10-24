package core.dao.dao.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.dao.login.ILogDao;
import core.dao.model.login.Login;


/**
 * 
 * @author Sergey class LogDaoImpl access data in login
 */

@Repository
@Transactional
public class LogDaoImpl implements ILogDao {
	@Autowired
	private HibernateTemplate hibernateT;

	@Override
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Login getLoginByIDLogin(int l) {
		if (l <= 0)
			throw new IllegalArgumentException();
		Login login = null;
		login = hibernateT.get(Login.class, l);
		return login;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Login> getAllLogin() {
		List<Login> listLogin = (List<Login>)hibernateT.find("from Login");
		return listLogin;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public int addLogin(Login login) {
		if (login == null)
			throw new IllegalArgumentException();
		int idLog = (int) hibernateT.save(login);
		return idLog;
	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public void delateLogin(Login login) {
		if (login == null)
			throw new IllegalArgumentException();
		hibernateT.delete(login);

	}

	@Override
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
	public Login updateLogin(Login login) {
		if (login == null)
			throw new IllegalArgumentException();
		return hibernateT.merge(login);
	}

	/**
	 * @return the HibernateTemplate
	 */
	public HibernateTemplate getHibernateT() {
		return hibernateT;
	}

	/**
	 * @param em
	 * the new  HibernateTemlate to set
	 */
	public void setHibernateT(HibernateTemplate hibernateT) {
		this.hibernateT = hibernateT;
	}
	 @Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public Login getByNameLogin(String name) {
		@SuppressWarnings("unchecked")
		List<Login> logins = (List<Login>) hibernateT.find(
				"from Login p where p.login = ?", name);
		Login login = null;
		if (logins.size() > 0) {
			for (int i = 0; i < logins.size(); i++) {
				login = logins.get(i);
			}
		}
		return login;
	}

	 @SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
		@Override
	public List<Login> getByNameUser(String name) {
		
			if(name.equals("")||name ==null){
				throw new IllegalArgumentException();
			}
		 List<Login> logins = (List<Login>) hibernateT.find(
					"from Login p where p.nameUser = ?", name);
			
			return logins;
	}
}
