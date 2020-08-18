package com.tt.ui.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysRole implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String roleName;
	private int enabled;
	private long createBy;
	private Date createTime;
	private List<SysUser> sysUserList;
	private List<SysPrivilege> sysPrivilegeList;

	public long getId() {
		return id;
	}

	public List<SysPrivilege> getSysPrivilegeList() {
		return sysPrivilegeList;
	}

	public void setSysPrivilegeList(List<SysPrivilege> sysPrivilegeList) {
		this.sysPrivilegeList = sysPrivilegeList;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<SysUser> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		this.sysUserList = sysUserList;
	}

}
