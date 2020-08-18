package com.tt.ui.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.exception.MyException;
import com.tt.ui.vo.ResponseObject;

@Controller
@RequestMapping("/exception")
public class ExceptionController {
	@RequestMapping("/add")
	@ResponseBody

	public ResponseObject<String> addException(HttpServletRequest request, HttpServletResponse response, long id)
			throws MyException {
		if (id == 1) {
			throw new MyException(202, "失败");
		}
		return new ResponseObject<String>(200, "成功", "sucuss", request.getRequestURL().toString());
	}
}
