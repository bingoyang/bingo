package com.visfull.bz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CityDao;
import com.visfull.bz.domain.City;

@Repository
public class CityDaoImpl extends HibernateBaseDaoImpl<City,Integer> implements CityDao {

	public void save(City object) {
		// TODO Auto-generated method stub

	}

	public City findByPK(Integer pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(City d) {
		// TODO Auto-generated method stub

	}

	public void saveOrUpdate(City d) {
		// TODO Auto-generated method stub

	}

	public void deleteByPK(Integer pk) {
		// TODO Auto-generated method stub

	}

	public List<City> findCitiesByProvinceId(Integer provinceId) {
		List<City> result = new ArrayList<City>();
		Criteria criteria = getSession().createCriteria(City.class);
		criteria.add(Restrictions.eq("provinceId", provinceId));
		criteria.setProjection(null);
		result = criteria.list();
		return result;
	}

}
