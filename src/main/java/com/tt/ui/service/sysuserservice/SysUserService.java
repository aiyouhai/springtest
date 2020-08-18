package com.tt.ui.service.sysuserservice;

import java.util.List;

import com.tt.ui.pojo.SysRole;
import com.tt.ui.pojo.SysUser;

public interface SysUserService {

	List<SysRole> selectRoleByUserId(long id);

	int addUser(SysUser user);

	SysUser selectOneById(long id);

	int updateSysUser(SysUser sysUser);

	int delUser(long id);

	int selectByUserNameOrPassword(String userName, String userPassword);

	int selectByUserNameOrPassword2(String userName, String userPassword, String userEmail);

	SysUser selectUserRolePrivilege(long id);

	List<SysUser> selectPageHelper(int pageNum, int pageSize);

	int selectcount();

}
