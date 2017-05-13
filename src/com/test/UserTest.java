package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.oauth2.model.User;




public class UserTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Before
	public void init(){
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destory(){
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void test() {
		User user = new User();
		user.setUsername("jiangzhou");
		user.setPassword("jiangzhou");
//		Token token = new Token();
//		token.setAccessToken("123");
//		token.setAuthorizationCode("123");
		session.save(user);
	}

}
