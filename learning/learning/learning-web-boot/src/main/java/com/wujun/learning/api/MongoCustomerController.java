package com.wujun.learning.api;

import com.wujun.learning.service.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo/customers")
public class MongoCustomerController{// extends BaseController{

	Logger log = LoggerFactory.getLogger("DEBUG");

	@Autowired
	CustomerRepository customerRepository;

//	@PostMapping
//	public ResultResponse addCustomer(String firstName, String lastName){
//		return processSimple(new ResultResponse(), rr -> {
//            Customer c = new Customer(firstName, lastName);
//            customerRepository.insert(c);
//        });
//	}
//
//	@GetMapping("{firstName}")
//	public ResultResponse query(@PathVariable(name="firstName")String firstName){
//		return processSimple(new ResultResponse(), rr -> rr.addAttribute("customer", customerRepository.findByFirstName(firstName)));
//	}
//
//	@GetMapping
//	public ResultResponse all(){
//		return processSimple(new ResultResponse(), rr -> rr.addAttribute("customers", customerRepository.findAll()));
//	}
//
//	@GetMapping("/total")
//	public ResultResponse totalNum(){
//		return processSimple(new ResultResponse(), rr -> rr.addAttribute("number", customerRepository.count()));
//	}
	
	
	
	
}
