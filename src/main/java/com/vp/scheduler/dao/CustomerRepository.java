package com.vp.scheduler.dao;

import java.util.List;

//import org.springframework.data.mongodb.repository.MongoRepository;

import com.vp.scheduler.dto.Customer;

//public interface CustomerRepository extends MongoRepository<Customer, String> {
//
//	  public Customer findByFirstName(String firstName);
//	  public List<Customer> findByLastName(String lastName);
//
//	}
public interface CustomerRepository  {

	  public Customer findByFirstName(String firstName);
	  public List<Customer> findByLastName(String lastName);

	}
