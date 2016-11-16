package com.cloudcomputing.usermanage.service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.cloudcomputing.database.GaeConnection;
import com.cloudcomputing.usermanage.model.User;

@Service
public class UserServiceBean implements UserService {
	
	private static String username;
	private static GaeConnection gc=new GaeConnection();
	
	
	static{
		System.out.println("static ~~~~~~~~~~~~~~~~~~~~~~");
		User user1 = new User();
		user1.setUsername("himanshiaggarwal16893@gmail.com");
		user1.setPassword("default");
		user1.setLocation("plano");
		User user2 = new User();
		user2.setUsername("xyz0608@gmail.com");
		user2.setPassword("default");
		user2.setLocation("dallas");
		try {
			gc.saveUser(user1);
			gc.saveUser(user2);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	private static User save(User user) throws ClassNotFoundException, SQLException{
		if(gc.findoneUser(user.getUsername())!=null){
			gc.updateUser(user);
		}
		gc.saveUser(user);
		return user;
	}
	
	private static boolean remove(String username) throws ClassNotFoundException, SQLException{
		String removedUser = gc.removeUser(username);
		return removedUser != null;
	}

	@Override
	public Collection<User> findAll() {
		Collection<User> users;
		try {
			users = gc.findallUser();
			return users;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

	@Override
	public User findOne(String username) {
		try {
			User user1=gc.findoneUser(username);
			return user1;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User createUser(User user) {
		User savedUser;
		try {
			savedUser = save(user);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			savedUser=null;
		}
		return savedUser;
	}

	@Override
	public User updateUser(User user) {
		try {
			gc.updateUser(user);
			return user;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deleteUser(String username) {
		try {
			remove(username);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
