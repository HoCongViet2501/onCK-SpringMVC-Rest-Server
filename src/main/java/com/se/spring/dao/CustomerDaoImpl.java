package com.se.spring.dao;

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
public class CustomerDaoImpl implements CustomerDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);
	}
	@Override
	@Transactional
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Customer customer=getCustomerById(id);
		session.delete(customer);
	}
	@Override
	@Transactional
	public Customer getCustomerById(int id) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// read from database using the primary key
		Customer customer = currentSession.get(Customer.class, id);

		return customer;
	}
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Customer> query=currentSession.createQuery("from Customer",Customer.class);
		List<Customer> customers=query.getResultList();
		return customers;
	}

}
