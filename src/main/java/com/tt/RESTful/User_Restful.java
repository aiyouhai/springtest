package com.tt.RESTful;

public class User_Restful {
	private Long id;
	private String name;
	private Integer age;
	private User_address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public User_address getAddress() {
		return address;
	}

	public void setAddress(User_address address) {
		this.address = address;
	}

}
