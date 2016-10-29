package com.cloudcomputing.usermanage.service;

import java.util.Collection;

import com.cloudcomputing.usermanage.model.User;

public interface UserService {
	
	Collection<User> findAll();
	
	User findOne(String username);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	void deleteUser(String username);
}
