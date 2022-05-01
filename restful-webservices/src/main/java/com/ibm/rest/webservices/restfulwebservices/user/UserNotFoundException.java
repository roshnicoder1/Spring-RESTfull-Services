package com.ibm.rest.webservices.restfulwebservices.user;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;  


//@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)  
public class UserNotFoundException extends RuntimeException   
{  
public UserNotFoundException(String message)   
{  
super(message);  
}  

}
