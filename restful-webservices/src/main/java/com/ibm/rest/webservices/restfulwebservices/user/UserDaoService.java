package com.ibm.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	 @Autowired
	    private UserRepository userRepository;
	 
	public static int usersCount = 5;
//creating an instance of ArrayList  
	private static List<User> users = new ArrayList<>();
//static block   
	static {
//adding users to the list  
		users.add(new User(1, "John", new Date()));
		users.add(new User(2, "Robert", new Date()));
		users.add(new User(3, "Adam", new Date()));
		users.add(new User(4, "Andrew", new Date()));
		users.add(new User(5, "Jack", new Date()));
	}

//method that retrieve all users from the list  
//	public List<User> findAll() {
//		return users;
//	}
	
	public List<User> findAll() {
		
		List<User> users = new ArrayList<User>();  
		userRepository.findAll().forEach(users1 -> users.add(users1));  
		return users; 
	}
	
	 

//method that add the user in the list   
//	public User save(User user) {
//		if (user.getId() == null) {
////increments the user id  
//			user.setId(++usersCount);
//		}
//		users.add(user);
//		return user;
//	}
	public User save(User user) {
		
		userRepository.save(user);  
		return user;
	}
	
	

//method that find a particular user from the list  
//	public User findOne(int id) {
//		for (User user : users) {
//			if (user.getId() == id)
//				return user;
//		}
//		return null;
//	}
	
	public User findOne(int id) {
		
		return userRepository.findById(id).get();  
	}
	
	

//method that delete a user resource  
//	public User deleteById(int id) {
//		Iterator<User> iterator = users.iterator();
//		while (iterator.hasNext()) {
//			User user = iterator.next();
//			if (user.getId() == id) {
//				iterator.remove();
//				return user; // returns the deleted resource back
//			}
//		}
//		return null;
//	}
	public void deleteById(int id) {
		 userRepository.deleteById(id);  
	}
	
	
}