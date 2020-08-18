package com.tt.ui.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.abel533.mapper.Mapper;
import com.tt.ui.pojo.Menu;


public interface MenuRepository extends Mapper<Menu>{

	List<Menu> getMenusByParentText();
	List<Menu> getMenusBySubText(@Param("parentId") String parentId);
	
}
