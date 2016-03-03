package com.singplayground.showcase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/basic")
public class BasicController {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ModelAndView editAgency() throws Exception {

		System.out.println("********");
		ModelAndView mav = new ModelAndView();

		mav.setViewName("basic/index");
		return mav;
	}

}
