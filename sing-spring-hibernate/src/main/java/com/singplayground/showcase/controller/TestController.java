package com.singplayground.showcase.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.singplayground.showcase.exception.ControllerGenericException;
import com.singplayground.showcase.exception.IllegalArgumentGenericException;
import com.singplayground.showcase.model.TestMod;
import com.singplayground.showcase.service.TestModService;

@Controller
public class TestController {

	protected Logger logger = Logger.getLogger(TestController.class);

	private TestModService testModService;

	@Autowired
	@Qualifier(value = "testModService")
	public void setTestModService(TestModService testModService) {
		this.testModService = testModService;
	}

	@RequestMapping(value = "/testInsert", method = RequestMethod.GET)
	public ModelAndView insertorupdateTestController() {
		ModelAndView mav = new ModelAndView();
		TestMod testMod = new TestMod();
		testMod.setMessage("this is content");
		try {
			testModService.saveOrupdateTest(testMod);
			mav.setViewName("basic/index");
			return mav;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/testGet", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView testGetController() {
		System.out.println("this is test get");
		ModelAndView mav = new ModelAndView();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			//sdf.
			Date cDate = new Date();

			String currentDateString = sdf.format(cDate);
			System.out.println("start list");
			List<TestMod> testLists = testModService.listTestMod();
			for (TestMod t : testLists) {
				System.out.println("this is tid : " + t.getTid());
				System.out.println("this is old message : " + t.getMessage());
				t.setMessage("New message - : " + currentDateString);
			}

			for (TestMod t : testLists) {
				System.out.println("this is tid : " + t.getTid());
				System.out.println("this is new message : " + t.getMessage());

				testModService.saveOrupdateTest(t);
			}

			System.out.println("the size of the list" + testLists.size());
			System.out.println("end list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;

	}

	@RequestMapping(value = "/testInsertNull", method = RequestMethod.GET)
	public ModelAndView insertorupdateNullTestController() throws Exception {
		ModelAndView mav = new ModelAndView();
		TestMod testMod = null;
		logger.info("insertorupdateNullTestController ---");
		logger.debug("debug insertorupdateNullTestController ---");

		logger.error("******* this is error ******** ");
		try {

			testModService.saveOrupdateTest(testMod);
			mav.setViewName("basic/index");

		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
			throw new IllegalArgumentGenericException("xxxx", "xxxxx");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception();
		}
		return mav;
	}

	@RequestMapping(value = "/testException", method = RequestMethod.GET)
	public void exceptionTestController() {
		String type = "error";
		if ("error".equals(type)) {
			System.out.println("test Exception ----- 1 ");
			throw new ControllerGenericException("E888", "This is custom message");
		}
	}

	@RequestMapping(value = "/testException2", method = RequestMethod.GET)
	public @ResponseBody HashMap exceptionTest2Controller() {

		HashMap hm = new HashMap();
		try {
			hm.put("a", "a value");
		} catch (Exception e) {
			throw new ControllerGenericException("E888", "This is custom message");
		}
		hm.put("b", "b value");
		return hm;
	}

}
