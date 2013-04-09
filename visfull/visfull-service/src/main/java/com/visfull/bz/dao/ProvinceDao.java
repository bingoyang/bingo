package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.Province;

public interface ProvinceDao extends HibernateBaseDao<Province,Integer> {
	List<Province> findALlProvinces();
}
