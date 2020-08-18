package com.tt.mybatis;

import org.apache.ibatis.session.SqlSession;

public class MybatisTest {
	private static SqlSession sqlSession=null;
public static void main(String[] args) {
	try {
		sqlSession = MybatisUtils.openSession();
		MybatisMapper mapper = sqlSession.getMapper(MybatisMapper.class);
		Mybatis mybatis = new Mybatis();
		mybatis.setName("mybatis1");
		mybatis.setNum(1);
		mapper.addMybatis(mybatis);
		sqlSession.commit();
	} catch (Exception e) {
		
		e.printStackTrace();
		sqlSession.rollback();
	}finally {
		if(sqlSession!=null) {
			sqlSession.close();
			
		}
	}
	
	
}
}
