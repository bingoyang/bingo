package com.visfull.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.visfull.system.dao.AuthUserDao;
import com.visfull.system.domain.AuthUser;

@Repository
public class AuthUserDaoImpl extends HibernateBaseDaoImpl<AuthUser, Long> implements AuthUserDao {

    public void save(AuthUser authUser) {
        getSession().save(authUser);
    }

    public AuthUser findByPK(Long id) {
        return (AuthUser) getSession().get(AuthUser.class, id);
    }

    public void update(AuthUser authUser) {
        getSession().update(authUser);
    }

    public void saveOrUpdate(AuthUser authUser) {
        getSession().saveOrUpdate(authUser);
    }

	public void deleteByPK(Long pk) {
		// TODO Auto-generated method stub
		
	}

}
