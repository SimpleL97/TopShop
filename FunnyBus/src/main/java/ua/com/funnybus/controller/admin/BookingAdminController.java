package ua.com.funnybus.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.funnybus.entity.Booking;
import ua.com.funnybus.service.BookingService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("bookingAdmin")
public class BookingAdminController {
	
	@Autowired
	private BookingService bookingService;
	
	@ModelAttribute("bookingAdmin")
	public Booking getForm(){
		return new Booking();
	}
	
	@GetMapping("/bus")
	public String ukraine(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id", size=20) Pageable pageable){
		model.addAttribute("page", bookingService.findAllOnWay("Городенка - Івано-Франківськ - Прага",pageable));
		return "admin-bookingBus";
	}
	
	@GetMapping("/bus/delete/{id}")
	public String deleteUa(@PathVariable int id){
		bookingService.delete(id);
		return "redirect:/admin/bus";
	}
	
	@GetMapping("/micro")
	public String czech(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id", size=20) Pageable pageable){
		model.addAttribute("page", bookingService.findAllOnWay("Україна - Словаччина - Чехія",pageable));
		return "admin-bookingMicro";
	}
	
	@GetMapping("/micro/delete/{id}")
	public String deleteCh(@PathVariable int id){
		bookingService.delete(id);
		return "redirect:/admin/micro";
	}
}
