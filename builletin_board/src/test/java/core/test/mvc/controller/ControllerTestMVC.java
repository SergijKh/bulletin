package core.test.mvc.controller;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import web.main.mvc.controller.MovieController;
import core.com.board.requestparam.model.RequestParamSerch;
import core.com.board.search.action.ISearching;
import core.dao.model.advertisement.Advertisement;
import core.dao.model.login.Login;
import core.dao.service.advertisement.AdvertisementService;
import core.dao.service.advertisement.IAdvertisementService;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
		"file:src/test/java/spring/context/mvcfirst-dispatcher-servlet.xml",
		"file:src/test/java/spring/context/mvc-dispatcher-servlet.xml" })
public class ControllerTestMVC {
	private static final Logger logger = Logger
			.getLogger(MovieController.class);
	@Mock
	@Autowired
	private IAdvertisementService impl;
	@Mock
	HttpSession httpSession;
	@Mock
	private ISearching searchServise;
	@InjectMocks
	private MovieController indexController;
	@Mock
	private HttpServletRequest request;
	@Mock
	protected MockHttpSession mockSession;
	private MockMvc mockMvc;

	@Before
	public void setup() {

		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
	}

	@Test
	public void testListAdvertisementService() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 0; i < 10; i++) {
			listAdvert.add(new Advertisement(i, "rubric" + i, "title" + i,
					"text" + i));
		}

		when(impl.getAdvertisement()).thenReturn(listAdvert);

		@SuppressWarnings("unchecked")
		List<Advertisement> sessionList = (List<Advertisement>) mockMvc
				.perform(get("/listAds")).andExpect(status().isOk())
				.andExpect(view().name("ListAds")).andReturn().getRequest()
				.getSession().getAttribute("listAdver");
		assertNotNull(sessionList);
		for (int i = 0; i < sessionList.size(); i++) {
			assertEquals(sessionList.get(i).getRubric(), listAdvert.get(i)
					.getRubric());
		}
		verify(impl, times(1)).getAdvertisement();
		verifyZeroInteractions(impl);
	}

	@Test
	// test method ListSerchAdvertisementByNameRubric by name rubric
	public void testListSerchAdvertisementByNameRubric() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 1; i < 3; i++) {
			listAdvert.add(new Advertisement(i, "rubric", "title" + i, "text"
					+ i));
		}

		// RequestParamSerch search = new RequestParamSerch("rubric","null",
		// "0");
		when(
				searchServise.serchCreteriaNameUserRubric(
						Mockito.any(Login.class),
						Mockito.any(RequestParamSerch.class),
						Mockito.any(AdvertisementService.class))).thenReturn(
				listAdvert);
		this.mockMvc
				.perform(
						post("/search").param("select_rubric", "rubric")
								.param("nameUser", "null").param("myAds", "0"))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								IntegrationTestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].idAdvertisement", is(1)))
				.andExpect(jsonPath("$[0].rubric", is("rubric")))
				.andExpect(jsonPath("$[0].title", is("title1")))
				.andExpect(jsonPath("$[1].idAdvertisement", is(2)))
				.andExpect(jsonPath("$[1].rubric", is("rubric")))
				.andExpect(jsonPath("$[1].title", is("title2")));

		ArgumentCaptor<Login> dtoLogin = ArgumentCaptor.forClass(Login.class);
		ArgumentCaptor<RequestParamSerch> dtoSearch = ArgumentCaptor
				.forClass(RequestParamSerch.class);
		ArgumentCaptor<AdvertisementService> dtoAdvertisementService = ArgumentCaptor
				.forClass(AdvertisementService.class);
		verify(searchServise, times(1)).serchCreteriaNameUserRubric(
				dtoLogin.capture(), dtoSearch.capture(),
				dtoAdvertisementService.capture());
		verifyZeroInteractions(searchServise);

	}

	@Test
	// test method ListSerchAdvertisementByNameRubric by name rubric
	public void testgetListAdvertismentUser() throws Exception {
		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 1; i < 3; i++) {
			listAdvert.add(new Advertisement("rubric" + i, "title" + i, "text"
					+ i));
		}

		Login login = new Login("login", "password", "nameUser");
		when(impl.getAdvertisementByIDLogin(Mockito.any(Login.class)))
				.thenReturn(listAdvert);
		this.mockMvc
				.perform(
						post("/myList").contentType(
								MediaType.APPLICATION_FORM_URLENCODED)
								.sessionAttr("login", login))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(
								IntegrationTestUtil.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].rubric", is("rubric1")))
				.andExpect(jsonPath("$[0].title", is("title1")))
				.andExpect(jsonPath("$[0].text", is("text1")))
				.andExpect(jsonPath("$[1].rubric", is("rubric2")))
				.andExpect(jsonPath("$[1].title", is("title2")))
				.andExpect(jsonPath("$[1].text", is("text2")));

		ArgumentCaptor<Login> dtoCaptor = ArgumentCaptor.forClass(Login.class);
		verify(impl, times(1)).getAdvertisementByIDLogin(dtoCaptor.capture());
		verifyNoMoreInteractions(impl);

	}

	@Test
	// redirect page list advertisement
	public void testgetDeleteAdverisementById() throws Exception {

		this.mockMvc.perform(delete("/delete/{id}", 1)).andExpect(
				status().isOk());

	}

	@Test
	// test mvc edit advertisement
	public void testEditAdvertisement() throws Exception {

		ArrayList<Advertisement> listAdvert = new ArrayList<Advertisement>();
		for (int i = 1; i < 3; i++) {
			listAdvert.add(new Advertisement("rubric" + i, "title" + i, "text"
					+ i));
		}
		Login login = new Login("login", "password", "nameUser");
		when(impl.getAdvertisementByIDLogin(Mockito.any(Login.class)))
				.thenReturn(listAdvert);

		this.mockMvc.perform(get("/editor").sessionAttr("login", login))
				.andExpect(status().isOk()).andExpect(view().name("Editor"))
				.andExpect(model().attribute("myListAdvertisment", listAdvert));

		ArgumentCaptor<Login> dtoCaptor = ArgumentCaptor.forClass(Login.class);
		verify(impl, times(1)).getAdvertisementByIDLogin(dtoCaptor.capture());
		verifyNoMoreInteractions(impl);
	}

	@Test
	// test mvc create advertisement
	public void testCreateAdvertisement() throws Exception {
		String rubric = "rubric";
		String title = "title";
		String text = "text";

		Login login = new Login("login", "password", "nameUser");

		this.mockMvc
				.perform(
						get("/editor").param("rubric", rubric)
								.param("title", title).param("text", text)
								.sessionAttr("login", login))
				                  .andExpect(status().isOk());

	
	}

	static class IntegrationTestUtil {

		public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
				MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(),
				Charset.forName("utf8"));

		public static byte[] convertObjectToJsonBytes(Object object)
				throws IOException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			return mapper.writeValueAsBytes(object);
		}
	}
}
