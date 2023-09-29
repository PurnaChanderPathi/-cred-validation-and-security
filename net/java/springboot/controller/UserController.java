package net.java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import net.java.springboot.Entity.UserInfo;
import net.java.springboot.Exception.UserNotFoundException;
import net.java.springboot.Service.UserService;
import net.java.springboot.dto.UserRequest;
import net.java.springboot.model.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest)
	{
		return new ResponseEntity<>(userService.saveUser(userRequest),HttpStatus.CREATED);
		
	}
	@GetMapping("/getAllUsers")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<User>> getAllUsers()
	{
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	  @GetMapping("/{id}")
	  @PreAuthorize("hasAuthority('ROLE_USER')")
	  public ResponseEntity<User> getUser(@PathVariable int
	  id) throws UserNotFoundException 
	  { 
		  return ResponseEntity.ofNullable(userService.getUser(id)); 
	  }
	  
	  @GetMapping("/byname/{name}") 
	  public ResponseEntity<User> getUserByName(@PathVariable String name) throws UserNotFoundException 
	  { 
		  return ResponseEntity.ok(userService.getUserByName(name)); 
	  }
	 
	  @PostMapping("/newUser")
	  public String addNewUser(@RequestBody UserInfo userInfo)
	  {
		  return userService.addUser(userInfo);
	  }
	
	

}
