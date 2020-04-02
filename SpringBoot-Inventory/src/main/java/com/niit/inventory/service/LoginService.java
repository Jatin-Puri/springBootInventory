package com.niit.inventory.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.niit.inventory.model.Dealer;
import com.niit.inventory.repository.DealerRepository;

@Service
@Transactional
public class LoginService {
   @Autowired
   private DealerRepository drepo;
   public void saveDealer(Dealer dealer)
   {
	   drepo.save(dealer);
   }
}
