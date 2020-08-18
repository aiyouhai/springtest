package com.tt.ui.service.sysuserservice;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tt.ui.pojo.SysRole;
import com.tt.ui.pojo.SysUser;
import com.tt.ui.repository.SysUserRepository;
import com.tt.ui.repository.SysUserRoleRepository;

@Service
public class SysUserServiceImpl implements SysUserService {
	private static Logger logger = Logger.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserRepository sysUserRepository;
	@Autowired
	private SysUserRoleRepository sysUserRoleRepository;

	@Override
	public List<SysRole> selectRoleByUserId(long id) {
		List<SysRole> roleList = sysUserRepository.selectRoleByUserId(id);
		return roleList;
	}

	@Override
	public int addUser(SysUser user) {
		int sysUser = sysUserRepository.addUser(user);
		return sysUser;
	}

	@Override
	public SysUser selectOneById(long id) {
		SysUser sysUser = sysUserRepository.selectOneById(id);
		return sysUser;
	}

	@Override
	public int updateSysUser(SysUser sysUser) {
		int num = sysUserRepository.updateSysUser(sysUser);
		return num;
	}

	@Override
	public int delUser(long id) {
		int num = sysUserRepository.deleUser(id);
		return num;
	}

	@Override
	public int selectByUserNameOrPassword(String userName, String userPassword) {
		int num = sysUserRepository.selectByUserNameOrPassword(userName, userPassword);
		return num;
	}

	@Override
	public int selectByUserNameOrPassword2(String userName, String userPassword, String userEmail) {
		int num = sysUserRepository.selectByUserNameOrPassword2(userName, userPassword, userEmail);
		return num;
	}

	@Override
	public SysUser selectUserRolePrivilege(long id) {

		SysUser sysUser = sysUserRepository.selectUserRolePrivilege(id);
		return sysUser;
	}

	@Override
	public List<SysUser> selectPageHelper(int pageNum, int pageSize) {
		int finallyPageNum = 0;
		if (pageNum == 0) {
			finallyPageNum = pageNum;
		} else {
			finallyPageNum = pageNum * pageSize;
		}

		List<SysUser> sysUserList = sysUserRepository.selectPageHelper(finallyPageNum, pageSize);

		return sysUserList;
	}

	@Override
	public int selectcount() {
		// TODO Auto-generated method stub
		return sysUserRepository.selectcount();
	}

}
