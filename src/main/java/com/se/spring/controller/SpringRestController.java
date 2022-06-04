package com.se.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.spring.entity.Country;
import com.se.spring.entity.Customer;
import com.se.spring.service.CountryService;
import com.se.spring.service.CustomerService;

@RestController
@RequestMapping("/api")
public class SpringRestController{
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CountryService countryService;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}
	@GetMapping("/countries")
	public List<Country> getCountries(){
		return countryService.getCountries();
	}
	@GetMapping("/customers/country/{idCountry}")
	public List<Customer> getCustomersByCountry(@PathVariable int idCountry){
		return countryService.getCustomersByCountryId(idCountry);
	}
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable int id) {
		Customer customer=customerService.getCustomerById(id);
		return customer;
	}
	@PostMapping("/customers/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		customer.setId(0);
		customerService.saveCustomer(customer);
		return customer;
	}
	@PutMapping("/customers/update")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customerService.saveCustomer(customer);
		return customer;
	}
	@DeleteMapping("/customers/delete/{id}")
	public String deleteCustomer(@PathVariable int id) {
		//Customer customer=customerService.getCustomerById(id);
		customerService.deleteCustomer(id);
		return "Delete customer id: "+id;
	}
}