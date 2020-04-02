package com.niit.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.inventory.model.Address;
import com.niit.inventory.model.Dealer;
import com.niit.inventory.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService lservice;
	
	@RequestMapping("/")
	public String viewHomePage() {

	return "index";
	}
	
	@RequestMapping("/register")
	public String viewRegisterPage(Model model) {
	Dealer dealer = new Dealer();
	model.addAttribute("dealer", dealer);
	return "register";

	}
	
	@PostMapping("/saveDealer") //saving the dealer
	public String saveDealer(HttpServletRequest req,@ModelAttribute("dealer") Dealer dealer) {
	String s=req.getParameter("street");//must be in dealer pojo
	String c=req.getParameter("city");
	int p=Integer.parseInt(req.getParameter("pincode"));

	Address a=new Address();//creating object of address pojo and calling setters
	a.setStreet(s);
	a.setCity(c);
	a.setPincode(p);

	dealer.setAddress(a);
	a.setDealer(dealer);
	lservice.saveDealer(dealer);
	return "index";
	}

	
	@GetMapping("/login")  //displaying loginform
	public String showLoginForm(Model theModel) {
	//Dealer d = new Dealer();
	//theModel.addAttribute("dealer", d);
	return "login";
	}

}
