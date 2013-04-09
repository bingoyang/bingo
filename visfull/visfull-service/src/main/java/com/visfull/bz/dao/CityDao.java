package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.City;

public interface CityDao extends HibernateBaseDao<City,Integer> {
	List<City> findCitiesByProvinceId(Integer provinceId);
}
