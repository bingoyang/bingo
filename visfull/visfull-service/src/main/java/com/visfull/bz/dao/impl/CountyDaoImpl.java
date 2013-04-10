package com.visfull.bz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CountyDao;
import com.visfull.bz.domain.County;

@Repository
public class CountyDaoImpl extends HibernateBaseDaoImpl<County,Integer> implements
		CountyDao {

	public void save(County object) {

	}

	public County findByPK(Integer pk) {
		return null;
	}

	public void update(County d) {

	}

	public void saveOrUpdate(County d) {

	}

	public void deleteByPK(Integer pk) {
		// TODO Auto-generated method stub

	}

	public List<County> findCountiesByCityId(Integer cityId) {
		List<County> result = new ArrayList<County>();
		Criteria criteria = getSession().createCriteria(County.class);
		criteria.add(Restrictions.eq("cityId",cityId));
		criteria.setProjection(null);
		result = criteria.list();
		return result;
	}

}
