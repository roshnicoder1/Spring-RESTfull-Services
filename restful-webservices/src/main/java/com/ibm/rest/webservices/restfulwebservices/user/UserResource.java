package com.ibm.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.hateoas.EntityModel;
//static import
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;  
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;  

@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retriveAllUsers() {
		return service.findAll();
	}
	
	//retrieves a specific user detail  
//	@GetMapping("/users/{id}")  
//	public User retriveUser(@PathVariable int id)  
//	{  
//
//	User user= service.findOne(id);  
//	if(user==null)  
//	//runtime exception  
//	throw new UserNotFoundException("id: "+ id);  
//	return user;  
//	}  
	
	//retrieve user by using HATEOAS older version
	
//	@GetMapping("/users/{id}")
//	public Resource<User> retrieveUser(@PathVariable int id) {
//		User user = service.findOne(id);
//		
//		if(user==null)
//			throw new UserNotFoundException("id-"+ id);
//		
//		
//		//"all-users", SERVER_PATH + "/users"
//		//retrieveAllUsers
//		Resource<User> resource = new Resource<User>(user);
//		
//		ControllerLinkBuilder linkTo = 
//				linkTo(methodOn(this.getClass()).retrieveAllUsers());
//		
//		resource.add(linkTo.withRel("all-users"));
//		
//		//HATEOAS
//		
//		return resource;
//	}
//	
	
	//retrieve user by using HATEOAS new version
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);
		
		
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retriveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));
		
		//HATEOAS
		
		return resource;
	}


	
	//method that posts a new user detail   
//	@PostMapping("/users")  
//	public void createUser(@RequestBody User user)  
//	{  
//	User sevedUser=service.save(user);    
//	} 
	
	//method that posts a new user detail and returns the status of HTTP and location of the user resource  
	@PostMapping("/users")  
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)  
	{  
	User sevedUser=service.save(user);    
	URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sevedUser.getId()).toUri();  
	return ResponseEntity.created(location).build();  
	}  
	
	//method that delete a user resource  
	//if the user deleted successfully it returns status 200 OK otherwise 404 Not Found  
	@DeleteMapping("/users/{id}")  
	public void deleteUser(@PathVariable int id)  
	{  
	User user= service.deleteById(id);  
	if(user==null)  
	//runtime exception  
	throw new UserNotFoundException("id: "+ id);  
	}  
	
	
	
}
