package com.oauth2.bll;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.oauth2.model.App;
import com.oauth2.tool.HibernateUtil;

public class SearchModel {
	static public App getApp(String appid){
		Session s = HibernateUtil.getSession();
		Query query = s.createQuery("from App as a where a.appid=:appid and a.ban=0");
		query.setString("appid", appid);
		List<App> a = query.list();
		if (a.isEmpty()){
			return null;
		}
		else{
			return a.get(0);
		}		
	}
}
