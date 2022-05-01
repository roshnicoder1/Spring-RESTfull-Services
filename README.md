# Spring-RESTfull-Services

## Annotations Used

* @SpringBootApplication
* @RestController   
* @Autowired  
* @GetMapping("/users/{id}")  
* @PathVariable 
* @Component  
* @RequestBody
* @PostMapping("/user")
* @ResponseStatus 
* @ControllerAdvice
* @DeleteMapping("/users/{id}")  
	

# @SpringBootApplication annotation. 

This single annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 
# @RestController   
@RestController is a specialized version of the controller. It includes the @Controller and @ResponseBody

The controller is annotated with the @RestController annotation; therefore, the @ResponseBody isn't required.

# If used @Controller

@Controller
@RequestMapping("books")
public class SimpleBookController {

    @GetMapping("/{id}", produces = "application/json")
    public @ResponseBody Book getBook(@PathVariable int id) {
        return findBookById(id);
    }

    private Book findBookById(int id) {
        // ...
    }
}

# @Autowired  

```
To use Java-based configuration in our application, let's enable annotation-driven injection to load our Spring configuration:
```
```
@Configuration
@ComponentScan("com.baeldung.autowire.sample")
public class AppConfig {}

```
```
Alternatively, the <context:annotation-config> annotation is mainly used to activate the dependency injection annotations in Spring XML files.
```
@SpringBootApplication annotation. This single annotation is equivalent to using @Configuration, @EnableAutoConfiguration, and @ComponentScan.

Spring Boot application, it will automatically scan the components in the current package and its sub-packages. Thus it will register them in Spring's Application Context, and allow us to inject beans using @Autowired.

```
Using @Autowired
After enabling annotation injection, we can use autowiring on properties, setters, and constructors.

* @Autowired on Properties
Let’s see how we can annotate a property using @Autowired. This eliminates the need for getters and setters.

First, let's define a fooFormatter bean:

@Component("fooFormatter")
public class FooFormatter {
    public String format() {
        return "foo";
    }
}
Then, we'll inject this bean into the FooService bean using @Autowired on the field definition:

@Component
public class FooService {  
    @Autowired
    private FooFormatter fooFormatter;
}
As a result, Spring injects fooFormatter when FooService is created.

* @Autowired on Setters
Now let's try adding @Autowired annotation on a setter method.

In the following example, the setter method is called with the instance of FooFormatter when FooService is created:

public class FooService {
    private FooFormatter fooFormatter;
    @Autowired
    public void setFooFormatter(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }
}

* @Autowired on Constructors
Finally, let's use @Autowired on a constructor.

We'll see that an instance of FooFormatter is injected by Spring as an argument to the FooService constructor:

public class FooService {
    private FooFormatter fooFormatter;
    @Autowired
    public FooService(FooFormatter fooFormatter) {
        this.fooFormatter = fooFormatter;
    }
}

@Autowired and Optional Dependencies
When a bean is being constructed, the @Autowired dependencies should be available. Otherwise, if Spring cannot resolve a bean for wiring, it will throw an exception.

Consequently, it prevents the Spring container from launching successfully with an exception of the form:

Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: 
No qualifying bean of type [com.autowire.sample.FooDAO] found for dependency: 
expected at least 1 bean which qualifies as autowire candidate for this dependency. 
Dependency annotations: 
{@org.springframework.beans.factory.annotation.Autowired(required=true)}
To fix this, we need to declare a bean of the required type:

public class FooService {
    @Autowired(required = false)
    private FooDAO dataAccessor; 
}


Autowire Disambiguation
By default, Spring resolves @Autowired entries by type. If more than one bean of the same type is available in the container, the framework will throw a fatal exception.

To resolve this conflict, we need to tell Spring explicitly which bean we want to inject.

* Autowiring by @Qualifier
For instance, let's see how we can use the @Qualifier annotation to indicate the required bean.


First, we'll define 2 beans of type Formatter:

@Component("fooFormatter")
public class FooFormatter implements Formatter {
    public String format() {
        return "foo";
    }
}
@Component("barFormatter")
public class BarFormatter implements Formatter {
    public String format() {
        return "bar";
    }
}
Now let's try to inject a Formatter bean into the FooService class:

public class FooService {
    @Autowired
    private Formatter formatter;
}
In our example, there are two concrete implementations of Formatter available for the Spring container. As a result, Spring will throw a NoUniqueBeanDefinitionException exception when constructing the FooService:

Caused by: org.springframework.beans.factory.NoUniqueBeanDefinitionException: 
No qualifying bean of type [com.autowire.sample.Formatter] is defined: 
expected single matching bean but found 2: barFormatter,fooFormatter
We can avoid this by narrowing the implementation using a @Qualifier annotation:

public class FooService {
    @Autowired
    @Qualifier("fooFormatter")
    private Formatter formatter;
}
When there are multiple beans of the same type, it's a good idea to use @Qualifier to avoid ambiguity.

Please note that the value of the @Qualifier annotation matches with the name declared in the @Component annotation of our FooFormatter implementation.

 Autowiring by Custom Qualifier
Spring also allows us to create our own custom @Qualifier annotation. To do so, we should provide the @Qualifier annotation with the definition:

@Qualifier
@Target({
  ElementType.FIELD, ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatterType {  
    String value();
}
Then we can use the FormatterType within various implementations to specify a custom value:


@FormatterType("Foo")
@Component
public class FooFormatter implements Formatter {
    public String format() {
        return "foo";
    }
}
@FormatterType("Bar")
@Component
public class BarFormatter implements Formatter {
    public String format() {
        return "bar";
    }
}
Finally, our custom Qualifier annotation is ready to use for autowiring:

@Component
public class FooService {  
    @Autowired
    @FormatterType("Foo")
    private Formatter formatter;
}
The value specified in the @Target meta-annotation restricts where to apply the qualifier, which in our example is fields, methods, types, and parameters.

```

# @GetMapping("/users/{id}")  

@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
The new approach makes it possible to shorten this simply to:

@GetMapping("/get/{id}")

# @GetMapping

```
@GetMapping("/get")
public @ResponseBody ResponseEntity<String> get() {
    return new ResponseEntity<String>("GET Response", HttpStatus.OK);
}
@GetMapping("/get/{id}")
public @ResponseBody ResponseEntity<String>
  getById(@PathVariable String id) {
    return new ResponseEntity<String>("GET Response : " 
      + id, HttpStatus.OK);
}

```
# @PostMapping
```
@PostMapping("/post")
public @ResponseBody ResponseEntity<String> post() {
    return new ResponseEntity<String>("POST Response", HttpStatus.OK);
}

```
# @PutMapping
```
@PutMapping("/put")
public @ResponseBody ResponseEntity<String> put() {
    return new ResponseEntity<String>("PUT Response", HttpStatus.OK);
}
```
# @DeleteMapping
```
@DeleteMapping("/delete")
public @ResponseBody ResponseEntity<String> delete() {
    return new ResponseEntity<String>("DELETE Response", HttpStatus.OK);
}
```
# @PathVariable 
```
the @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.

@PathVariable annotation would be an endpoint that identifies an entity with a primary key:

@GetMapping("/api/employees/{id}")
@ResponseBody
public String getEmployeesById(@PathVariable String id) {
    return "ID: " + id;
}
In this example, we use the @PathVariable annotation to extract the templated part of the URI, represented by the variable {id}.

In the previous example, we skipped defining the name of the template path variable since the names for the method parameter and the path variable were the same.

However, if the path variable name is different, we can specify it in the argument of the @PathVariable annotation:

@GetMapping("/api/employeeswithvariable/{id}")
@ResponseBody
public String getEmployeesByIdWithVariableName(@PathVariable("id") String employeeId) {
    return "ID: " + employeeId;
}
http://localhost:8080/api/employeeswithvariable/1 
---- 
ID: 1
We can also define the path variable name as @PathVariable(value=”id”) instead of PathVariable(“id”) for clarity.

 we can have more than one path variable in our request URI for a controller method, which also has multiple method parameters:

@GetMapping("/api/employees/{id}/{name}")
@ResponseBody
public String getEmployeesByIdAndName(@PathVariable String id, @PathVariable String name) {
    return "ID: " + id + ", name: " + name;
}
http://localhost:8080/api/employees/1/bar 
---- 
ID: 1, name: bar

We can also handle more than one @PathVariable parameter using a method parameter of type java.util.Map<String, String>:

@GetMapping("/api/employeeswithmapvariable/{id}/{name}")
@ResponseBody
public String getEmployeesByIdAndNameWithMapVariable(@PathVariable Map<String, String> pathVarsMap) {
    String id = pathVarsMap.get("id");
    String name = pathVarsMap.get("name");
    if (id != null && name != null) {
        return "ID: " + id + ", name: " + name;
    } else {
        return "Missing Parameters";
    }
}

https://www.baeldung.com/spring-pathvariable

```


# @Component  


https://www.tutorialspoint.com/difference-between-bean-and-component-annotation-in-spring

In most typical applications, we have distinct layers like data access, presentation, service, business, etc.

Additionally, in each layer we have various beans. To detect these beans automatically, Spring uses classpath scanning annotations.

Then it registers each bean in the ApplicationContext.

@Component is a generic stereotype for any Spring-managed component.
@Service annotates classes at the service layer.
@Repository annotates classes at the persistence layer, which will act as a database repository.

# @Component
We can use @Component across the application to mark the beans as Spring's managed components. Spring will only pick up and register beans with @Component, and doesn't look for @Service and @Repository in general.

They are registered in ApplicationContext because they are annotated with @Component:


freestar
@Component
public @interface Service {
}
@Component
public @interface Repository {
}
@Service and @Repository are special cases of @Component. They are technically the same, but we use them for the different purposes.

# @Repository
@Repository’s job is to catch persistence-specific exceptions and re-throw them as one of Spring’s unified unchecked exceptions.

For this, Spring provides PersistenceExceptionTranslationPostProcessor, which we are required to add in our application context (already included if we're using Spring Boot):

<bean class=
  "org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor"/>
This bean post processor adds an advisor to any bean that’s annotated with @Repository.

# @Service
We mark beans with @Service to indicate that they're holding the business logic. Besides being used in the service layer, there isn't any other special use for this annotation.

https://www.youtube.com/watch?v=zJBfC96EYd8

# @RequestBody
The @RequestBody annotation maps body of the web request to the method parameter. The body of the request is passed through an HttpMessageConverter. It resolves the method argument depending on the content type of the request. Optionally, automatic validation can be applied by annotating the argument with @Valid.

# @PostMapping
The @PostMapping annotation is the specialized version of the @RequestMapping annotation which acts as a shortcut for @RequestMapping(method=RequestMethod=POST). @PostMapping method handles the Http POST requests matched with the specified URI.

# @ResponseStatus annotation available on custom exceptions and to map these exceptions to HTTP status codes.

Such a custom exception may look like:

```
package com.ibm.rest.webservices.restfulwebservices.user;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;  

@ResponseStatus(HttpStatus.NOT_FOUND)  
public class UserNotFoundException extends RuntimeException   
{  
public UserNotFoundException(String message)   
{  
super(message);  
}  

}


```

# @ControllerAdvice is an annotation provided by Spring allowing you to write global code that can be applied to a wide range of controllers — varying from all controllers to a chosen package or even a specific annotation. 
Spring 3.2 brings support for a global @ExceptionHandler with the @ControllerAdvice annotation.

This enables a mechanism that breaks away from the older MVC model and makes use of ResponseEntity along with the type safety and flexibility of @ExceptionHandler:

```
@ControllerAdvice
public class RestResponseEntityExceptionHandler 
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value 
      = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
      RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, 
          new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}

```
The@ControllerAdvice annotation allows us to consolidate our multiple, scattered @ExceptionHandlers from before into a single, global error handling component.

The actual mechanism is extremely simple but also very flexible:

It gives us full control over the body of the response as well as the status code.
It provides mapping of several exceptions to the same method, to be handled together.
It makes good use of the newer RESTful ResposeEntity response.
One thing to keep in mind here is to match the exceptions declared with @ExceptionHandler to the exception used as the argument of the method.

If these don't match, the compiler will not complain — no reason it should — and Spring will not complain either.

However, when the exception is actually thrown at runtime, the exception resolving mechanism will fail with:


java.lang.IllegalStateException: No suitable resolver for argument [0] [type=...]
HandlerMethod details: ...

# @DeleteMapping("/users/{id}")  
	public void deleteUser(@PathVariable int id)  
	{  
	User user= service.deleteById(id);  
	if(user==null)  
	//runtime exception  
	throw new UserNotFoundException("id: "+ id);  
	}  
