package com.tt.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import net.sf.json.JSONObject;

/*
 * @author Alan Chen
 * @description 认证异常: 匿名用户在认证过程中的异常
 * @date 2019/6/17
 * **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException {
		String exceptionMsg = ((HttpServletRequest) httpServletResponse).getHeader("ServiceException");
		if (!StringUtils.isEmpty(exceptionMsg)) {
			responseServiceException(httpServletResponse, exceptionMsg);
		}
	}

	private void responseServiceException(HttpServletResponse response, String exceptionMsg) {

		AuthenticationServiceException serviceError = new AuthenticationServiceException(exceptionMsg);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("认证异常", serviceError);

		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(jsonObject.toString());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
