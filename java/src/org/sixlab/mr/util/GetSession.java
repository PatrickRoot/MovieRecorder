package org.sixlab.mr.util;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class GetSession {
	private static SqlSessionFactory factory;
	
	static {
		InputStream in = GetSession.class.getClassLoader().getResourceAsStream(
				"mybatis-config.xml");
		factory = new SqlSessionFactoryBuilder().build(in);
	}
	
	public static SqlSessionFactory getSessionFactory() {
		return factory;
	}
	
	public static SqlSession getSession() {
		return factory.openSession();
	}
}