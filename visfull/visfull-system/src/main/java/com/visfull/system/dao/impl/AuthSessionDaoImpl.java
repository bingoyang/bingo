package com.visfull.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.visfull.system.dao.AuthSessionDao;
import com.visfull.system.domain.AuthSession;

@Repository
public class AuthSessionDaoImpl extends HibernateBaseDaoImpl<AuthSession,String> implements
		AuthSessionDao {

	public void save(AuthSession object) {
		getSession().save(object);
	}

	public AuthSession findByPK(String pk) {
		return (AuthSession)getSession().get(AuthSession.class,pk);
	}

	public void update(AuthSession d) {
		getSession().update(d);
	}

	public void saveOrUpdate(AuthSession d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(String pk) {
		// TODO Auto-generated method stub
		
	}
	
}
