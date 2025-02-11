package com.genpact.fd.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.fd.entity.Cart;
import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.entity.OrderSystem;
import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.repository.FoodItemsRepository;
import com.genpact.fd.repository.OrderSystemRepository;
import com.genpact.fd.service.OrderSystemService;
import com.genpact.fd.service.CartService;
@Service
public class OrderSystemServiceImpl implements OrderSystemService  {
	@Autowired
	private OrderSystemRepository orderSystemRepository;
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private FoodItemsRepository foodItemsRepository;
	@Autowired
	private CartService CartService;
	User user=new User();
	OrderSystem orderSystem=new OrderSystem();
	@Override
	public void addToOrderSystem(Long id) throws FoodDeliveryException{
		try {
			FoodItems foodItems=new FoodItems();
			foodItems=foodItemsRepository.findById(id).get();
			OrderSystem orderSystem=new OrderSystem();
			user=userServiceImpl.getCurrentUser();
			orderSystem.setFirstName(user.getFirstName());
			orderSystem.setLastName(user.getLastName());
			orderSystem.setNumber(user.getNumber());
			orderSystem.setFoodItem(foodItems.getName());
			orderSystem.setDateAndTime(LocalDateTime.now());
			orderSystem.setStatus("Pending");
			orderSystemRepository.save(orderSystem);
		}catch(Exception e) {
			throw new FoodDeliveryException("Item May Be Deleted or Not Exist");
		}
		
	}
	
	public List<OrderSystem> getAllOrdersOfUsers() throws FoodDeliveryException{
		List<OrderSystem>ordersList=orderSystemRepository.findAll();
		List<OrderSystem>filteredList=new ArrayList<>();
		user=userServiceImpl.getCurrentUser();
		for(OrderSystem o:ordersList) {
			if(o.getNumber().equals(user.getNumber())) {
				filteredList.add(o);
			}
		}
		return filteredList;
	}
	
	public List<OrderSystem> getAllOrdersOfUserss()throws FoodDeliveryException{
		List<OrderSystem>ordersList=orderSystemRepository.findAll();
		return ordersList;
		
	}
	
	public void changeAcceptStatus(Long id) throws FoodDeliveryException{
	 String accept="accept";
	 OrderSystem OrderSystemlist=orderSystemRepository.findById(id).get();
	 OrderSystemlist.setStatus(accept);
	 orderSystemRepository.save(OrderSystemlist);
	}
	
	public void changeRejectStatus(Long id) throws FoodDeliveryException{
		 String accept="Reject";
		 OrderSystem OrderSystemlist=orderSystemRepository.findById(id).get();
		 OrderSystemlist.setStatus(accept);
		 orderSystemRepository.save(OrderSystemlist);
		}

	
	public void addToOrderSystemFromCart(Long id) throws FoodDeliveryException{
		try {
			Cart cart=new Cart();
			cart=CartService.getCartItemById(id);
			OrderSystem orderSystem=new OrderSystem();
			user=userServiceImpl.getCurrentUser();
			orderSystem.setFirstName(cart.getFirstName());
			orderSystem.setLastName(cart.getLastName());
			orderSystem.setNumber(cart.getNumber());
			orderSystem.setFoodItem(cart.getFoodItem());
			orderSystem.setStatus("Pending");
			orderSystem.setDateAndTime(LocalDateTime.now());
			orderSystemRepository.save(orderSystem);
			CartService.removeById(id);
		}catch(Exception e) {
			throw new FoodDeliveryException("Cart Item Is Not Available or Removed");
		}
	}
	
	public List<OrderSystem> ordersOfUsers(Long number){
		List<OrderSystem>ordersList=orderSystemRepository.findAll();
		List<OrderSystem>filteredList=new ArrayList<>();
		for(OrderSystem o:ordersList) {
			if(o.getNumber().equals(number)) {
				filteredList.add(o);
			}
		}
		return filteredList;
	}

}
