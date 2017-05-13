package com.oauth2.tool;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	static private Configuration configuration = new Configuration().configure();
	static private StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
	static private StandardServiceRegistryImpl registry = (StandardServiceRegistryImpl) builder.build();
	static private SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
    //方法返回session
	static public Session getSession(){
        return sessionFactory.openSession();
    }
    
    public HibernateUtil(){
    	
    }
}
