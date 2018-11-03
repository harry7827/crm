package com.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;

public interface SaleVisitService {

	PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	void save(SaleVisit saleVisit);

	SaleVisit findById(String visit_id);

	void delete(SaleVisit saleVisit);

	void update(SaleVisit saleVisit);
	
}
