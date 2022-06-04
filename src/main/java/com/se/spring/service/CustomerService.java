package com.se.spring.service;

import java.util.List;

import com.se.spring.entity.Customer;

public interface CustomerService {
	public void saveCustomer(Customer customer);
	public void deleteCustomer(int id);
	public Customer getCustomerById(int id);
	public List<Customer> getCustomers();
}
