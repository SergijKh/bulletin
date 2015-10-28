package core.dao.search;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import core.com.board.requestparam.model.RequestParamSerch;
import core.com.board.search.action.Search;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.AdvertisementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/test/java/spring/context/mvcfirst-dispatcher-servlet.xml",
	"file:src/test/java/spring/context/mvc-dispatcher-servlet.xml" })
public class TestSearch
 {
	@Mock
	@Autowired
	private AdvertisementService impl;

	
	@Autowired
	private  Search search ;
	  
	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);


	

	}

	

	@Test
	//test all advertisement  user
	public void testSearchListUserAll() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "все" , "title" + i,
					"text" + i));
		}
		 Login login =  new Login();
		when(impl.getAdvertisementByIDLogin(login)).thenReturn(listAdvert); 
	 RequestParamSerch  searchP  = new  RequestParamSerch("все","null","1");
         List<Advertisement>  listAdver =  search.serchCreteriaNameUserRubric(login, searchP, impl);
		assertNotNull(listAdver);
		verify(impl, times(1)).getAdvertisementByIDLogin(login);
		verifyZeroInteractions(impl);
	}

	@Test
	//test all advertisement  user
	public void testSearchListRubricUser() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "продажа" , "title" + i,
					"text" + i));
		}
		 Login login =  new Login(1,"login","password","nameUser");
	 RequestParamSerch  searchP  = new  RequestParamSerch("продажа","null","1");
	 when(impl.getAdvertisementByIDLoginRubric(login,
			 searchP.getSelect_rubric())).thenReturn(listAdvert); 
         List<Advertisement>  listAdver =  search.serchCreteriaNameUserRubric(login, searchP, impl);
		assertNotNull(listAdver);
		verify(impl, times(1)).getAdvertisementByIDLoginRubric(login,
				 searchP.getSelect_rubric());
		verifyZeroInteractions(impl);
	}
	@Test
	//test all advertisement  user
	public void testSearchLisByNameUser() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "продажа" , "title" + i,
					"text" + i));
		}
		 Login login =  new Login(1,"login","password","nameUser");
	 RequestParamSerch  searchP  = new  RequestParamSerch("все","nameUser","0");
	 when(impl.getAdvertisementByNameUser(searchP.getNameUser())).thenReturn(listAdvert); 
         List<Advertisement>  listAdver =  search.serchCreteriaNameUserRubric(login, searchP, impl);
		assertNotNull(listAdver);
		verify(impl, times(1)).getAdvertisementByNameUser(searchP.getNameUser());
		verifyZeroInteractions(impl);
	}

	@Test
	//test all advertisement name  user and rubric 
	public void testSearchLisByNameUserRubric() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "продажа" , "title" + i,
					"text" + i));
		}
		 Login login =  new Login(1,"login","password","nameUser");
	 RequestParamSerch  searchP  = new  RequestParamSerch("продажа","nameUser","0");
	 when(impl.getAdvertisementByNameUserRubic(
				searchP.getNameUser(), searchP.getSelect_rubric())).thenReturn(listAdvert); 
         List<Advertisement>  listAdver =  search.serchCreteriaNameUserRubric(login, searchP, impl);
		assertNotNull(listAdver);
		verify(impl, times(1)).getAdvertisementByNameUserRubic(
				searchP.getNameUser(), searchP.getSelect_rubric());
		verifyZeroInteractions(impl);
	}

	
	@Test
	//test all advertisement one  rubric 
	public void testSearchLisByrRubric() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "продажа" , "title" + i,
					"text" + i));
		}
		 Login login =  new Login(1,"login","password","nameUser");
	 RequestParamSerch  searchP  = new  RequestParamSerch("продажа","null","0");
	 when(impl.getAdvertisementByRubric(searchP
				.getSelect_rubric())).thenReturn(listAdvert); 
         List<Advertisement>  listAdver =  search.serchCreteriaNameUserRubric(login, searchP, impl);
		assertNotNull(listAdver);
		verify(impl, times(1)).getAdvertisementByRubric(searchP
				.getSelect_rubric());
		verifyZeroInteractions(impl);
	}
	
	
	
	
	


	

}
