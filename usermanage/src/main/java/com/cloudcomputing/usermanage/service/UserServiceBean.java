package com.cloudcomputing.usermanage.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudcomputing.usermanage.model.User;

@Service
public class UserServiceBean implements UserService {
	
	private static String username;
	private static Map<String, User> userMap;
	
	static{
		System.out.println("static ~~~~~~~~~~~~~~~~~~~~~~");
		if(userMap == null){
			userMap = new HashMap<>();
		}
		User user1 = new User();
		user1.setUsername("kagebunsineric@gmail.com");
		user1.setPassword("default");
		user1.setLocation("plano");
		
		User user2 = new User();
		user2.setUsername("xyz0508@gmail.com");
		user2.setPassword("default");
		user2.setLocation("dallas");
		
		userMap.put(user1.getUsername(), user1); 
		userMap.put(user2.getUsername(), user2); 
	}
	
	private static User save(User user){
		if(userMap.containsKey(user.getUsername()))
			userMap.put(user.getUsername(), user);
		userMap.put(user.getUsername(), user); 
		return user;
	}
	
	private static boolean remove(String username){
		User removedUser = userMap.remove(username);
		return removedUser != null;
	}

	@Override
	public Collection<User> findAll() {
		Collection<User> users = userMap.values();
		return users;
	}

	@Override
	public User findOne(String username) {
		User user = userMap.get(username);
		return user;
	}

	@Override
	public User createUser(User user) {
		User savedUser = save(user);
		return savedUser;
	}

	@Override
	public User updateUser(User user) {
		userMap.put(user.getUsername(), user);
		return user;
	}

	@Override
	public void deleteUser(String username) {
		remove(username);
	}

}
