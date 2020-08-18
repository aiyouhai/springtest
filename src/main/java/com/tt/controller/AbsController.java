package com.tt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

@Scope(value = "prototype")
public class AbsController {
	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		return basePath;
	}

}
