package com.genpact.fd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.genpact.fd.entity.User;


public interface UserRepository extends JpaRepository<User,Long>{

	@Query("select user from User user where user.number=:number and user.password=:password")
	User Findnumberandpassword(@Param("number") Long number, @Param("password" )String password);
	
	User findUserByfirstName(@Param("firstname") String firstname);


	List<User> findByfirstNameContainingIgnoreCase(String search);;
}
