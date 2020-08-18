package com.tt.utils;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


import org.springframework.util.StringUtils;



@SuppressWarnings("serial")
//1.标注为@MappedSuperclass的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
//2.标注为@MappedSuperclass的类不能再标注@Entity或@Table注解，也无需实现序列化接口。
@MappedSuperclass
public class AbstractEntity implements Serializable{
	private String id;
	private Date createDate;
	private Date modifyDate;
	
	@Id
	@Column(name = "ID", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@PrePersist
	public void prePersistBase() {
		if (StringUtils.isEmpty(getCreateDate())) {
			setCreateDate(new Date());
		}
	}

	@PreUpdate
	public void preUpdateBase() {
		if (StringUtils.isEmpty(getModifyDate())) {
			setModifyDate(new Date());
		}
	}

}
