package com.tt.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class SpringSecurityController {
	@RequestMapping("/security")
	@ResponseBody
	public String helloSecurity() {
		return "hello security";

	}
}
