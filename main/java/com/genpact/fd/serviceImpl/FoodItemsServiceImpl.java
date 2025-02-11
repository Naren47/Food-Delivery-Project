package com.genpact.fd.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.repository.FoodItemsRepository;
import com.genpact.fd.service.FoodItemsService;

@Service
public class FoodItemsServiceImpl implements FoodItemsService{
	
	@Autowired
	private FoodItemsRepository foodItemsRepository;
	
	private List<FoodItems>carlist=new ArrayList<>();
	

	@Override
	public List<FoodItems> getAllFoodItems() throws FoodDeliveryException{
		// TODO Auto-generated method stub
		return foodItemsRepository.findAll();
	}

	@Override
	public FoodItems saveFoodItem(FoodItems foodItems) throws FoodDeliveryException{
		// TODO Auto-generated method stub
		return foodItemsRepository.save(foodItems);
	}

	@Override
	public FoodItems getFoodItemById(Long id) throws FoodDeliveryException{
		// TODO Auto-generated method stub
		return foodItemsRepository.findById(id).get();
	}

	@Override
	public void deleteFoodItemById(Long id) throws FoodDeliveryException {
		// TODO Auto-generated method stub
		foodItemsRepository.deleteById(id);;
	}

	@Override
	public void addToCart(Long id) throws FoodDeliveryException {
		carlist.add(foodItemsRepository.findById(id).get());
	}
	
	public List<FoodItems> viewCart() throws FoodDeliveryException {
		return carlist;
	}

	@Override
	public void removeItemById(Long id) throws FoodDeliveryException {
		
		// TODO Auto-generated method stub
		for(FoodItems item:carlist) {
			if(item.getId().equals(id)) {
				carlist.remove(item);
			}
		}
		
	}
	public List<FoodItems> searchProductsByName(String name) {
        return foodItemsRepository.findByNameContainingIgnoreCase(name);
    }

	@Override
	public Optional<FoodItems> findById(Long id) throws FoodDeliveryException{
		
		return foodItemsRepository.findById(id);
	}
	
}
