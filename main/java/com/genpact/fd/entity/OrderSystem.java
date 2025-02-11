package com.genpact.fd.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ordersystem")
public class OrderSystem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String FirstName;
	private String lastName;
	private Long number;
	private String FoodItem;
	private String status;
	private LocalDateTime dateAndTime;
	
	public OrderSystem() {}
	
	public OrderSystem(String firstName, String lastName, Long number, String foodItem, String status,LocalDateTime dateAndTime) {
		super();
		this.FirstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.FoodItem = foodItem;
		this.status = status;
		this.dateAndTime=dateAndTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
}
