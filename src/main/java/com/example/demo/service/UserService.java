package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {

	//create
	User createUser(User user);
	
	//getAll
	List<User> getAllUser();
	
	//getSingleUser
	User getUserById(int userId);
	
	//getByUserName
	User getUserByEmail(String email);
	
	//Save user
	public String saveUser(User user);
}
