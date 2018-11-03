package com.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.BaseDictDao;
import com.crm.domain.BaseDict;
import com.crm.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService{
	@Autowired
	private BaseDictDao baseDictDao;
	public List<BaseDict> findByTypeCode(String dict_type_code){
		//dict_type_code
		DetachedCriteria detachedCriteria=DetachedCriteria.forClass(BaseDict.class).add(Restrictions.eq("dict_type_code", dict_type_code));
		return baseDictDao.findByTypeCode(detachedCriteria);
	}

}
