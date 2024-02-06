package org.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.java.Log;

@Controller
@Log
public class MvcController {

	
	@GetMapping("/search")
	public String query(@RequestParam String q, Model model) {
		
		model.addAttribute("q",q);
		return "query";
	}
	
	@PostMapping("/ssrf/post")
	public String callRemote(@RequestParam String stockApi, Model model) {
		
		log.info("Calling remote service " + stockApi);
		model.addAttribute("api",stockApi);
		return "ssrf";
		
	}
}
