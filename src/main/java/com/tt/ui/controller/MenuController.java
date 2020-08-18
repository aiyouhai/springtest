package com.tt.ui.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.ui.pojo.Menu;
import com.tt.ui.service.menuservice.MenuService;
import com.tt.ui.vo.ResponseObject;
import com.tt.utils.AbstractController;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractController {

	@Resource
	private MenuService menuService;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getMenus", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseObject getMenus() {
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		List<Menu> parentList = menuService.getMenusByParentText();

		if (parentList.size() > 0 || parentList != null) {

			for (Menu menu : parentList) {
				HashMap<String, Object> sub = new HashMap<String, Object>();

				List<Menu> subList = menuService.getMenusBySubText(menu.getId());
				sub.put("parent", menu);
				sub.put("subList", subList);
				menuList.add(sub);
			}
		}

		return new ResponseObject(202, "成功", menuList);
	}

	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String menuIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		String basePath = getBasePath(request);
		model.addAttribute("basePath", basePath);
		return "index";
	}

	@RequestMapping(value = "/manage", method = { RequestMethod.GET, RequestMethod.POST })
	public String menuManage(HttpServletRequest request, HttpServletResponse response, Model model) {
		String basePath = getBasePath(request);
		model.addAttribute("basePath", basePath);
		return "menu/manage";
	}

}
