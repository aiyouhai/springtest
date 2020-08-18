package com.tt.RESTful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class User_RestfulController {

	@Autowired
	User_Restfulservice user_RestfulServiceImpl;

	@GetMapping("/user")
	@ResponseBody
	public List<User_Restful> getUser_Restful() {
		List<User_Restful> userList = user_RestfulServiceImpl.getUserRe();
		return userList;

	}

	@GetMapping("/user/{id}")
	@ResponseBody
	public User_Restful getUser_RestfulOne(@PathVariable int id) {
		User_Restful user = user_RestfulServiceImpl.getUserReOne(id);
		return user;

	}

	@GetMapping("/userAddress/{id}")
	@ResponseBody
	public User_Restful getUser_RestfulAndInfo(@PathVariable int id) {
		User_Restful user = user_RestfulServiceImpl.getUserReAndAddre(id);
		return user;

	}

}
