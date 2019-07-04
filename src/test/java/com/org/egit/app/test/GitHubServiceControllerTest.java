package com.org.egit.app.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.org.egit.app.bean.User;
import com.org.egit.app.controller.GitHubServiceController;
import com.org.egit.app.service.GitHubService;

//import com.org.egit.app.controller.GitHubServiceController;

@RunWith(SpringRunner.class)
@WebMvcTest(value = GitHubServiceController.class, secure = false)
public class GitHubServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GitHubService gitHubService;
	
	User mockUser = new User("Sri");
	/*Course mockCourse = new Course("Course1", "Spring", "10 Steps",
			Arrays.asList("Learn Maven", "Import Project", "First Example",
					"Second Example"));*/
	

	String exampleCourseJson = "{\"userName\":\"Sri\"}";

	@Test
	public void getReposTest() throws Exception {
		Mockito.when(gitHubService.getUserRepositories(Mockito.anyObject())).thenReturn(mockUser);

	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/repos/ynvsreeni").accept(	MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());	
		
		System.out.println("  === "+result.getResponse().getContentAsString());

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(exampleCourseJson, result.getResponse().getContentAsString(), false);
	}
	
	//@Test
	public void getReposTestXML() throws Exception {
		Mockito.when(gitHubService.getUserRepositories(Mockito.anyObject())).thenReturn(mockUser);

	
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(	"/repos/ynvsreeni").accept(	MediaType.APPLICATION_XML_VALUE);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(" = result======== "+result.getResponse().getContentAsString());
		
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><userName>Sri</userName></user>";
		System.out.println("  === "+result.getResponse().toString());
		//assertEquals(expected, result.getResponse().getContentAsString(), false);
		
		
	}


}
