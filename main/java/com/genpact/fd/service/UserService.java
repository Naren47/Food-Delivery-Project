package com.genpact.fd.service;

import java.util.List;


import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;

public interface UserService {

	List<User> getAllUsers() throws FoodDeliveryException;

	void saveUser(User user) throws FoodDeliveryException;

	User getUserById(Long id) throws FoodDeliveryException;

	void deleteUserById(Long id) throws FoodDeliveryException;

	public Boolean isPresent(long number,String password) throws FoodDeliveryException;
	
	public User getUserbyNumberandPassword(Long number,String password) throws FoodDeliveryException;

	public User getCurrentUser() throws FoodDeliveryException;
	
	public void ClearCurrentUser() throws FoodDeliveryException;

	boolean isNumberPresent(Long number) throws FoodDeliveryException;

	
	public List<User> findByfirstNameContainingIgnoreCase(String search);
}
