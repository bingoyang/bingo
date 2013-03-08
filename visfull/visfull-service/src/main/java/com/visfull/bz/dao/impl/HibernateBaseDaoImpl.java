package com.visfull.bz.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.visfull.bz.dao.HibernateBaseDao;

/**
 *@author Administrator
 *2012-8-1 下午05:49:41
 *
 */
public abstract class HibernateBaseDaoImpl<D extends java.io.Serializable,PK extends java.io.Serializable> implements HibernateBaseDao<D, PK> {
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory; 
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
