package com.tt.utils;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

//匹配用户拥有权限和请求权限(MyAccessDecisionManager:decide)
public class MyAccessDecisionManager implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}

		Iterator<ConfigAttribute> ite = configAttributes.iterator();

		while (ite.hasNext()) {

			ConfigAttribute ca = ite.next();
			// needRole 为访问相应的资源应该具有的权限。
			String needRole = ((SecurityConfig) ca).getAttribute();

			// ga 为用户所被赋予的权限。
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needRole.trim().contains(ga.getAuthority().trim())) {
					return;
				}

			}

		}
		throw new AccessDeniedException("没有权限");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		if (attribute.toString().startsWith("ROLE_")) {
			return true;
		}
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
