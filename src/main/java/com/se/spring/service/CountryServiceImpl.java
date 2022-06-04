package com.se.spring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.se.spring.dao.CountryDao;
import com.se.spring.entity.Country;
import com.se.spring.entity.Customer;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryDao countryDao;
	@Override
	@Transactional
	public List<Customer> getCustomersByCountryId(int idCountry) {
		// TODO Auto-generated method stub
		return countryDao.getCustomersByCountryId(idCountry);
	}

	@Override
	@Transactional
	public Country getCountry(int id) {
		// TODO Auto-generated method stub
		return countryDao.getCountry(id);
	}

	@Override
	@Transactional
	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return countryDao.getCountries();
	}

}
