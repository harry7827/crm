package com.crm.dao.impl;

import java.util.List;
import com.crm.dao.UserDao;
import com.crm.domain.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public User login(User user) {
		String hql="from User where user_code=? and user_password=?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql,new String[] {user.getUser_code(),user.getUser_password()});
		if (list!=null&&list.size()>0) {
			return list.get(0);
		}
		return null;
	}

}
