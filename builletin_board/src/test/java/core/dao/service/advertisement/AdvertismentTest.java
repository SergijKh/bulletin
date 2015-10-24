package core.dao.service.advertisement;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.Before;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.AdvertisementService;
import core.dao.service.login.LogService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/test/java/spring/context/mvc-dispatcher-servlet.xml" })
@Transactional
public class AdvertismentTest {
	@Autowired
	AdvertisementService impl;
	@Autowired
	LogService logServic;
	// list logins
	private ArrayList<Advertisement> testAdvertisementList;
	private ArrayList<Login> listLogin;
	private ArrayList<Advertisement> baseAdvertisement;

	@Before
	public void init() {
		Login login = null;
		baseAdvertisement = new ArrayList<Advertisement>();
		testAdvertisementList = new ArrayList<Advertisement>();
		listLogin = new ArrayList<Login>();
		for (int i = 0; i < 6; i++) {
			login = new Login("login" + i, "pasword" + i, "nameUser" + i);
			listLogin.add(login);
			System.out.println("sjakd");
			logServic.addLogin(login);
		}

		String[] rubricArray = { "продажа", "покупка", "аренда", "слуги",
				"знакомства" };

		String rubric = "", title = "", text = "";
		long date = new Date().getTime();
		Advertisement advertis = null;
		for (int i = 0; i < 30; i++) {
			rubric = rubricArray[((int) (Math.random() * ((rubricArray.length)-1)))];
			title = "pasword" + i + i;
			for (int j = 0; j < i; j++) {
				text += "text" + j + "email.com  ";
			}
			advertis = new Advertisement(rubric, title, text, date);
			advertis.setLogin(listLogin.get(((int) (Math.random() * (listLogin
					.size()-1)))));
			testAdvertisementList.add(advertis);
			text = "";
		}

	}

	@After
	public void destroy() {
		testAdvertisementList = null;
		/*if (baseAdvertisement.size() > 0) {
			for (int m = 0; m < baseAdvertisement.size(); m++) {
				impl.delateAdvertisement(baseAdvertisement.get(m));
			}
		}

		for (int i = 0; i < listLogin.size(); i++) {
			logServic.delateLogin(listLogin.get(i));
		}*/
	}

	 @Ignore
	@Test
	public void testAddAdvertisement() {

		int id = impl.addAdvertisement(testAdvertisementList.get(9));

		Advertisement advertis = impl.getAdvertisementByID(id);
		baseAdvertisement.add(advertis);

		assertNotNull(" is null.", advertis);
		assertEquals(id, advertis.getIdAdvertisement());
		assertEquals(advertis.getRubric(), testAdvertisementList.get(9)
				.getRubric());
		assertEquals(advertis.getTitle(), testAdvertisementList.get(9)
				.getTitle());
		assertEquals(advertis.getText(), testAdvertisementList.get(9).getText());
		assertEquals(advertis.getLogin(), testAdvertisementList.get(9)
				.getLogin());
		assertEquals(advertis.getLogin().getLogin(),
				testAdvertisementList.get(9).getLogin().getLogin());
		assertEquals(advertis.getLogin().getNameUser(), testAdvertisementList
				.get(9).getLogin().getNameUser());
		assertEquals(advertis.getLogin().getPassword(), testAdvertisementList
				.get(9).getLogin().getPassword());

	}

	// @Ignore
	@Test
	public void testAddListAdvertisement() {

		for (int i = 0; i < testAdvertisementList.size(); i++) {
			impl.addAdvertisement(testAdvertisementList.get(i));
		}
		ArrayList<Advertisement> listAdvert = (ArrayList<Advertisement>) impl
				.getAdvertisement();
		assertNotNull("list is null", listAdvert);
		if (listAdvert != null) {
			for (int j = 0; j < listAdvert.size(); j++) {
				baseAdvertisement.add(listAdvert.get(j));
				assertEquals(listAdvert.get(j).getRubric(),
						testAdvertisementList.get(j).getRubric());
				assertEquals(listAdvert.get(j).getTitle(),
						testAdvertisementList.get(j).getTitle());
				assertEquals(listAdvert.get(j).getText(), testAdvertisementList
						.get(j).getText());
				assertEquals(listAdvert.get(j).getLogin(),
						testAdvertisementList.get(j).getLogin());
				assertEquals(listAdvert.get(j).getLogin().getLogin(),
						testAdvertisementList.get(j).getLogin().getLogin());
				assertEquals(listAdvert.get(j).getLogin().getNameUser(),
						testAdvertisementList.get(j).getLogin().getNameUser());
				assertEquals(listAdvert.get(j).getLogin().getPassword(),
						testAdvertisementList.get(j).getLogin().getPassword());

			}
		}

	}

   @Ignore
	@Test
	public void testupdateAdvertisement() {

		int id = impl.addAdvertisement(testAdvertisementList.get(1));
		Advertisement advert = testAdvertisementList.get(1);
		advert.setRubric("update");
		advert.setTitle("update");
		advert.setText("update");
		Advertisement update = impl.updateAdvertisement(advert);
		baseAdvertisement.add(update);
		assertEquals(advert, update);
		assertNotNull("list is null", update);
	}
  // @Ignore
	@Test
	public void testGetAdvertisementByRubric() {
		String buy = "покупка";
		for (int i = 0; i < testAdvertisementList.size(); i++) {
			impl.addAdvertisement(testAdvertisementList.get(i));
			baseAdvertisement.add(testAdvertisementList.get(i));
		}
		List<Advertisement> listRubric = impl.getAdvertisementByRubric(buy);
		assertNotNull("list is null", listRubric);
		for (int i = 0; i < listRubric.size(); i++) {
			assertEquals(listRubric.get(i).getRubric(), buy);
		}

	}
    @Ignore
	@Test
	public void testGetAdvertisementByNameUser() {
		String nameUser = "nameUser" + 1;
		for (int i = 0; i < testAdvertisementList.size(); i++) {
			impl.addAdvertisement(testAdvertisementList.get(i));
			baseAdvertisement.add(testAdvertisementList.get(i));
		}
		ArrayList<Login> logins = (ArrayList<Login>) logServic
				.getByNameUser(nameUser);
		assertNotNull("list is null", logins);
		for (int i = 0; i < logins.size(); i++) {
			@SuppressWarnings("unchecked")
			Set<Advertisement> setAdvert = (Set<Advertisement>) logins
					.get(i).getAdvertis();
			assertNotNull("list is null", setAdvert);
			Iterator<Advertisement> itr = setAdvert.iterator();
	      	while ( itr.hasNext()) {
				assertEquals(itr.next().getLogin().getIdLogin(), logins
						.get(i).getIdLogin());

			}
		}

	}
}
