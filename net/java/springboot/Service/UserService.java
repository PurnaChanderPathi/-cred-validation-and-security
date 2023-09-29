package net.java.springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.java.springboot.Entity.UserInfo;
import net.java.springboot.Exception.UserNotFoundException;
import net.java.springboot.dto.UserRequest;
import net.java.springboot.model.User;
import net.java.springboot.repository.UserInfoRepository;
import net.java.springboot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public User saveUser(UserRequest userRequest)
	{
		User user=User.build(0, userRequest.getName(), userRequest.getEmail(),
				userRequest.getMobile(), userRequest.getGender(),
				userRequest.getAge(), userRequest.getNationality());
		return userRepository.save(user);	
	}
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	
	  public User getUser(int id) throws UserNotFoundException
	 { User user=userRepository.findById(id).get();
	 if(user !=null)
	 {
	 return user;
	 }
	 else
	 {
		 throw new UserNotFoundException("User not found with this id: "+id);
	 }
	 }
	  
	  public User getUserByName(String name) throws UserNotFoundException
	  { 
		  User user=userRepository.findByName(name);
		  if(user != null)
		  {
		  return user; 
		  }
		  else
		  {
			  throw new UserNotFoundException("User not found with this name: "+name);
		  }
		  }
	 
	public String addUser(UserInfo userInfo)
	{
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		userInfoRepository.save(userInfo);
		return "user added to system";
	}
	

}
