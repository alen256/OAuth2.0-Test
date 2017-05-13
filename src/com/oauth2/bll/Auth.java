package com.oauth2.bll;

import java.util.Date;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oauth2.model.Token;
import com.oauth2.tool.HibernateUtil;
import com.oauth2.tool.TokenProcessor;

public class Auth {
	static int i = 0;
	static Random random = new Random();
	static public boolean check(String username, String password){
		Session s = HibernateUtil.getSession();
		Query query = s.createQuery("from User as u where u.username=:username and u.password=:password");
		query.setString("username", username);
		query.setString("password", password);
		boolean re = !query.list().isEmpty();
		s.close();
		return re;
	}
	static public String makeToken(String username, String appid, String redirect_uri){
		if (i > 65530){
			i = 0;
		}
		String authorization_code = TokenProcessor.makeToken(random.nextInt(6) + username + new Date().getTime() + i++);
		String access_token = TokenProcessor.makeToken(username + random.nextInt(6) + i++ + new Date().getTime());
		System.out.println(random.nextInt(6) + username + new Date().getTime() + i++);
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		Token token = new Token();
		token.setUsername(username);
		token.setAppid(appid);
		token.setRedirectUri(redirect_uri);
		token.setAuthorizationCode(authorization_code);
		token.setAccessToken(access_token);
		token.setUsed("0");
		token.setCreateAt(new Date());
		s.save(token);
		t.commit();
		s.close();
		return authorization_code;
	}
	
}
