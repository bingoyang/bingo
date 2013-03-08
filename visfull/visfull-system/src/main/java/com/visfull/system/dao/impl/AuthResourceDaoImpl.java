package com.visfull.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.visfull.system.dao.AuthResourceDao;
import com.visfull.system.domain.AuthResource;

@Repository
public class AuthResourceDaoImpl extends HibernateBaseDaoImpl<AuthResource,Long> implements AuthResourceDao {

    public void save(AuthResource authResource) {
        getSession().save(authResource);
    }

    public AuthResource findByPK(Long id) {
        return (AuthResource) getSession().get(AuthResource.class, id);
    }

    public void update(AuthResource authResource) {
        getSession().update(authResource);
    }

    public void saveOrUpdate(AuthResource authResource) {
        getSession().saveOrUpdate(authResource);
    }

    @SuppressWarnings("unchecked")
    public List<AuthResource> findAuthResourcesByIds(String ids ,String resourceType) {
        String sb = new String("from AuthResource r where r.id in (?) and r.resourceType = ? ");
        org.hibernate.Query query = getSession().createQuery(sb.toString());
        query.setParameter(0, ids);
        query.setParameter(1,resourceType);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AuthResource> findAuthResourcesByType(String resourceType) {
        String sb = new String("from AuthResource r where  r.resourceType = ? ");
        org.hibernate.Query query = getSession().createQuery(sb.toString());
        query.setParameter(0,resourceType);
        return query.list();
    }

}
