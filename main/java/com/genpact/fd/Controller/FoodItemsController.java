package com.genpact.fd.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.genpact.fd.entity.FoodItems;
import com.genpact.fd.entity.User;
import com.genpact.fd.exception.FoodDeliveryException;
import com.genpact.fd.repository.FoodItemsRepository;
import com.genpact.fd.service.FoodItemsService;
import com.genpact.fd.service.UserService;

@Controller
public class FoodItemsController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FoodItemsService foodItemsService;

	public FoodItemsController(FoodItemsService foodItemsService) throws FoodDeliveryException {
		super();
		this.foodItemsService = foodItemsService;
	}
	
	@GetMapping("/FoodItems")
	public String listFoodItems(Model model) throws FoodDeliveryException{
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==6374395459L) {
				FoodItems foodItems=new FoodItems(); 
				model.addAttribute("foodItems",foodItemsService.getAllFoodItems() );
				return "FoodItems";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}
	}
	
	@GetMapping("/products")
	public String seachFoodItems(@RequestParam(name = "search", required = false) String search, Model model) throws FoodDeliveryException {
		List<FoodItems> foodItems;
		if(StringUtils.isEmpty(search)) {
			foodItems=foodItemsService.getAllFoodItems();	
		}else {
			foodItems=foodItemsService.searchProductsByName(search);
		}
		model.addAttribute("foodItems", foodItems);
		model.addAttribute("search", search);
		return "SearchedFoodItem";
	}
	
	@GetMapping("/FoodItems/new")
	public String createFoodItemForm(Model model) throws FoodDeliveryException{
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==6374395459L) {
				FoodItems foodItems=new FoodItems(); 
				model.addAttribute("foodItems",foodItems);
				return "addnewitem";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}
	}
	
	@GetMapping("/GetAllFoodItems")
	public String listFoodItemsView(Model model) throws FoodDeliveryException {
		model.addAttribute("foodItems",foodItemsService.getAllFoodItems() );
		return "FoodItemsView";
	}
	
	@PostMapping("/FoodItems")
	public String saveFoodItem(@ModelAttribute("foodItems") FoodItems foodItems,@RequestParam("file") MultipartFile file) throws FoodDeliveryException, IOException {
		if (file != null && !file.isEmpty()) {
            foodItems.setImage(file.getBytes());
        }
		foodItemsService.saveFoodItem(foodItems);
		return "redirect:/FoodItems";
	}

	@GetMapping("/FoodItems/edit/{id}")
	public String editFoodItemForm(@PathVariable Long id,Model model) throws FoodDeliveryException{
		model.addAttribute("foodItem", foodItemsService.getFoodItemById(id));
		return "editfooditem";
	}
	
	@PostMapping("/FoodItems/{id}")
	public String updateSFoodItem(@PathVariable Long id,@ModelAttribute("foodItems") FoodItems foodItems,Model model) throws FoodDeliveryException {
		FoodItems existingfoodItems=foodItemsService.getFoodItemById(id);
		existingfoodItems.setId(id);
		existingfoodItems.setName(foodItems.getName());
		existingfoodItems.setType(foodItems.getType());
		existingfoodItems.setPrice(foodItems.getPrice());
		foodItemsService.saveFoodItem(foodItems);
		return "redirect:/FoodItems";
	}
	
	@GetMapping("/FoodItems/{id}")
	public String deleteFoodItem(@PathVariable Long id) throws FoodDeliveryException{
		User user=userService.getCurrentUser();
		if(user!=null) {
			if(user.getNumber()==6374395459L) {
				foodItemsService.deleteFoodItemById(id);
				return "redirect:/FoodItems";
			}else {
				throw new FoodDeliveryException("You are Not Allowed To Access The Page");
			}
		}else{
			return "start";
	}
	}
	
	@GetMapping("/FoodItems/addToCart{id}")
	public String addToCart(@PathVariable Long id) throws FoodDeliveryException {
		foodItemsService.addToCart(id);
		return "redirect:/GetAllFoodItems";
	}
	
	@GetMapping("/EmptyCart")
	public String EmptyCart() {
		return "EmptyCart";
	}
	@GetMapping("/FoodItems/viewCart")
	public String viewCart(Model model) throws FoodDeliveryException {
		List<FoodItems>items=foodItemsService.viewCart();
		String url="";
		if(items.size()==0) {
			model.addAttribute("message","Oops ,your Cart is Empty");
			url= "/EmptyCart";
		}else {
			model.addAttribute("items",items);
			url= "viewCart";
		}
		return url;
	}
	
	 @GetMapping("/food/image/{id}")
	 @ResponseBody
	 public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws FoodDeliveryException{
	        Optional<FoodItems> optionalFoodItem = foodItemsService.findById(id);

	        if (optionalFoodItem.isPresent()) {
	            FoodItems foodItem = optionalFoodItem.get();
	            byte[] image = foodItem.getImage();

	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG); // Change the media type based on your image type

	            return new ResponseEntity<>(image, headers, HttpStatus.OK);
	        }

	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	
	@GetMapping("/delete/item/{id}")
	public String deleteItemFromCart(@PathVariable Long id,Model model) throws FoodDeliveryException{
		foodItemsService.removeItemById(id);
		List<FoodItems>items=foodItemsService.viewCart();
		try {
			String url="";
			if(items.size()==0) {
				model.addAttribute("message","Oops ,your Cart is Empty");
				url= "/EmptyCart";
			}else {
				model.addAttribute("items",items);
				url= "redirect:/FoodItems/viewCart";
			}
			return url;
		}catch(Exception e) {
			return "login";
		}

	}
}
