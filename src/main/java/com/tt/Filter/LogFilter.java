package com.tt.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LogFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 输出站点名称
		System.out.println("站点网址：http://www.runoob.com");

		// 把请求传回过滤链
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// 获取初始化参数
		String site = config.getInitParameter("Site");

		// 输出初始化参数
		System.out.println("网站名称: " + site);

	}

}
