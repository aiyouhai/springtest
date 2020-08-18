package com.tt.ui.service.menuservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.ui.pojo.Menu;
import com.tt.ui.repository.MenuRepository;

@Service("menuService")
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public List<Menu> getMenusByParentText() {
		List<Menu> menusByParentText = menuRepository.getMenusByParentText();
		return menusByParentText;
	}
	@Override
	public List<Menu> getMenusBySubText(String parentId) {
		List<Menu> menusBySubText = menuRepository.getMenusBySubText(parentId);
		return menusBySubText;
	}

}
