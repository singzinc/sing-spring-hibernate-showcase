package com.singplayground.showcase.test;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.singplayground.showcase.test.config.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class JsonControllerTests extends AbstractContextControllerTests {

	private MockMvc mockMvc;

	@Rule
	public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation)).build();
	}

	@Test
	public void param() throws Exception {
		this.mockMvc.perform(get("/test/json/example1")).andExpect(content().string("test"));
		/*	
			this.mockMvc.perform(get("/test/json/example1")).andExpect(content().string("test"))
					.andDo(document("index", links(linkWithRel("alpha").description("Link to the alpha resource"), linkWithRel("bravo").description("Link to the bravo resource"))));
		*/
	}

	@Test
	public void getStock() throws Exception {
		//this.mockMvc.perform(get("/getStock")).andExpect(content().string("test"));

		this.mockMvc.perform(get("/getStock").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.stockName").value("HSBC"))
				.andExpect(jsonPath("$.marketCode").value("HK"));
		/*	
			this.mockMvc.perform(get("/test/json/example1")).andExpect(content().string("test"))
					.andDo(document("index", links(linkWithRel("alpha").description("Link to the alpha resource"), linkWithRel("bravo").description("Link to the bravo resource"))));
		*/
	}

}
