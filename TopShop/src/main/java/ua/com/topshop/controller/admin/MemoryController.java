package ua.com.topshop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.filter.SimpleFilter;
import ua.com.topshop.entity.Memory;
import ua.com.topshop.service.MemoryService;
import ua.com.topshop.validator.MemoryValidator;
import static ua.com.topshop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/memory")
@SessionAttributes("memory")
public class MemoryController {
	
	@Autowired
	private MemoryService memoryService;
	
	@ModelAttribute("memory")
	public Memory getForm(){
		return new Memory();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@InitBinder("memory")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new MemoryValidator(memoryService));
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", memoryService.findAll(pageable, filter));
		return "admin-memory";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,  @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("memory", memoryService.findOne(id));
		return show(model, pageable, filter);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		memoryService.delete(id);
		return "redirect:/admin/memory"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("memory") @Valid Memory memory, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		memoryService.save(memory);
		status.setComplete();
		return "redirect:/admin/memory"+getParams(pageable, filter);
	}
}
