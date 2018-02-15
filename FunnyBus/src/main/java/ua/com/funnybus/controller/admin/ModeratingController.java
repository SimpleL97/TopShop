package ua.com.funnybus.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.funnybus.editor.UserEditor;
import ua.com.funnybus.entity.Review;
import ua.com.funnybus.entity.User;
import ua.com.funnybus.service.ReviewService;
import ua.com.funnybus.service.UserService;

@Controller
@RequestMapping("/admin/moderating")
@SessionAttributes("moderating")
public class ModeratingController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReviewService reviewService;
	
	@InitBinder("moderating")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(User.class, new UserEditor(userService));
	}
	@ModelAttribute("moderating")
	public Review getForm(){
		return new Review();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(direction=Sort.Direction.ASC, sort="id", size=50) Pageable pageable){
		model.addAttribute("page", reviewService.findAll(pageable, false));
		model.addAttribute("users", userService.findAll());
		return "admin-moderating";
	}
	
	@GetMapping("/accept/{id}")
	public String accept(@PathVariable int id){
		Review review = reviewService.findOne(id, false);
		review.setFlag(true);
		reviewService.update(review);
		return "redirect:/admin/moderating";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		reviewService.delete(id);
		return "redirect:/admin/moderating";
	}
}
