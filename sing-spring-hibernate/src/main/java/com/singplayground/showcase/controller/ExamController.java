package com.singplayground.showcase.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.singplayground.showcase.dto.ExamDto;

@RestController
public class ExamController {

	protected Logger logger = LogManager.getLogger(JsonController.class);

	@RequestMapping(value = "/exam/{year}/getExam", method = RequestMethod.GET)
	@ResponseBody
	public ExamDto getExamByYear(@PathVariable Integer year) {
		logger.info("get exam by Year : " + year);

		ExamDto examDto = new ExamDto();
		/*
				if (year == 2013) {
					System.out.println("year == 2013");
					examDto.setExamId(Long.parseLong("1"));
				} else {
					System.out.println("year != 2013");
				}
		*/
		switch (year) {
		case 2013: {
			System.out.println("year == 2013");
			examDto.setExamId(Long.parseLong("1"));
			examDto.setTitle("2013 - english exam");
			examDto.setContent("this is Content");
			break;
		}
		case 2014: {
			System.out.println("year == 2014");
			examDto.setExamId(Long.parseLong("2"));
			examDto.setTitle("2014 - Math exam");
			examDto.setContent("this is Content");
			break;
		}
		case 2015: {
			System.out.println("year == 2015");
			examDto.setExamId(Long.parseLong("3"));
			examDto.setTitle("2015 - Business exam");
			examDto.setContent("this is Content");
			break;
		}
		default: {
			System.out.println("year == 2010");
			examDto.setExamId(Long.parseLong("1000"));
			examDto.setTitle("2015 - Exam");
			examDto.setContent("this is Content");
			break;
		}
		}

		return examDto;
	}

	@RequestMapping(value = "/exam/listAllExam", method = RequestMethod.GET)
	@ResponseBody
	public List<ExamDto> listAllExam() {
		List<ExamDto> examList = new ArrayList();

		ExamDto examDto1 = new ExamDto();
		ExamDto examDto2 = new ExamDto();
		ExamDto examDto3 = new ExamDto();

		examDto1.setExamId(Long.parseLong("1"));
		examDto1.setTitle("2013 - english exam");
		examDto1.setContent("this is Content");

		examDto2.setExamId(Long.parseLong("2"));
		examDto2.setTitle("2014 - Math exam");
		examDto2.setContent("this is Content");

		examDto3.setExamId(Long.parseLong("3"));
		examDto3.setTitle("2015 - Business exam");
		examDto3.setContent("this is Content");

		examList.add(examDto1);
		examList.add(examDto2);
		examList.add(examDto3);

		return examList;
	}
}
