package com.visfull.system.dao;

import java.util.List;

import com.visfull.system.domain.AuthResource;

public interface AuthResourceDao extends HibernateBaseDao<AuthResource,Long> {
    
    public List<AuthResource> findAuthResourcesByIds(String ids ,String resourceType);
    
    public List<AuthResource> findAuthResourcesByType(String resourceType);
}
