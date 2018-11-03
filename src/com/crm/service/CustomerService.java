package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;

public interface CustomerService {

	void findByCode();

	void save(Customer customer);

	PageBean<Customer> findByPage(DetachedCriteria criteria,Integer currPage,Integer pageSize);

	Customer findById(Long cust_id);

	void delete(Customer c);

	void update(Customer customer);

	List<Customer> findAll();

}
