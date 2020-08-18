package com.tt.mybatis;

public interface MybatisMapper {

	void addMybatis(Mybatis mybatis);
	int deleteMybatis(String name);
	 void updateMybatis( String name);
	 Mybatis findMybatis(String name );
}
