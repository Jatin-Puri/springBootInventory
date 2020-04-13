package com.niit.inventory.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*Cross-origin resource sharing (CORS) is a standard protocol that defines the interaction 
 * between a browser and a server for safely handling cross-origin HTTP requests.

Simply put, a cross-origin HTTP request is a request to a specific resource,
 which is located at a different origin, namely a domain, protocol and port, 
 than the one of the client performing the request.*/
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping(value="/api")
public class ProductRestController {

}
