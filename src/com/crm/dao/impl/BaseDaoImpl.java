package com.crm.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.crm.dao.BaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	private Class clazz;

	public BaseDaoImpl() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType p=(ParameterizedType) type;
		clazz = (Class) p.getActualTypeArguments()[0];
		System.out.println(type);
		System.out.println(p);
		System.out.println(clazz);
	}
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public T findById(Serializable id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	@Override
	public List<T> findByPage(DetachedCriteria criteria, Integer beginResult, Integer pageSize) {
		criteria.setProjection(null);
		List<T> list = (List<T>) this.getHibernateTemplate().findByCriteria(criteria,beginResult,pageSize);
		if (list!=null&&list.size()>0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer findByCount(DetachedCriteria criteria) {
		criteria.setProjection(Projections.rowCount());
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
		return list.get(0).intValue();
	}

}
