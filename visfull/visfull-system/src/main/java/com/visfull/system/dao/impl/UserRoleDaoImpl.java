package com.visfull.system.dao.impl;

import com.visfull.system.dao.UserRoleDao;
import com.visfull.system.domain.UserRole;

public class UserRoleDaoImpl extends HibernateBaseDaoImpl<UserRole,Long> implements UserRoleDao {

    public void save(UserRole userRole) {
        getSession().save(userRole);
    }

    public UserRole findByPK(Long id) {
        return (UserRole) getSession().get(UserRole.class, id);
    }
    public void update(UserRole userRole) {
        getSession().update(userRole);
    }
    public void saveOrUpdate(UserRole userRole) {
        getSession().saveOrUpdate(userRole);
    }

	public void deleteByPK(Long pk) {
		
	}

}
