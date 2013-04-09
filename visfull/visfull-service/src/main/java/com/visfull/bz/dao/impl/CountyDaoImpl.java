package com.visfull.bz.dao.impl;

import java.util.List;

import com.visfull.bz.dao.CountyDao;
import com.visfull.bz.domain.County;

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
		// TODO Auto-generated method stub
		return null;
	}

}
