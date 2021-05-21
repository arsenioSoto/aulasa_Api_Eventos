package soto.api.eventos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
	System.out.println("Chamou o metodo index");
		return "redirect:/eventos";
	}

}
