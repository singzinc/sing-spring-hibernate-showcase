package com.singplayground.showcase.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.singplayground.showcase.exception.ControllerGenericException;
import com.singplayground.showcase.exception.IllegalArgumentGenericException;
import com.singplayground.showcase.model.TestDateTime;
import com.singplayground.showcase.model.TestMod;
import com.singplayground.showcase.service.TestDateTimeService;
import com.singplayground.showcase.service.TestModService;

@Controller
public class TestController {

	protected Logger logger = LogManager.getLogger(TestController.class);

	private TestModService testModService;

	private TestDateTimeService testDateTimeService;

	@Autowired
	@Qualifier(value = "testModService")
	public void setTestModService(TestModService testModService) {
		this.testModService = testModService;
	}

	@Autowired
	@Qualifier(value = "testDateTimeService")
	public void setTestDateTimeService(TestDateTimeService testDateTimeService) {
		this.testDateTimeService = testDateTimeService;
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
	public ModelAndView testGetController() {
		System.out.println("this is test get");
		logger.info("----------- test get ------");
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

	@RequestMapping(value = "/testException2/{year}/{month}/{day}", method = RequestMethod.GET)
	@ResponseBody
	public List<TestDateTime> exceptionTest2Controller(@PathVariable Integer year, @PathVariable Integer month, @PathVariable Integer day) {

		//Date date = new Date();
		//		Calendar calendar = new GregorianCalendar(2013, 1, 28, 13, 24, 56);
		//
		//		calendar.set(Calendar.YEAR, 2014);
		//		calendar.set(Calendar.MONTH, 11);
		//		calendar.set(Calendar.MINUTE, 33);
		//		calendar.set(Calendar.HOUR, 0);
		//		calendar.set(Calendar.MINUTE, 0);
		//		calendar.set(Calendar.MILLISECOND, 0);

		//Date date = calendar.getTime();

		//System.out.println("this is the date :" + date.toString());
		//System.out.println("this is the date :" + date.);

		//DateTime srcDateTime = date.toDateTime(srcTZ);
		//DateTime dstDateTime = srcDateTime.toDateTime(dstTZ);
		//DateTime dt = new DateTime(DateTimeZone.UTC);

		//		DateTime dt = new DateTime(DateTimeZone.UTC).withHourOfDay(5).withMinuteOfHour(20).withSecondOfMinute(0).withYear(1994).withMonthOfYear(7).withDayOfMonth(2);
		//		
		//		System.out.println("this is date:" + dt.toString());
		//		System.out.println("****** millis ****" + dt.getMillis());
		//System.out.println("UTC Time:" + dstDateTime.getMillis());
		//System.out.println("UTC Time:" + new Timestamp(dstDateTime.getMillis()));

		System.out.println("------ 1");
		List<TestDateTime> testDateTimes = testDateTimeService.listTestDate();

		/*
		System.out.println("------ 2");
		TestMod testMod = new TestMod();

		testMod.setTid(Long.parseLong("1"));

		System.out.println("*********************************");
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		Calendar calendar = Calendar.getInstance(timeZone);

		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.US);
		simpleDateFormat.setTimeZone(timeZone);

		System.out.println("Time zone: " + timeZone.getID());
		System.out.println("default time zone: " + TimeZone.getDefault().getID());
		System.out.println();

		System.out.println("UTC:     " + simpleDateFormat.format(calendar.getTime()));
		System.out.println("Default: " + calendar.getTime());

		Date longDate = calendar.getTime();
		//System.out.println(longDate);
		System.out.println("this is time stamp : " + longDate.getTime());
		//java.sql.Timestamp timeStampDate = new Timestamp(longDate.getTime());
		testMod.setMessage("xxx");
		*/

		for (TestDateTime t : testDateTimes) {
			System.out.println("t.getDateTime2() : " + t.getDateTime2());
			System.out.println("t.getDateTime() : " + t.getDateTime());
			//t.setMessage("New message - : " + currentDateString);
		}

		return testDateTimes;
		//hm.put("b", longDate);
		//return hm;
	}
}
