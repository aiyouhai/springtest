package com.tt.mybatis;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class MybatisUtils {
private static SqlSessionFactory sqlSessionFactory=null;
	
	
public static SqlSessionFactory innitSqlSessionFactory() {
	String resource="配置文件";
	
	InputStream inputStream;
	try {
		inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	} catch (IOException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}
	return sqlSessionFactory;
}
	
	public static SqlSession openSession() {
		if(sqlSessionFactory==null) {
			 innitSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
		
		
	}
}
