package com.visfull.bz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;

import com.visfull.bz.dao.ProvinceDao;
import com.visfull.bz.domain.Province;

public class ProviceDaoImpl extends HibernateBaseDaoImpl<Province,Integer> implements
		ProvinceDao {

	public void save(Province object) {
		// TODO Auto-generated method stub

	}

	public Province findByPK(Integer pk) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Province d) {
		// TODO Auto-generated method stub

	}

	public void saveOrUpdate(Province d) {
		// TODO Auto-generated method stub

	}

	public void deleteByPK(Integer pk) {
		// TODO Auto-generated method stub

	}

	public List<Province> findALlProvinces() {
		List<Province> result = new ArrayList<Province>();
		Criteria criteria = getSession().createCriteria(Province.class);
		criteria.setProjection(null);
		
		return result;
	}

}
