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
import org.springframework.data.domain.Sort;

import ua.com.dto.filter.GraphicFilter;
import ua.com.dto.form.GraphicForm;
import ua.com.topshop.editor.MemoryEditor;
import ua.com.topshop.editor.ProducerEditor;
import ua.com.topshop.entity.Memory;
import ua.com.topshop.entity.Producer;
import ua.com.topshop.service.GraphicService;
import ua.com.topshop.service.MemoryService;
import ua.com.topshop.service.ProducerService;
import ua.com.topshop.util.ParamBuilder;
import ua.com.topshop.validator.GraphicValidator;

@Controller
@RequestMapping("/admin/graphic")
@SessionAttributes("graphic")
public class GraphicController {

	@Autowired
	private GraphicService graphicService;
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private MemoryService memoryService;
	
	@ModelAttribute("graphic")
	public GraphicForm getForm(){
		return new GraphicForm();
	}
	
	@InitBinder("graphic")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Memory.class, new MemoryEditor(memoryService));
		binder.registerCustomEditor(Producer.class, new ProducerEditor(producerService));
		binder.setValidator(new GraphicValidator(graphicService));
	}
	
	@ModelAttribute("filter")
	public GraphicFilter getFilter(){
		return new GraphicFilter();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") GraphicFilter filter){
		model.addAttribute("page", graphicService.findAll(pageable, filter));
		model.addAttribute("producers", producerService.findAll());
		model.addAttribute("memories", memoryService.findAll());
		return "admin-graphic";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") GraphicFilter filter){
		model.addAttribute("graphic", graphicService.findForm(id));
		return show(model, pageable, filter);
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") GraphicFilter filter){
		graphicService.delete(id);
		return "redirect:/admin/graphic"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("graphic") @Valid GraphicForm form,BindingResult br, Model model, SessionStatus status, @PageableDefault(direction=Sort.Direction.DESC, sort="id") Pageable pageable, @ModelAttribute("filter") GraphicFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		graphicService.save(form);
		status.setComplete();
		return "redirect:/admin/graphic"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, GraphicFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getMaxPrice().isEmpty()){
			builder.append("&maxPrice=");
			builder.append(filter.getMaxPrice());
		}
		if(!filter.getMinPrice().isEmpty()){
			builder.append("&minPrice=");
			builder.append(filter.getMinPrice());
		}
		
		if(!filter.getMaxFrequency().isEmpty()){
			builder.append("&maxFrequency=");
			builder.append(filter.getMaxFrequency());
		}
		if(!filter.getMinFrequency().isEmpty()){
			builder.append("&minFrequency=");
			builder.append(filter.getMinFrequency());
		}
		
		if(!filter.getMaxData_bus().isEmpty()){
			builder.append("&maxDataBus=");
			builder.append(filter.getMaxData_bus());
		}
		if(!filter.getMinData_bus().isEmpty()){
			builder.append("&minDataBus=");
			builder.append(filter.getMinData_bus());
		}
		
		if(!filter.getMaxMemory_value().isEmpty()){
			builder.append("&maxMemoryValue=");
			builder.append(filter.getMaxMemory_value());
		}
		if(!filter.getMinMemory_value().isEmpty()){
			builder.append("&minMemoryValue=");
			builder.append(filter.getMinMemory_value());
		}
		
		if(!filter.getMemoryId().isEmpty()){
			for (int id : filter.getMemoryId()) {
				builder.append("&memoryId=");
				builder.append(id);
			}
		}
		if(!filter.getProducerId().isEmpty()){
			for (int id : filter.getProducerId()) {
				builder.append("&producerId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
}

