package com.tt.ui.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tt.ui.pojo.SysRole;
import com.tt.ui.pojo.SysUser;

public interface SysUserRepository {

	List<SysRole> selectRoleByUserId(long id);

	int addUser(SysUser user);

	SysUser selectOneById(long id);

	int updateSysUser(SysUser sysUser);

	int deleUser(long id);

	int selectByUserNameOrPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

	int selectByUserNameOrPassword2(@Param("userName") String userName, @Param("userPassword") String userPassword,
			@Param("userEmail") String userEmail);

	SysUser selectUserRolePrivilege(long id);

	List<SysUser> selectPageHelper(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

	int selectcount();

	SysUser findByUserName(String username);

}
