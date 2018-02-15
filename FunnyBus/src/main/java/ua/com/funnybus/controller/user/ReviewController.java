package ua.com.funnybus.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.form.ReviewForm;
import ua.com.funnybus.service.ReviewService;
import ua.com.funnybus.validator.ReviewValidator;

@Controller
@RequestMapping("/reviews")
@SessionAttributes("review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@InitBinder("review")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new ReviewValidator());
	}
	@ModelAttribute("review")
	public ReviewForm getForm(){
		return new ReviewForm();
	}
	@GetMapping
	public String show(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable){
		model.addAttribute("page", reviewService.findAll(pageable, true));
		return "user-review";
	}
	
	@PostMapping
	public String save(@ModelAttribute("review") @Valid ReviewForm form,BindingResult br, Model model, SessionStatus status, @PageableDefault(direction=Sort.Direction.DESC, sort="id", size=25) Pageable pageable){
		if(br.hasErrors()) return show(model, pageable);
		reviewService.save(form);
		status.setComplete();
		return "redirect:/reviews";
	}
}