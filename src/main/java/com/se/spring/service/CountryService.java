package com.se.spring.service;

import java.util.List;

import com.se.spring.entity.Country;
import com.se.spring.entity.Customer;

public interface CountryService {
	public List<Customer> getCustomersByCountryId(int idCountry);
	public Country getCountry(int id);
	public List<Country> getCountries();
}
