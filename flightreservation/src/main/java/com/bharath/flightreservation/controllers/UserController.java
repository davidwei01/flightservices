package com.bharath.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bharath.flightreservation.entity.User;
import com.bharath.flightreservation.repos.UserRepository;
import com.bharath.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	@RequestMapping(value="registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register(), use is " + user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}
	
	@GetMapping("/showLogin")
	public String showLogin() {
		return "login/login";
	}
	
	@RequestMapping(value="/login", method= { RequestMethod.POST })
	public String login(@RequestParam("email") String username, @RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.info("Inside login(). Username is " + username + " password is " + password);
		boolean loginResponse = securityService.login(username, password);
		if (loginResponse) {
			return "findFlight";
		} else {
			modelMap.addAttribute("msg", "user name doesn't match password, pls try again.");
		}
		return "login/login";
	}

}
