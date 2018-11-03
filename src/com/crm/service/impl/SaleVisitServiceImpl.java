package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.SaleVisitDao;
import com.crm.domain.Customer;
import com.crm.domain.PageBean;
import com.crm.domain.SaleVisit;
import com.crm.service.SaleVisitService;

public class SaleVisitServiceImpl implements SaleVisitService {
	@Autowired
	private SaleVisitDao saleVisitDao;

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = saleVisitDao.findByCount(criteria);
		pageBean.setTotalCount(totalCount);
		
		List<SaleVisit> list = saleVisitDao.findByPage(criteria,(currPage-1)*pageSize,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public SaleVisit findById(String visit_id) {
		return saleVisitDao.findById(visit_id);
	}

	@Override
	public void delete(SaleVisit saleVisit) {
		saleVisitDao.delete(saleVisit);
	}

	@Override
	public void update(SaleVisit saleVisit) {
		saleVisitDao.update(saleVisit);
	}
}
