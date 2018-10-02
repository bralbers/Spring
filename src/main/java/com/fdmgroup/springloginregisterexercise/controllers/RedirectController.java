package com.fdmgroup.springloginregisterexercise.controllers;

import org.jboss.logging.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class RedirectController {
	
	private Logger myLogger = Logger.getLogger(RedirectController.class);
	
	@RequestMapping(value="/redirect")
	private String redirectClient(Model model) {
		myLogger.info("Redirect Page has been summoned");
		return "redirect";	
	}
}
