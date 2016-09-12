package com.singplayground.showcase.test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.singplayground.showcase.service.TestModService;
import com.singplayground.showcase.test.config.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestControllerTests extends AbstractContextControllerTests {
	private MockMvc mockMvc;
	//@Autowired
	private TestModService testModService;

	@Autowired
	@Qualifier(value = "testModService")
	public void setTestModService(TestModService testModService) {
		this.testModService = testModService;
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void getTest1() throws Exception {

		this.mockMvc.perform(get("/getTestlist").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(10)))
				.andExpect(jsonPath("$[*].tid", contains(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));

	}

	@Test
	public void getTest2() throws Exception {
		System.out.println("------");
		testModService.listTestMod();
	}
}
