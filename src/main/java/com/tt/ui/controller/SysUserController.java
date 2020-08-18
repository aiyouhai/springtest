package com.tt.ui.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.ui.pojo.SysRole;
import com.tt.ui.pojo.SysUser;
import com.tt.ui.service.sysuser_roleservice.SysUser_RoleService;
import com.tt.ui.service.sysuserservice.SysUserService;
import com.tt.ui.vo.PageInfo;
import com.tt.utils.TestDomain;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {
	private static final String SYSUSER_KEY = "SYSUSER_KEY";
	@Autowired
	private SysUserService sysUserServiceImpl;
	@Autowired
	private SysUser_RoleService sysUser_RoleServiceImpl;
	@Autowired
	private TestDomain testDomain;

	@RequestMapping(method = RequestMethod.GET, value = "/selectRoleByUserId")
	@ResponseBody
	public List<SysRole> selectRoleByUserId(long id) {
		SysUser selectOneById = testDomain.selectOneById(id);
		System.out.println(selectOneById);
		List<SysRole> roleList = sysUserServiceImpl.selectRoleByUserId(id);
		return roleList;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/addcookie")
	public void addCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("name", "lisa");

		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getsession")
	@ResponseBody
	public void addSession(HttpServletRequest request, HttpServletResponse response) {
		String attribute = (String) request.getSession().getAttribute("prod");
		System.out.println("购买的商品有：" + attribute);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/addsession")
	public void addSession(String prod, HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		prod = new String(prod.getBytes("iso8859-1"), "utf-8");
		HttpSession session = request.getSession(true);
		session.setAttribute("prod", prod);
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		cookie.setMaxAge(30 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/selectOneById")
	@ResponseBody
	public SysUser selectOneById(long id, HttpServletRequest request, HttpServletResponse response) {
		String characterEncoding = request.getCharacterEncoding();
		System.out.println("characterEncoding:" + characterEncoding);
		String contextPath = request.getContextPath();
		System.out.println("contextPath:" + contextPath);
		String pathInfo = request.getPathInfo();
		System.out.println("pathInfo:" + pathInfo);
		String queryString = request.getQueryString();
		System.out.println("queryString:" + queryString);
		String remoteAddr = request.getRemoteAddr();
		System.out.println("remoteAddr:" + remoteAddr);
		String remoteHost = request.getRemoteHost();
		System.out.println("remoteHost:" + remoteHost);
		String requestURI = request.getRequestURI();
		System.out.println("requestURI:" + requestURI);
		String servletPath = request.getServletPath();
		System.out.println("servletPath:" + servletPath);
		int serverPort = request.getServerPort();
		System.out.println("serverPort:" + serverPort);
		Map<String, String[]> parameterMap = request.getParameterMap();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName());
				System.out.println(cookie.getValue());
				System.out.println(cookie.getMaxAge());
				System.out.println(cookie.getPath());
			}
		}
		SysUser sysUser = sysUserServiceImpl.selectOneById(id);
		return sysUser;
	}

//分页查询
	@RequestMapping(method = RequestMethod.GET, value = "/selectPageHelper")
	@ResponseBody
	public PageInfo<SysUser> selectPageHelper(@RequestParam int pageNum, @RequestParam int pageSize) {

		int totalCount = sysUserServiceImpl.selectcount();
		int totalPage = 0;
		totalPage = totalCount % pageSize > 0 ? totalCount / pageSize + 1 : totalCount / pageSize;

		List<SysUser> sysUserList = sysUserServiceImpl.selectPageHelper(pageNum - 1, pageSize);
		com.tt.ui.vo.PageInfo<SysUser> pageInfo = new com.tt.ui.vo.PageInfo<SysUser>();
		pageInfo.setLists(sysUserList);
		pageInfo.setPageNum(pageNum);
		pageInfo.setPageSize(pageSize);
		pageInfo.setTotalCount(totalCount);
		pageInfo.setTotalPage(totalPage);
		return pageInfo;
	}

	// 添加用户，角色

	@RequestMapping(method = RequestMethod.GET, value = "/adduser")
	@ResponseBody
	@Transactional
	public int addUser() {
		SysUser user = new SysUser();
		user.setCreateTime(new Date());
		user.setUserEmail("1234@qq.com");
		user.setUserInfo("普通用户");
		user.setUserName("lisa");
		user.setUserPassword("1234");
		user.setHeadimg(new byte[] {});
		int sysUser = sysUserServiceImpl.addUser(user);
		long sysRoleId = 2;
		System.out.println(user.getId());
		long id = user.getId();
		int sysUser_role = sysUser_RoleServiceImpl.addUser_Role(id, sysRoleId);
		return sysUser_role;
	}

//更新
	@RequestMapping(method = RequestMethod.GET, value = "/updateuser")
	@ResponseBody
	@Transactional
	public int updateUser(long id) {
		SysUser sysUser = selectOneById(id, null, null);
		sysUser.setUserName(null);
		sysUser.setCreateTime(null);
		sysUser.setHeadimg(null);
		sysUser.setUserEmail(null);
		sysUser.setUserInfo(null);
		sysUser.setUserPassword(null);

		int num = sysUserServiceImpl.updateSysUser(sysUser);
		return num;
	}

	// 删除
	@RequestMapping(method = RequestMethod.GET, value = "/deleteuser")
	@ResponseBody
	@Transactional
	public int delUser(long id) {
		int roleNum = sysUser_RoleServiceImpl.deleRoleByUserId(id);
		int num = sysUserServiceImpl.delUser(id);
		return num;
	}

	// 条件查询
	@RequestMapping(method = RequestMethod.GET, value = "/selectByUserNameOrPassword")
	@ResponseBody
	@Transactional
	public int selectByUserNameOrPassword(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String userPassword) {
		int roleNum = sysUserServiceImpl.selectByUserNameOrPassword(userName, userPassword);

		return roleNum;
	}

	// 条件查询
	@RequestMapping(method = RequestMethod.GET, value = "/selectByUserNameOrPassword2")
	@ResponseBody
	@Transactional
	public int selectByUserNameOrPassword2(@RequestParam(required = false) String userName,
			@RequestParam(required = false) String userPassword, @RequestParam(required = false) String userEmail) {
		int roleNum = sysUserServiceImpl.selectByUserNameOrPassword2(userName, userPassword, userEmail);

		return roleNum;
	}

	//
	@RequestMapping(method = RequestMethod.GET, value = "/selectUserRolePrivilege")
	@ResponseBody
	@Transactional
	public SysUser selectUserRolePrivilege(long id) {
		SysUser sysUser = sysUserServiceImpl.selectUserRolePrivilege(id);

		return sysUser;
	}
}
