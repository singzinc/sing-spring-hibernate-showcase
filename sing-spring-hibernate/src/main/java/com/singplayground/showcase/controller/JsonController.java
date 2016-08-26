package com.singplayground.showcase.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

	protected Logger logger = LogManager.getLogger(JsonController.class);

	@RequestMapping(value = "/test/json/example1", method = RequestMethod.GET)
	@ResponseBody
	public String example1() {
		logger.info("******** json example 1 ******");
		String msg = "test";
		return msg;
	}

}
