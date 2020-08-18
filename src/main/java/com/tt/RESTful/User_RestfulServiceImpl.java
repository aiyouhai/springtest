package com.tt.RESTful;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_RestfulServiceImpl implements User_Restfulservice {

	@Autowired
	User_RestfulRepository user_RestfulRepository;

	@Override
	public List<User_Restful> getUserRe() {
		List<User_Restful> userList = user_RestfulRepository.getUserRe();
		return userList;
	}

	@Override
	public User_Restful getUserReOne(int id) {
		User_Restful user = user_RestfulRepository.getUserReOne(id);
		return user;
	}

	@Override
	public User_Restful getUserReAndAddre(int id) {
		User_Restful user = user_RestfulRepository.getUserReAndAddre(id);
		return user;
	}

}
