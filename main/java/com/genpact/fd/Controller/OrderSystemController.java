package com.genpact.fd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.entity.OrderSystem;
import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.service.OrderSystemService;
import com.genpact.fd.service.UserService;
import com.genpact.fd.serviceImpl.OrderSystemServiceImpl;

@Controller
public class OrderSystemController {
	@Autowired
	private OrderSystemService orderSystemService;
	@Autowired
	private UserService userService;
	@Autowired
	private OrderSystemServiceImpl orderSystemServiceImpl;
	
	@GetMapping("/OrderSystem/{id}")
	public String OrderFoodItem(@PathVariable Long id,Model model) throws FoodDeliveryException{
		if(userService.getCurrentUser()!=null) {
			orderSystemService.addToOrderSystem(id);
			return "redirect:/GetAllFoodItems";
		}else {
			return "start";
		}			
	}
	
	@GetMapping("/OrderSystem/GetOrders")
	public String getAllOrdersofUser(Model model) throws FoodDeliveryException{
		if(userService.getCurrentUser()!=null) {
			List<OrderSystem>orderList=orderSystemService.getAllOrdersOfUsers();
			model.addAttribute("orderList", orderList);
			return "viewRecentOrder";
		}else {
			return "start";
		}
	}
	
	@GetMapping("/AdminOrderRespond")
	public String getAllOrders(Model model) throws FoodDeliveryException{
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==9945787190L) {
				List<OrderSystem>orderList=orderSystemService.getAllOrdersOfUserss();
				model.addAttribute("orderList", orderList);
				return "AdminOrderRespond";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}
	}
	
	
	
	@GetMapping("/Order/accept/{id}")
	public String orderAcceptByAdmin(@PathVariable Long id,Model model) throws FoodDeliveryException{
		orderSystemServiceImpl.changeAcceptStatus(id);
		return "redirect:/AdminOrderRespond";
	}
	
	
	@GetMapping("/Order/reject/{id}")
	public String orderRejectByAdmin(@PathVariable Long id,Model model) throws FoodDeliveryException {
		orderSystemServiceImpl.changeRejectStatus(id);
		return "redirect:/AdminOrderRespond";
	}
	
	@GetMapping("/fromCartToOrder/{id}")
	public String orderedFromCart(@PathVariable Long id,Model model) throws FoodDeliveryException {
		if(userService.getCurrentUser()!=null) {
			orderSystemService.addToOrderSystemFromCart(id);
			return "redirect:/Cart/list";
		}else {
			return "start";
		}			
		
	}
	@GetMapping("/Orders/{number}")
	public String adminShowUsersOrder(@PathVariable Long number,Model model) {
		model.addAttribute("orders",orderSystemService.ordersOfUsers(number));
		return "OrdersOfUser";
		
	}
}


