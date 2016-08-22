package com.singplayground.showcase.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.singplayground.showcase.model.TestDateTime;

public class TestDateDaoImpl implements TestDateDao {

	public Logger logger = LogManager.getLogger(TestDateDaoImpl.class);
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public List<TestDateTime> getList() {

		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TestDateTime.class);

		List<TestDateTime> testList = criteria.list();
		return testList;
	}
}
