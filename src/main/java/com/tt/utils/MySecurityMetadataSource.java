package com.tt.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.tt.ui.pojo.SysPrivilege;
import com.tt.ui.pojo.SysRole;
import com.tt.ui.repository.SysPrivilegeRepository;

public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	protected final Log logger = LogFactory.getLog(getClass());

	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

	@Resource
	private SysPrivilegeRepository sysPrivilegeRepository;

//找到具体某个路径对应哪些角色
	public void init() {
		Map<RequestMatcher, Collection<ConfigAttribute>> result = new HashMap<RequestMatcher, Collection<ConfigAttribute>>();
		List<SysPrivilege> sysPrivileges = (List<SysPrivilege>) sysPrivilegeRepository.findAllSysPrivilege();
		if (!sysPrivileges.isEmpty()) {
			for (SysPrivilege sysPrivilege : sysPrivileges) {
				RequestMatcher requestMatcher = new AntPathRequestMatcher(sysPrivilege.getPrivilegeUrl());
				if (!sysPrivilege.getSysRoleList().isEmpty()) {
					List<SysRole> sysRoleList = sysPrivilege.getSysRoleList();
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					for (SysRole role : sysRoleList) {
						configAttributes.add(new SecurityConfig(role.getRoleName()));
					}
					result.put(requestMatcher, configAttributes);
				}
			}
			requestMap = result;
		} else {
			requestMap = new HashMap<RequestMatcher, Collection<ConfigAttribute>>();
		}
	}

	// 获取当前请求url的角色
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		Collection<ConfigAttribute> results = new ArrayList<ConfigAttribute>();
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		if (null == requestMap || requestMap.size() <= 0) {
			init();
		}
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		results.add(new SecurityConfig("ROLE_USER"));
		return results;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	// 获取所欲有url分别对应的角色
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

}
