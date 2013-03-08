package com.visfull.system.dao;

import java.util.List;

import com.visfull.system.domain.SystemDict;

public interface SystemDictDao extends HibernateBaseDao<SystemDict,Long> {
	
	public List<SystemDict> findDictsByType();

}
