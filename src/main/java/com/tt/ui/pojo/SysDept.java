package com.tt.ui.pojo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "sys_dept")
public class SysDept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "JDBC")
	private long id;
	private String deptName;
	@Transient
	private List<SysYg> yg;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<SysYg> getYg() {
		return yg;
	}

	public void setYg(List<SysYg> yg) {
		this.yg = yg;
	}

}
