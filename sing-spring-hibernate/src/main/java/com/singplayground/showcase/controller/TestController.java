package com.singplayground.showcase.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.singplayground.showcase.model.TestMod;
import com.singplayground.showcase.service.TestModService;

@Controller
//@RequestMapping(value="/pmaptools")
public class TestController {

	//@Autowired
	private TestModService testModService;

	@Autowired
	@Qualifier(value = "testModService")
	public void setTestModService(TestModService testModService) {
		this.testModService = testModService;
	}

	@RequestMapping(value = "/testMod", method = RequestMethod.GET)
	public void insertorupdateTest() {
		TestMod testMod = new TestMod();
		Long id = Long.parseLong("3");
		testMod.setTid(id);
		testMod.setMessage("cccc");
		try {
			testModService.saveOrupdateTest(testMod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseBody
	public HashMap testController() {

		HashMap hm = new HashMap();
		try {
			hm.put("status", "Y");
		} catch (Exception e) {

			hm.put("status", "N");
		}

		return hm;

	}
}
