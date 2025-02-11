package com.genpact.fd.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.fd.entity.Cart;
import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.entity.OrderSystem;
import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.repository.CartRepository;
import com.genpact.fd.repository.FoodItemsRepository;
import com.genpact.fd.repository.OrderSystemRepository;
import com.genpact.fd.service.CartService;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	private UserServiceImpl userServiceImpl;
	@Autowired
	private FoodItemsRepository foodItemsRepository;
	User user=new User();
	OrderSystem orderSystem=new OrderSystem();
	@Autowired
	private CartRepository cartRepository;
	public void addToCart(Long id) throws FoodDeliveryException {
		try {
			Cart cart=new Cart();
			FoodItems foodItems=new FoodItems();
			foodItems=foodItemsRepository.findById(id).get();
			user=userServiceImpl.getCurrentUser();
			cart.setFirstName(user.getFirstName());
			cart.setLastName(user.getLastName());
			cart.setNumber(user.getNumber());
			cart.setFoodItem(foodItems.getName());
			cart.setPrice(foodItems.getPrice());
			cartRepository.save(cart);
		}catch(Exception e) {
			throw new FoodDeliveryException("Item May be Deleted");
		}
		
	}
	
	public List<Cart> getAllOrdersOfUsers() throws FoodDeliveryException{
		List<Cart>cartList=cartRepository.findAll();
		List<Cart>filteredList=new ArrayList<>();
		user=userServiceImpl.getCurrentUser();
		for(Cart c:cartList) {
			if(c.getNumber().equals(user.getNumber())) {
				filteredList.add(c);
			}
		}
		return filteredList;
	}
	
	public Cart getCartItemById(Long id) throws FoodDeliveryException {
		return cartRepository.findById(id).get();
	}

	@Override
	public void removeById(Long id) throws FoodDeliveryException {
		cartRepository.deleteById(id);
		
	}
	
}
