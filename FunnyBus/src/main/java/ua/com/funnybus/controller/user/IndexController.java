package ua.com.funnybus.controller.user;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(Principal principal){ 
		if(principal!=null){
			System.out.println(principal.getName());
		}
		return "user-index";
	}
	@RequestMapping("/admin")
	public String admin(){
		return "admin-admin";
	}
	
	@GetMapping("/booking")
	public String booking(){
		return "user-booking";
	}
	@GetMapping("/login")
	public String login(){
		return "admin-login";
	}
	//--------------------------------------------
	@GetMapping("/dostavkaPeredach")
	public String dostavkaPeredach(){
		return "user-dostavkaPeredach";
	}
	@GetMapping("/mikroAuvobys")
	public String mikroAuvobys(){
		return "user-mikroAuvobys";
	}
	@GetMapping("/pravulaPerevezenna")
	public String pravulaPerevezenna(){
		return "user-pravulaPerevezenna";
	}
	@GetMapping("/rent")
	public String rent(){
		return "user-rent";
	}
	
	@GetMapping("/reusu")
	public String reusu(){
		return "user-reusu";
	}
	
	@GetMapping("/kontakt")
	public String kontakt(){
		return "user-kontakt";
	}
	
}
