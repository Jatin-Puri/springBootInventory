package com.niit.inventory.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.inventory.model.Address;
import com.niit.inventory.model.Dealer;
import com.niit.inventory.model.Product;
import com.niit.inventory.service.LoginService;
import com.niit.inventory.service.ProductService;
@Controller
public class LoginController {
	
	@Autowired
	private LoginService lservice;
	
	@Autowired
	private ProductService service;
	
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

	@PostMapping("/loginDealer")
	public ModelAndView loginDealer(HttpServletRequest req,@ModelAttribute("dealer") Dealer dealer) {
		String email=req.getParameter("email");
		String pass=req.getParameter("password");
	    String pass2=encryptPass(pass);
		
		StringTokenizer st = new StringTokenizer(email, "@");
		String s2 = st.nextToken();
		
		 ModelAndView mav=null;
		 Dealer d = lservice.findByEmail(email);
		 
		 if(d==null) {
			 mav= new ModelAndView("login");
				mav.addObject("error", "User Doesn't Exists");
		 }
		 else  if(email.equals(d.getEmail()) && pass2.equals(d.getPassword()))
		 {
					 
		 req.getSession().setAttribute("user", s2);	
		 
		  mav = new ModelAndView("products");
		 mav.addObject("dealer", d);
		 
		 List<Product> listProducts = service.listAll();
		    mav.addObject("listProducts", listProducts);
	     		    
		 } 
		 
		 else
		 {mav= new ModelAndView("login");
			mav.addObject("error", "Invalid Username or Password");
		 }
		 
		 return mav;
	}
	
	private String encryptPass(String pass) {
		Base64.Encoder encoder = Base64.getEncoder();
		String normalString = pass;
		String encodedString = encoder.encodeToString(
	    normalString.getBytes(StandardCharsets.UTF_8) );
		return encodedString;
	}
	
	
	
	@GetMapping("/logout")//code for logout
	public String logout(HttpServletRequest req) {
	req.getSession().invalidate();
	return "index";
	}
	
}
