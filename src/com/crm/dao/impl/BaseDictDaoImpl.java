package com.crm.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.crm.dao.BaseDictDao;
import com.crm.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao{

	@Override
	public List<BaseDict> findByTypeCode(DetachedCriteria detachedCriteria) {
		return (List<BaseDict>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}
	
}
