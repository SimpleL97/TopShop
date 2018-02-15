package ua.com.topshop.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.topshop.entity.User;
import ua.com.topshop.service.GraphicService;
import ua.com.topshop.service.ProducerService;
import ua.com.topshop.service.UserService;
import ua.com.topshop.validator.UserValidator;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private GraphicService graphicService;
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}
	
	@RequestMapping("/producer/{id}") 
	public String category(@PathVariable int id, Model model){
		model.addAttribute("producer", producerService.findOne(id));
		model.addAttribute("graphics", graphicService.findByProducerId(id));
		return "user-producer";
	}
	
	@RequestMapping("/graphic/{id}") 
	public String graphic(@PathVariable int id, Model model){
		model.addAttribute("graphic", graphicService.findOne(id));
		return "user-graphic";
	}
	
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@RequestMapping("/admin/systemConsole")
	public String systemConsole(){
		return "admin-systemConsole";
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user, BindingResult br, Model model){
		if(br.hasErrors()) return registration(model);
		userService.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "user-login";
	}
}
