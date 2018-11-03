package com.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.domain.Customer;
import com.crm.domain.LinkMan;
import com.crm.domain.PageBean;

public interface LinkManService {

	PageBean<LinkMan> findByPage(DetachedCriteria criteria, Integer currPage, Integer pageSize);

	LinkMan findById(Long lkm_id);

	void delete(LinkMan lm);

	void save(LinkMan model);

	void update(LinkMan model);
	
}
