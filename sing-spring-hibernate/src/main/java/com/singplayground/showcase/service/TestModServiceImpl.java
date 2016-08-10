package com.singplayground.showcase.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.singplayground.showcase.dao.TestDao;
import com.singplayground.showcase.model.TestMod;

@Service
public class TestModServiceImpl implements TestModService {

	public Logger logger = Logger.getLogger(TestModServiceImpl.class);

	//@Autowired
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Transactional
	public void saveOrupdateTest(TestMod testMod) throws IllegalArgumentException {
		logger.info("thsi is service");
		Assert.notNull(testMod, "testMod cannot null");
		testDao.savaOrupdate(testMod);
	}

	@Transactional
	public List<TestMod> listTestMod() {
		return testDao.getList();
	}

}
