package ua.com.topshop.controller.user;

import java.security.Principal;
import java.util.List;

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

import ua.com.dto.filter.GraphicFilter;
import ua.com.topshop.entity.Graphic;
import ua.com.topshop.entity.Orderr;
import ua.com.topshop.entity.User;
import ua.com.topshop.service.GraphicService;
import ua.com.topshop.service.MemoryService;
import ua.com.topshop.service.OrderrService;
import ua.com.topshop.service.ProducerService;
import ua.com.topshop.service.UserService;
import ua.com.topshop.util.ParamBuilder;

@Controller
@RequestMapping("/")
@SessionAttributes("graph")
public class GraphicUserController {
	

	@Autowired
	private GraphicService graphicService;
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private MemoryService memoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderrService orderService;
	
	@ModelAttribute("graph")
	public Graphic getForm(){
		return new Graphic();
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
		return "user-graphics";
	}
	
	@GetMapping("/add/{id}")
	public String add(@PathVariable int id, Model model ,@PageableDefault(direction=Sort.Direction.DESC, sort="id", size=12) Pageable pageable, @ModelAttribute("filter") GraphicFilter filter, Principal principal){
		if(principal!=null){	
			User user = userService.findByEmail(principal.getName());
			List<Orderr> list=user.getOrders();
			for (int i = 0; i < list.size(); i++) {
				Orderr order1 = list.get(i);
				if(order1.getGraphic().equals(graphicService.findOne(id))){
					int amount=order1.getAmount();
					amount++;
					order1.setAmount(amount);
					orderService.save(order1);
					return "redirect:/"+getParams(pageable, filter);
				}
			}
			Orderr order = new Orderr();
			order.setAmount(1);
			order.setFlag(false);
			Graphic graphic = graphicService.findOne(id);
			order.setGraphic(graphic);
			order.setUser(user);
			orderService.save(order);
		}
		return "redirect:/"+getParams(pageable, filter);
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