package com.tt.ui.pojo;

import java.util.List;

public class RoleAndPrivilegeUrl {
	private List<String> roleNameList;
	private String privilegeUrl;

	public List<String> getRoleNameList() {
		return roleNameList;
	}

	public void setRoleNameList(List<String> roleNameList) {
		this.roleNameList = roleNameList;
	}

	public String getPrivilegeUrl() {
		return privilegeUrl;
	}

	public void setPrivilegeUrl(String privilegeUrl) {
		this.privilegeUrl = privilegeUrl;
	}

}
