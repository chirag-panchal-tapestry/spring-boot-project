package com.rahul.savingbank.rahulsavingbank.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.rahul.savingbank.rahulsavingbank.model.Account;
import com.rahul.savingbank.rahulsavingbank.model.User;
import com.rahul.savingbank.rahulsavingbank.utils.ConstantValues;

@Controller
@RequestMapping(value = ConstantValues.USER_BASE)
public class UserController {

	protected RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping(value = ConstantValues.USER_LOGIN)
	public ModelAndView loginPage(Model model) {
		User user = new User();
		return new ModelAndView(ConstantValues.USER_LOGIN_VIEW).addObject(user);
	}

	@GetMapping(value = ConstantValues.USER_REGISTER)
	public ModelAndView registerPage() {
		User user = new User();
		return new ModelAndView(ConstantValues.USER_REGISTER_VIEW).addObject(user);
	}

	@PostMapping(value = ConstantValues.USER_REGISTERED)
	public String registered(@ModelAttribute("user") User user) {
		return ConstantValues.USER_LOGIN;
	}

	@PostMapping(value = ConstantValues.USER_LOGGED_IN)
	public String loggedin(@ModelAttribute("user") User user) {
		System.out.println(restTemplate.getForObject("http://localhost:8888/account", Account.class));
		
		return ConstantValues.USER_HOME;
	}
}
