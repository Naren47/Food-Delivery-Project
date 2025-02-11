package com.genpact.fd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.entity.Login;
import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/Users")
	public String listUsers(Model model) throws FoodDeliveryException {
		model.addAttribute("users",userService.getAllUsers() );
		return "Users";
	}
	
	@GetMapping("/Users/new")
	public String createUser(Model model) throws FoodDeliveryException {
		User user=new User(); 
		model.addAttribute("user",user);
		return "addnewUser";	
	}
	
	@PostMapping("/Users")
	public String saveUser(@ModelAttribute("user") User user) throws FoodDeliveryException{
		userService.saveUser(user);
		return "redirect:/Users";
	}

	@GetMapping("/Users/edit/{id}")
	public String editUser(@PathVariable Long id,Model model) throws FoodDeliveryException{
		model.addAttribute("user", userService.getUserById(id));
		return "edituser";
	}
	
	@PostMapping("/Users/{id}")
	public String updateUser(@PathVariable Long id,@ModelAttribute("user") User user,Model model) throws FoodDeliveryException{
		User existingUer=userService.getUserById(id);
		
		existingUer.setId(id);
		existingUer.setFirstName(user.getFirstName());
		existingUer.setLastName(user.getLastName());
		existingUer.setNumber(user.getNumber());
		existingUer.setAddress(user.getAddress());
		existingUer.setPincode(user.getPincode());
		userService.deleteUserById(id);
		userService.saveUser(existingUer);
		return "redirect:/Users";
	}
	
	@GetMapping("/Users/{id}")
	public String deleteUser(@PathVariable Long id) throws FoodDeliveryException{
		userService.deleteUserById(id);
		return "redirect:/Users";
	}
	
	@GetMapping("/UserRegistration")
	public String userRegistration(Model model) throws FoodDeliveryException{
		User user=new User();
		model.addAttribute("user", user);
		return "userRegistration";
	}
	
	@PostMapping("/UserRegistration")
	public String saveUserRegistration(@ModelAttribute("user") User user) throws FoodDeliveryException{
		if(userService.isNumberPresent(user.getNumber())) {
			return "redirect:/UserRegistration?failed";
		}else {
			userService.saveUser(user);
			return "redirect:/UserRegistration?success";
		}
	}
	
	@GetMapping("/start")
	public String Userlogin(Model model) throws FoodDeliveryException{
		Login login=new Login();
		model.addAttribute("login", login);
		return "start";
	}
	
	@GetMapping("/")
	public String Userstart(Model model) throws FoodDeliveryException{
		Login login=new Login();
		model.addAttribute("login", login);
		return "start";
	}
	
	@PostMapping("/userlogin")
	public String login(@ModelAttribute("login") Login login,Model model) throws FoodDeliveryException{
		if(userService.isPresent(login.getNumber(), login.getPassword())) {
			User existuser=userService.getUserbyNumberandPassword(login.getNumber(), login.getPassword());
			model.addAttribute("User", existuser);
			return "Profilepage";
		}else {
			return "redirect:/start?failed";
		}
	}
	
	@GetMapping("/logout")
	public String Userlogin() throws FoodDeliveryException{
		userService.ClearCurrentUser();
		return "start";
	}
	
	@GetMapping("/AdmissionConsole")
	public String adminConsole() throws FoodDeliveryException{
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==9945787190L) {
				return "AdmissionConsole";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}
}
	
	
	@GetMapping("/SearchByName")
	public String seachUsersByName(Model model) throws FoodDeliveryException {
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==9945787190L) {
				return "SearchUserDetailsByName";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}		
}
	
	@PostMapping("/SearchByName")
	public String seachedUsersByName(@RequestParam(name = "search", required = false) String search, Model model) throws FoodDeliveryException {
		List<User> users;
		if(StringUtils.isEmpty(search)) {
			users=userService.getAllUsers();	
		}else{
			users=userService.findByfirstNameContainingIgnoreCase(search);
		}
		model.addAttribute("users", users);
		model.addAttribute("search", search);
		return "SearchUserDetailsByName";
		
	}
	
}
