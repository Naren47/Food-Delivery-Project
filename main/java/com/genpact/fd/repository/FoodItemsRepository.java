package com.genpact.fd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genpact.fd.entity.FoodItems;

public interface FoodItemsRepository extends JpaRepository<FoodItems,Long>{

	List<FoodItems> findByNameContainingIgnoreCase(String name);

}
