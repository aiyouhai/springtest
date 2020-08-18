package com.tt.ui.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.ui.pojo.SysDept;
import com.tt.ui.service.sysdeptservice.SysDeptService;
import com.tt.ui.service.sysygservice.SysYgService;

@Controller
@RequestMapping("/dept")
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptServiceImpl;
	@Autowired
	private SysYgService sysYgServiceImpl;

	@RequestMapping(method = RequestMethod.GET, value = "/selectdeptandyg")
	@ResponseBody
	public SysDept selectdeptandyg(long id) {
		SysDept sysDept = sysDeptServiceImpl.selectDept(id);
		return sysDept;
	}

	// 通用mapper
	@RequestMapping(method = RequestMethod.GET, value = "/selectOne")
	@ResponseBody
	public List<SysDept> selectOne(long id, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName());
				System.out.println(cookie.getValue());
				System.out.println(cookie.getMaxAge());
				System.out.println(cookie.getPath());
			}
		}
		List<SysDept> sysDept = sysDeptServiceImpl.selectOne(id);
		return sysDept;
	}
}
