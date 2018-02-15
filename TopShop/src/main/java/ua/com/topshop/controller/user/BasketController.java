package ua.com.topshop.controller.user;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.topshop.entity.Orderr;
import ua.com.topshop.entity.User;
import ua.com.topshop.service.OrderrService;
import ua.com.topshop.service.UserService;

@Controller
@RequestMapping("/basket")
@SessionAttributes("basket")
public class BasketController {

	@Autowired
	private OrderrService orderService;
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("basket")
	public Orderr getForm(){
		return new Orderr();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		orderService.delete(id);
		return "redirect:/basket";
	}
	
	@GetMapping("/plus/{id}")
	public String plus(@PathVariable int id){
		Orderr order = orderService.findOne(id);
		int amount=order.getAmount();
		amount++;
		order.setAmount(amount);
		orderService.save(order);
		return "redirect:/basket";
	}
	@GetMapping("/minus/{id}")
	public String minus(@PathVariable int id){
		Orderr order = orderService.findOne(id);
		int amount=order.getAmount();
		if(amount>1){
			amount--;
		}
		order.setAmount(amount);
		orderService.save(order);
		return "redirect:/basket";
	}
	
	@GetMapping
	public String basket(Model model, Principal principal){ 
	if(principal!=null){ 
	User user = userService.findByEmail(principal.getName()); 
	int userId =user.getId(); 
	model.addAttribute("orders", orderService.findUserFlag(userId, false)); 
	model.addAttribute("sum", orderService.sum(userId, false));
	} 
	return "user-basket"; 
	}
	
	@GetMapping("/accept")
	public String accept(Principal principal){
		User user = userService.findByEmail(principal.getName());
		List<Orderr> orders = orderService.findUserFlag(user.getId(), false);
		for (int i = 0; i < orders.size(); i++) {
			Orderr order = orders.get(i);
			order.setFlag(true);
			orderService.save(order);
		}
		return "redirect:/";
	}
}
