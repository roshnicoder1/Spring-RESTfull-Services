package com.ibm.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world") instead can use @GetMapping(path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloworldBean() {
		return new HelloWorldBean("Hello World !");
	}
	//@PathVariable use this to bind that is typed in url to this method 
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloworldPathVar(@PathVariable String name ) {
		return new HelloWorldBean(String.format("Hello World, %s ", name));
	}

}
