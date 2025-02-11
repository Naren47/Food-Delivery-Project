package com.genpact.fd.service;

import java.util.List;

import com.genpact.fd.entity.Cart;
import com.genpact.fd.exception.FoodDeliveryException;

public interface CartService {

	public void addToCart(Long id) throws FoodDeliveryException;

	public List<Cart> getAllOrdersOfUsers() throws FoodDeliveryException;
	
	public Cart getCartItemById(Long id) throws FoodDeliveryException;

	public void removeById(Long id) throws FoodDeliveryException;

}
