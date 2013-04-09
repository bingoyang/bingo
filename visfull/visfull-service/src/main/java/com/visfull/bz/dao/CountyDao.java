package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.County;

public interface CountyDao extends HibernateBaseDao<County,Integer> {
	
	List<County> findCountiesByCityId(Integer cityId);

}
