package net.java.springboot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.java.springboot.Entity.UserInfo;
import net.java.springboot.Exception.UserNotFoundException;
import net.java.springboot.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserInfoRepository infoRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<UserInfo> userInfo = infoRepository.findByName(username);
		 return userInfo.map(UserInfoUserDetails::new)
		.orElseThrow(()-> new UsernameNotFoundException("user not found with username :"+username));
		 
	}

}
