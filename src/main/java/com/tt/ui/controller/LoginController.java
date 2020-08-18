package com.tt.ui.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class LoginController {
	private static final Log logger = LogFactory.getLog(LoginController.class);

	/*
	 * @RequestMapping(value = "/index") public String index(String username, String
	 * password, HttpServletResponse response, HttpSession session,
	 * HttpServletRequest request, Model model) {
	 * 
	 * return "index"; }
	 * 
	 * @RequestMapping(value = "/login") public String login(String username, String
	 * password, HttpServletResponse response, HttpSession session,
	 * HttpServletRequest request, Model model) { System.out.println(username + ":"
	 * + password); if (username == null || password == null) { return "login"; } //
	 * 3.获取一个用户识别信息 Subject currentUser = SecurityUtils.getSubject();
	 * 
	 * // .判断是否已经身份验证 if (!currentUser.isAuthenticated()) { // 4.1把用户名和密码封装为
	 * UsernamePasswordToken 对象 UsernamePasswordToken token = new
	 * UsernamePasswordToken(username, password); // 4.2设置rememberme
	 * token.setRememberMe(true); try { // 4.3登录. currentUser.login(token); } catch
	 * (UnknownAccountException e) { throw new AuthorizationException("用户名错误"); }
	 * catch (IncorrectCredentialsException e) { throw new
	 * AuthorizationException("密码错误"); } } return "index"; }
	 * 
	 * @RequestMapping("/logout") public String logout() { Subject subject =
	 * SecurityUtils.getSubject(); // 退出 subject.logout();
	 * System.out.println("退出成功"); return "redirect:/index/login"; }
	 * 
	 * @RequiresRoles(value = { "ROLE_ADMIN" })
	 * 
	 * @RequestMapping("/register") public String register() {
	 * 
	 * Subject currentUser = SecurityUtils.getSubject(); if
	 * (currentUser.hasRole("ROLE_ADMIN")) { return "register"; } else { throw new
	 * AuthorizationException("没有权限"); }
	 * 
	 * return "register"; }
	 * 
	 * @RequestMapping("/user/register") public String
	 * userRegister(@ModelAttribute("user") UserForm user) { if
	 * (user.getUpass().equals(user.getReupass())) { logger.info("成功"); return
	 * "login"; // 注册成功，跳转到 login.jsp } else { logger.info("失败"); //
	 * 使用@ModelAttribute("user")与model.addAttribute("user",user)的功能相同 //
	 * register.jsp页面上可以使用EL表达式${user.uname}取出ModelAttribute的uname值 return
	 * "redirect:/index/register"; // 返回 register.jsp }
	 * 
	 * }
	 */
}
