package core.dao.service.log;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import core.dao.model.login.Login;
import core.dao.service.login.LogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/test/java/spring/context/mvc-dispatcher-servlet.xml" })
@Transactional
public class LoginTest {

	@Autowired
	LogService impl;
	// list logins
	private ArrayList<Login> testLoginList;

	@Before
	public void init() {
		String login, password, nameUser;
		testLoginList = new ArrayList<Login>();
		for (int i = 0; i < 10; i++) {
			login = "name" + i;
			password = "pasword" + i + i;
			nameUser = "email" + i + "@gmail.com";
			testLoginList.add(new Login(login, password, nameUser));
		}

	}

	@After
	public void destroy() {
		testLoginList = null;
	}

	@Ignore
	@Test
	public void testAddLoginInBase() {
		Login log = new Login("name", "23gh", "nameUser");
		int id = impl.addLogin(log);
		Login logB = (Login) impl.getByNameLogin("name");
		impl.delateLogin(log);
		assertNotNull("Person list is null.", logB);
		assertEquals(id, logB.getIdLogin());
		assertEquals(log.getLogin(), logB.getLogin());
		assertEquals(log.getPassword(), logB.getPassword());
		assertEquals(log.getNameUser(), logB.getNameUser());
	}

	/**
		 *  
		 */
	@Test
	@Ignore
	public void testManyLoginIn() {
		for (int i = 0; i < testLoginList.size(); i++) {
			impl.addLogin(testLoginList.get(i));
		}
		ArrayList<Login> newList = (ArrayList<Login>) impl.getAllLogin();
		for (int i = 0; i < testLoginList.size(); i++) {
			assertEquals(testLoginList.get(i).getLogin(), newList.get(i)
					.getLogin());
			assertEquals(testLoginList.get(i).getPassword(), newList.get(i)
					.getPassword());
			impl.delateLogin(testLoginList.get(i));
		}
		assertEquals(testLoginList.size(), newList.size());

	}

	@Test
	@Ignore
	public void testGetIdLogin() {
		Login log = new Login("name", "23gh", "nameUser");
		int id = impl.addLogin(log);
		Login logB = impl.getLoginByIDLogin(id);
		impl.delateLogin(log);
		assertNotNull("Person list is null.", logB);
		assertEquals(id, logB.getIdLogin());
		assertEquals(log.getLogin(), logB.getLogin());
		assertEquals(log.getPassword(), logB.getPassword());
	}
}
