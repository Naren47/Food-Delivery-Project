package com.genpact.fd.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.genpact.fd.entity.Cart;
import com.genpact.fd.entity.OrderSystem;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.service.CartService;
import com.genpact.fd.service.UserService;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@GetMapping("/Cart/{id}")
	public String OrderFoodItem(@PathVariable Long id,Model model) throws FoodDeliveryException{
		if(userService.getCurrentUser()!=null) {
			cartService.addToCart(id);
			return "redirect:/GetAllFoodItems";
		}else {
			return "start";
		}			
	}
	
	@GetMapping("/Cart/list")
	public String getAllOrdersofUser(Model model) throws FoodDeliveryException{
		if(userService.getCurrentUser()!=null) {
			List<Cart>cartList=cartService.getAllOrdersOfUsers();
			model.addAttribute("cartList", cartList);
			return "viewCartItem";
		}else {
			return "start";
		}
	}
	
	@GetMapping("/Cart/delete/{id}")
	public String removeItemFromCart(@PathVariable Long id,Model model) throws FoodDeliveryException {
		cartService.removeById(id);
		return "redirect:/Cart/list";
	}
}
