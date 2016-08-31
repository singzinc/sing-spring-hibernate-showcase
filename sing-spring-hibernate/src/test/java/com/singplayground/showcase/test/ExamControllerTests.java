package com.singplayground.showcase.test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.singplayground.showcase.test.config.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExamControllerTests extends AbstractContextControllerTests {

	protected Logger logger = LogManager.getLogger(ExamControllerTests.class);
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Rule
	public RestDocumentation restDocumentation = new RestDocumentation("target/generated-snippets");

	private RestDocumentationResultHandler document;

	@Before
	public void setup() throws Exception {
		//this.mockMvc = webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation)).build();
		this.document = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).apply(documentationConfiguration(this.restDocumentation)).alwaysDo(this.document).build();
	}

	@Test
	public void getExamByYear() throws Exception {
		logger.info("start test case");
		this.document.snippets(responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`")));
		logger.info("end msg");

		this.mockMvc.perform(get("/exam/2013/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(1))
				.andExpect(jsonPath("$.title").value("2013 - english exam"));
		this.mockMvc.perform(get("/exam/2014/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(2))
				.andExpect(jsonPath("$.title").value("2014 - Math exam"));
		this.mockMvc.perform(get("/exam/2015/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(3))
				.andExpect(jsonPath("$.title").value("2015 - Business exam"));
		this.mockMvc.perform(get("/exam/2016/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(1000))
				.andExpect(jsonPath("$.title").value("2015 - Exam"));

		/*
		this.mockMvc.perform(get("/mapping/produces").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.foo").value("bar"))
		.andExpect(jsonPath("$.fruit").value("apple"));
		*/

	}

	@Test
	public void listAllExam() throws Exception {
		logger.info("start test case - list all exam");
		this.mockMvc.perform(get("/exam/listAllExam").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[*].examId", contains(1, 2, 3)));

	}

	@Test
	public void listAllExam2() throws Exception {
		logger.info("start test case - list all exam");
		this.mockMvc.perform(get("/exam/listAllExam").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[*].examId", containsInAnyOrder(1, 3, 2)));

	}
}
