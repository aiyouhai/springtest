package com.tt.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;

@Scope(value="prototype")
public class AbstractController {

	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		return basePath;
		
	}
	
}
