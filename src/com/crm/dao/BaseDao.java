package com.crm.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {

	void save(T entity);
	
	void delete(T entity);

	void update(T entity);
	
	T findById(Serializable id);

	List<T> findAll();
	
	List<T> findByPage(DetachedCriteria criteria,Integer beginResult,Integer pageSize);

	Integer findByCount(DetachedCriteria criteria);
}
