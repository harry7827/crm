package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.CustomerDao;
import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	public CustomerServiceImpl() {
	}
	@Override
	public void findByCode() {
		
	}
	@Override
	public void save(Customer customer) {
		customerDao.save(customer);
	}
	@Override
	public PageBean<Customer> findByPage(DetachedCriteria criteria,Integer currPage,Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = customerDao.findByCount(criteria);
		pageBean.setTotalCount(totalCount);
		
		List<Customer> list = customerDao.findByPage(criteria,(currPage-1)*pageSize,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}
	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}
	@Override
	public void delete(Customer c) {
		customerDao.delete(c);
	}
	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
