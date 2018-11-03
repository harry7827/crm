package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.LinkManDao;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;
import com.crm.service.LinkManService;

public class LinkManServiceImpl implements LinkManService{
	@Autowired
	private LinkManDao linkManDao;
	
	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize) {
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		
		Integer totalCount = linkManDao.findByCount(criteria);
		pageBean.setTotalCount(totalCount);
		
		List<LinkMan> list = linkManDao.findByPage(criteria,(currPage-1)*pageSize,pageSize);
		pageBean.setList(list);
		
		return pageBean;
	}

	@Override
	public LinkMan findById(Long lkm_id) {
		return linkManDao.findById(lkm_id);
	}

	@Override
	public void delete(LinkMan lm) {
		linkManDao.delete(lm);
	}

	@Override
	public void save(LinkMan model) {
		linkManDao.save(model);
	}

	@Override
	public void update(LinkMan model) {
		linkManDao.update(model);
	}

}
