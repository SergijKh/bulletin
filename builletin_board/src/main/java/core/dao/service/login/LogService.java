package core.dao.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.dao.login.ILogDao;
import core.dao.model.login.Login;


@Service
public class LogService implements ILogService{
  

	@Autowired
	ILogDao daoImpl;
	@Override
	public Login getLoginByIDLogin(int l) {
		return daoImpl.getLoginByIDLogin(l);
	}

	@Override
	public List<Login> getAllLogin() {
		return daoImpl.getAllLogin();
	}

	@Override
	public int addLogin(Login login) {
		return daoImpl.addLogin(login);
	}

	@Override
	public void delateLogin(Login login) {
		daoImpl.delateLogin(login);
	}

	@Override
	public Login updateLogin(Login login) {
		return daoImpl.updateLogin(login) ;
	}
	 /**
	 * @return the daoImpl
	 */
	public ILogDao getDaoImpl() {
			return daoImpl;
		}

	/**
	 * @param daoImpl the daoImpl to set
	 */
	public void setDaoImpl(ILogDao daoImpl) {
			this.daoImpl = daoImpl;
		}

	@Override
	public Login getByNameLogin(String nameLogin) {
		return daoImpl.getByNameLogin(nameLogin);
		}

	@Override
	public List<Login> getByNameUser(String nameUser) {
		return daoImpl.getByNameUser(nameUser);
	}
	
}
