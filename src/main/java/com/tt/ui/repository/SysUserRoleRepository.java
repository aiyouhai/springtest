package com.tt.ui.repository;

import org.apache.ibatis.annotations.Param;

public interface SysUserRoleRepository {

	int addUser_Role(@Param("id") long id, @Param("sysRoleId") long sysRoleId);

	int deleRoleByUserId(long id);

	int selectByUserNameOrPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

}
