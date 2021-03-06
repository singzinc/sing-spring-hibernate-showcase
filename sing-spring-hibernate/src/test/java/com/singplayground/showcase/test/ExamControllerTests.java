package com.singplayground.showcase.test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.singplayground.showcase.test.config.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = RestNotesSpringDataRest.class)
public class ExamControllerTests extends AbstractContextControllerTests {

	protected Logger logger = LogManager.getLogger(ExamControllerTests.class);
	private MockMvc mockMvc;

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	private RestDocumentationResultHandler documentationHandler;

	@Before
	public void setup() throws Exception {
		//this.mockMvc = webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation)).build();
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation)).build();

		this.documentationHandler = document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

		//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
		//		this.mockMvc = webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation)).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(documentationConfiguration(this.restDocumentation))
		//				.alwaysDo(document("{method-name}/{step}/"))
				.build();

	}

	@Test
	public void getExamByYear() throws Exception {
		logger.info("start test case");
		//this.document.snippets(responseHeaders(headerWithName("Content-Type").description("The Content-Type of the payload, e.g. `application/hal+json`")));
		logger.info("end msg");

		this.mockMvc.perform(get("/exam/2013/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(1))
				.andExpect(jsonPath("$.title").value("2013 - english exam"));
		this.mockMvc.perform(get("/exam/2014/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(2))
				.andExpect(jsonPath("$.title").value("2014 - Math exam"));
		this.mockMvc.perform(get("/exam/2015/getExam").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.examId").value(3))
				.andExpect(jsonPath("$.title").value("2015 - Business exam"));
		this.mockMvc
				.perform(get("/exam/2016/getExam").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.examId").value(1000))
				.andExpect(jsonPath("$.title").value("2015 - Exam"))
				.andDo(this.documentationHandler.document(responseFields(fieldWithPath("examId").description("examId")),
						responseFields(fieldWithPath("title").description("title")), responseFields(fieldWithPath("content").description("content")),
						responseFields(fieldWithPath("examDate").description("examDate")), responseFields(fieldWithPath("totalMarks").description("totalMarks"))));

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
		/*
		this.mockMvc.perform(get("/exam/listAllExam").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[*].examId", containsInAnyOrder(1, 3, 2)));
		*/

		this.mockMvc.perform(get("/exam/listAllExam").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[*].examId", containsInAnyOrder(1, 3, 2)));

	}

}
