package com.genpact.fd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.service.UserService;
import com.genpact.fd.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private User currentUser=null;
	
	@Autowired
	private UserRepository UserRepository;
	
	
	public UserServiceImpl(UserRepository userRepository) throws FoodDeliveryException{
		super();
		UserRepository = userRepository;
	}
	
	@Override
	public List<User> getAllUsers() throws FoodDeliveryException{
		return UserRepository.findAll();
	}
	
	@Override
	public void saveUser(User user) throws FoodDeliveryException{
		Boolean flag=true;
		List<User>allUsers=UserRepository.findAll();
		for(User users:allUsers) {
			if(user.getNumber().equals(users.getNumber())) {
				flag=false;
			}
		}
		if(flag) {
			UserRepository.save(user);
		}else {
			throw new FoodDeliveryException("Mobile Number is Already Exist");
		}
	}
	
	@Override
	public User getUserById(Long id) throws FoodDeliveryException{
		return UserRepository.findById(id).get();
	}
	@Override
	public void deleteUserById(Long id) throws FoodDeliveryException{
		try {
			UserRepository.deleteById(id);
		}catch(Exception e) {
			throw new FoodDeliveryException("User Not Exist");
		}
	}
		
	public User getUserbyNumberandPassword(Long number,String password) throws FoodDeliveryException{
		return UserRepository.Findnumberandpassword(number, password);
	}

	@Override
	public Boolean isPresent(long number, String password) throws FoodDeliveryException{
		User user=UserRepository.Findnumberandpassword(number, password);
		if(user!=null) {
			currentUser=user;
			return true;
		}else {
			return false;
		}
	}

	@Override
	public User getCurrentUser() throws FoodDeliveryException{
		return currentUser;
	}
	
	public void ClearCurrentUser() throws FoodDeliveryException {
		currentUser=null;
	}

	public boolean isNumberPresent(Long number) throws FoodDeliveryException{
		List<User>allUsers=UserRepository.findAll();
		for(User user:allUsers) {
			if(user.getNumber().equals(number)) {
				return true;
			}
		}
		return false;
	}


	@Override
	public List<User> findByfirstNameContainingIgnoreCase(String search) {
		// TODO Auto-generated method stub
		List<User> searchedUsersList=UserRepository.findByfirstNameContainingIgnoreCase(search);
		return searchedUsersList;
	}	
}
