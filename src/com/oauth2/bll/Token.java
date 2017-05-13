package com.oauth2.bll;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oauth2.tool.HibernateUtil;

import jdk.nashorn.internal.parser.TokenKind;

public class Token {
	static public String check(String code, String redirect_uri, String appid, String secret){
		String token = "";
		Session s = HibernateUtil.getSession();
		Query query = s.createQuery("select t.id, t.accessToken, t.createAt from App a, Token t "
				+ "where a.appid=:appid and a.secret=:secret and a.ban='0' "
				+ "and t.authorizationCode=:code and t.redirectUri=:redirect_uri and t.used='0'");
		query.setString("appid", appid);
		query.setString("secret", secret);
		query.setString("code", code);
		query.setString("redirect_uri", redirect_uri);
		List<Object> rs = query.list();

		if (!rs.isEmpty()) {
			Object[] r = (Object[])rs.get(0);
			Transaction transaction = s.beginTransaction();
			try{
				com.oauth2.model.Token token_obj = (com.oauth2.model.Token)s.get(com.oauth2.model.Token.class, (Integer)(r[0]));
				token_obj.setUsed("1");
				token_obj.setUpdateAt(new Date());
				s.update(token_obj);
				transaction.commit();
				if ((new Date().getTime() - ((Date)(r[2])).getTime()) < 60000 )
					token = (String)(r[1]);
			}
			catch(Exception e){
				transaction.rollback();
			}
		}
		s.close();
		return token;
	}
	
	static public boolean validate(String token){
		Session s = HibernateUtil.getSession();
		Query query = s.createQuery("select t.updateAt from Token t "
				+ "where t.accessToken=:token and t.used='1'");
		query.setString("token", token);
		List<Object> rs = query.list();
		System.out.println();
		return !rs.isEmpty() && new Date().getTime() - ((Date)rs.get(0)).getTime() <= 3600000;
	}
}
