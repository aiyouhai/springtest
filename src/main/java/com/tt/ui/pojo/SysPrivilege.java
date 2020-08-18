package com.tt.ui.pojo;

import java.util.List;

public class SysPrivilege {
	private long id;
	private String privilegeName;
	private String privilegeUrl;
	private List<SysRole> sysRoleList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPrivilegeName() {
		return privilegeName;
	}

	public void setPrivilegeName(String privilegeName) {
		this.privilegeName = privilegeName;
	}

	public String getPrivilegeUrl() {
		return privilegeUrl;
	}

	public void setPrivilegeUrl(String privilegeUrl) {
		this.privilegeUrl = privilegeUrl;
	}

	public List<SysRole> getSysRoleList() {
		return sysRoleList;
	}

	public void setSysRoleList(List<SysRole> sysRoleList) {
		this.sysRoleList = sysRoleList;
	}

}
