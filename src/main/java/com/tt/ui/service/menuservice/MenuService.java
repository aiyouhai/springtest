package com.tt.ui.service.menuservice;

import java.util.List;

import com.tt.ui.pojo.Menu;
public interface MenuService {

	List<Menu> getMenusByParentText();

	List<Menu> getMenusBySubText(String parentId);

}
