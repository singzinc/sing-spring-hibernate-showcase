package com.singplayground.showcase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.singplayground.showcase.dao.TestDao;
import com.singplayground.showcase.model.TestMod;

@Service
public class TestModServiceImpl implements TestModService {

	//@Autowired
	private TestDao testDao;

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Transactional
	public void saveOrupdateTest(TestMod testMod) {
		testDao.savaOrupdate(testMod);
	}

}
