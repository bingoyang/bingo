package com.visfull.bz.dao.impl;

import com.visfull.bz.dao.AreaDao;
import com.visfull.bz.domain.BzArea;

public class AreaDaoImpl extends HibernateBaseDaoImpl<BzArea,Integer> implements AreaDao {

	public void save(BzArea object) {
		getSession().save(object);
	}

	public BzArea findByPK(Integer pk) {
		return (BzArea) getSession().get(BzArea.class,pk);
	}

	public void update(BzArea d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzArea d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Integer pk) {
		getSession().delete(getSession().get(BzArea.class, pk));
	}

}
