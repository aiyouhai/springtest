package com.tt.ui.service.sysuser_roleservice;

public interface SysUser_RoleService {

	int addUser_Role(long id, long sysRoleId);

	int deleRoleByUserId(long id);

	int selectByUserNameOrPassword(String userName, String userPassword);

}
