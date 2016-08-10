package com.singplayground.showcase.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.singplayground.showcase.model.TestMod;

@Repository
public class TestDaoImpl implements TestDao {

	public Logger logger = Logger.getLogger(TestDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void savaOrupdate(Object object) {
		logger.info("----- save or update ----");
		getSession().saveOrUpdate(object);
	}

	public List<TestMod> getList() {
		logger.info("----- get list ----");

		Session session = this.sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TestMod.class);

		List<TestMod> campaignList = criteria.list();
		return campaignList;
	}

}
