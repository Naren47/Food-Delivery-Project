package com.genpact.fd.service;

import java.util.List;

import com.genpact.fd.entity.OrderSystem;
import com.genpact.fd.exception.FoodDeliveryException;

public interface OrderSystemService {
	void addToOrderSystem(Long id) throws FoodDeliveryException;
	
	public List<OrderSystem> getAllOrdersOfUsers() throws FoodDeliveryException;
	
	List<OrderSystem> getAllOrdersOfUserss() throws FoodDeliveryException;

	public void addToOrderSystemFromCart(Long id) throws FoodDeliveryException;
	
	public List<OrderSystem> ordersOfUsers(Long number);
}
