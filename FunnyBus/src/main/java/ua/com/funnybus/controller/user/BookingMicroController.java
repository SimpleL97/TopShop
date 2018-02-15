package ua.com.funnybus.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import ua.com.dto.form.BookingForm;
import ua.com.funnybus.editor.TripEditor;
import ua.com.funnybus.entity.Trip;
import ua.com.funnybus.service.BookingService;
import ua.com.funnybus.service.TripService;
import ua.com.funnybus.validator.BookingValidator;

@Controller
@RequestMapping("/booking/micro")
@SessionAttributes("bookingMicro")
public class BookingMicroController {
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BookingService bookingService;
	
	@InitBinder("bookingMicro")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Trip.class, new TripEditor(tripService));
		binder.setValidator(new BookingValidator());
	}
	@ModelAttribute("bookingMicro")
	public BookingForm getForm(){
		return new BookingForm();
	}
	
	@GetMapping
	public String show(Model model){
		model.addAttribute("trips", tripService.findOnWay("Україна - Словаччина - Чехія"));
		return "user-micro";
	}
	@PostMapping
	public String save(@ModelAttribute("bookingMicro")@Valid BookingForm bookingForm,BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()) return show(model);
		bookingService.save(bookingForm);
		status.setComplete();
		return "redirect:/";
	}
}