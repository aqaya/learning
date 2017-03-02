package com.wujun.learning.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.commom.utils.BaseController;
import com.wujun.learning.commom.utils.ResultResponse;
import com.wujun.learning.config.Properties;
import com.wujun.learning.service.Customer;
import com.wujun.learning.service.CustomerRepository;
import com.wujun.learning.service.UserService;

@RestController
@RequestMapping("/customers")
public class CustomerController extends BaseController{

	Logger log = LoggerFactory.getLogger("DEBUG");

	@Autowired
	Properties properties;

	@Autowired
	UserService userService;
	
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping
	public ResultResponse addCustomer(String firstName, String lastName){
		return processSimple(new ResultResponse(), new Taker() {
			
			@Override
			public void process(ResultResponse rr) {
				Customer c = new Customer(firstName, lastName);
				customerRepository.insert(c);
			}
		});
	}
	
	@GetMapping("{firstName}")
	public ResultResponse query(@PathVariable(name="firstName")String firstName){
		return processSimple(new ResultResponse(), new Taker() {
			
			@Override
			public void process(ResultResponse rr) {
				rr.addAttribute("customer", customerRepository.findByFirstName(firstName));
			}
		});
	}
	
	@GetMapping
	public ResultResponse all(){
		return processSimple(new ResultResponse(), new Taker() {
			
			@Override
			public void process(ResultResponse rr) {
				rr.addAttribute("customers", customerRepository.findAll());
			}
		});
	}
	
	@GetMapping("/total")
	public ResultResponse totalNum(){
		return processSimple(new ResultResponse(), new Taker() {
			
			@Override
			public void process(ResultResponse rr) {
				rr.addAttribute("number", customerRepository.count());
			}
		});
	}
	
	
	
	
}
