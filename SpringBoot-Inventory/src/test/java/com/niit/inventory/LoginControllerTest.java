package com.niit.inventory;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import com.niit.inventory.controller.LoginController;
import com.niit.inventory.model.Address;
import com.niit.inventory.model.Dealer;
import com.niit.inventory.service.LoginService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {LoginController.class})
public class LoginControllerTest {
	
	@InjectMocks
	LoginController loginController;
	
	@Mock
	private LoginService lservice;
	
	@Spy
	Model model;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void viewRegisterPageTest() {
		Assert.assertEquals(loginController.viewRegisterPage(model), "register");
	}
	@Test
	public void createDealerTest() {
		Dealer d = new Dealer();
		d.setId(1L);		
		d.setFname("Jatin");
		d.setEmail("xyz@gmail.com");
		d.setLname("");
		d.setPassword("root");
		d.setDob(java.sql.Date.valueOf("1996-07-30"));
		d.setPhoneNo("9560773578");
		
		Address a = new Address();
		a.setStreet("Rohini");
		a.setCity("New Delhi");
		a.setPincode(110089);
		
		
		d.setAddress(a);
		a.setDealer(d);
		
		lservice.saveDealer(d);
		verify(lservice,times(1)).saveDealer(d);
	}
	
	@Test
	public void loginDealerTest()
	{
	String email="jatin@gmail.com";
	Dealer d=new Dealer();
	d.setEmail("jatin@gmail.com");
	d.setPassword("1234");
	when(lservice.findByEmail(email)).thenReturn(d);

	Dealer x= lservice.findByEmail("jatin@gmail.com");

	assertEquals(x.getEmail(),"jatin@gmail.com" );
	assertEquals(x.getPassword(),"MTIzNDU=");
	verify(lservice,times(1)).findByEmail("jatin@gmail.com");
	}
}