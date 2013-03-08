package com.visfull.bz.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.DemoDao;
import com.visfull.bz.domain.BzBlackWhite;

@Repository
public class DemoDaoImpl implements DemoDao {
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory; 
    
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveObj(BzBlackWhite blackWhite) {
        getSession().save(blackWhite);
    }

}
