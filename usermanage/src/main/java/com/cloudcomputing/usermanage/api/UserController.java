package com.cloudcomputing.usermanage.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudcomputing.usermanage.model.User;
import com.cloudcomputing.usermanage.service.UserServiceBean;

@RestController
public class UserController {	
	
	@Autowired
	private UserServiceBean userService;
	
	@RequestMapping(
		value = "/api/users", 
		method = RequestMethod.GET, 
		produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> users = userService.findAll();
		return new ResponseEntity<Collection<User>>(users,
						HttpStatus.OK);
				
	}
	
	@RequestMapping(
			value = "/api/users/{username:.*}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("username") String username){
		User user = userService.findOne(username);
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);		
	}
	
	@RequestMapping(
			value = "/api/users",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = userService.createUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
	@RequestMapping(
			value = "/api/users/{username:.*}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User oldUser = userService.updateUser(user);
		if(oldUser == null){
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}
    	
	@RequestMapping(
			value = "/api/users/{username:.*}", 
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username){
		userService.deleteUser(username);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);		
	}
	
}
