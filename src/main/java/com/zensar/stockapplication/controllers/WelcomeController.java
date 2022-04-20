package com.zensar.stockapplication.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	//http://localhost:9090/test
	@RequestMapping(value ="/test", method= {RequestMethod.GET,RequestMethod.POST})
	public void test() {
		System.out.println("Iam inside test method of Welcomecontroller ");
	}
}
