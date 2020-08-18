package com.tt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tt.ui.pojo.SysUser;
import com.tt.ui.repository.SysUserRepository;

@Component
public class TestDomain {
	@Autowired
	private SysUserRepository sysUserRepository;
	/*
	 * public static TestDomain testDomain;
	 * 
	 * @PostConstruct public void init() { testDomain = this;
	 * testDomain.sysUserService = this.sysUserService;
	 * 
	 * }
	 */

	public SysUser selectOneById(long id) {
		SysUser selectOneById = sysUserRepository.selectOneById(id);
		return selectOneById;
	}

}
