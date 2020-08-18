package com.tt.ui.service.CustomUserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tt.ui.pojo.SysRole;
import com.tt.ui.pojo.SysUser;
import com.tt.ui.repository.SysUserRepository;

public class CustomUserService implements UserDetailsService {
	@Autowired
	private SysUserRepository sysUserRepository;

	// 重写loadUserByUsername 方法获得 userdetails 类型用户
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		SysUser user = sysUserRepository.findByUserName(username);
		if (user == null) {

			throw new BadCredentialsException("帐号不存在，请重新输入");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		if (null == user.getSysRoleList() || user.getSysRoleList().isEmpty())
			throw new UsernameNotFoundException("权限不足!");
		// 用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
		for (SysRole role : user.getSysRoleList()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			System.out.println(role.getRoleName());
		}
		System.out.println(user.getUserName() + "------" + user.getUserPassword());
		return new User(user.getUserName(), user.getUserPassword(), authorities);

	}

}
