package com.tt.ui.service.sysuser_roleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.ui.repository.SysUserRoleRepository;

@Service
public class SysUser_RoleServiceImpl implements SysUser_RoleService {
	@Autowired
	private SysUserRoleRepository sysUserRoleRepository;

	@Override
	public int addUser_Role(long id, long sysRoleId) {
		int num = sysUserRoleRepository.addUser_Role(id, sysRoleId);
		return num;
	}

	@Override
	public int deleRoleByUserId(long id) {
		int num = sysUserRoleRepository.deleRoleByUserId(id);
		return num;
	}

	@Override
	public int selectByUserNameOrPassword(String userName, String userPassword) {
		int num = sysUserRoleRepository.selectByUserNameOrPassword(userName, userPassword);
		return num;
	}

}
