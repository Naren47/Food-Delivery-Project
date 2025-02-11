package com.genpact.fd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String FirstName;
	private String lastName;
	private Long number;
	private String FoodItem;
	private Double price;
	
	public Cart() {}
	
	
	
	public Cart(String firstName, String lastName, Long number, String foodItem, Double price) {
		super();
		FirstName = firstName;
		this.lastName = lastName;
		this.number = number;
		FoodItem = foodItem;
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getFoodItem() {
		return FoodItem;
	}
	public void setFoodItem(String foodItem) {
		FoodItem = foodItem;
	}
	
}
