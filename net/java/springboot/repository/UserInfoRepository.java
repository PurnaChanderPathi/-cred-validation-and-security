package net.java.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.springboot.Entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	Optional<UserInfo> findByName(String username);

}
