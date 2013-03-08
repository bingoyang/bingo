package com.visfull.system.dao;
/**
 *@author Administrator
 *2012-8-1 下午05:25:52
 *
 */
public interface HibernateBaseDao<D,PK> {
    
    public void save(D object);
    
    public D findByPK(PK pk);
    
    public void update(D d);
    
    public void saveOrUpdate(D d);
    
}
