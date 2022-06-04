package com.se.spring.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.se.spring.entity.Country;
import com.se.spring.entity.Customer;

@Repository
public class CountryDaoImpl implements CountryDao {
	@Autowired 
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public List<Customer> getCustomersByCountryId(int idCountry) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query<Customer> query=session.createNativeQuery("select * from Customer cu join Country co on cu.country_id=co.id where co.id='"+idCountry+"'",Customer.class);
		List<Customer> customers=query.getResultList();
		return customers;
	}
	@Override
	@Transactional
	public Country getCountry(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Country country=session.get(Country.class, id);
		return country;
	}

	@Override
	@Transactional
	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query<Country> query=session.createQuery("from Country",Country.class);
		List<Country> countries=query.getResultList();
		return countries;
	}

}
