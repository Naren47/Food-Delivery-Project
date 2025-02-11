package com.genpact.fd.service;

import java.util.List;
import java.util.Optional;

import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.exception.FoodDeliveryException;

public interface FoodItemsService {

	List<FoodItems> getAllFoodItems() throws FoodDeliveryException;

	FoodItems saveFoodItem(FoodItems foodItems) throws FoodDeliveryException;

	FoodItems getFoodItemById(Long id) throws FoodDeliveryException;

	void deleteFoodItemById(Long id) throws FoodDeliveryException;
	
	void addToCart(Long id) throws FoodDeliveryException;
	
	List<FoodItems> viewCart() throws FoodDeliveryException;

	void removeItemById(Long id) throws FoodDeliveryException;

	Optional<FoodItems> findById(Long id) throws FoodDeliveryException;
	
	public List<FoodItems> searchProductsByName(String name);
}
