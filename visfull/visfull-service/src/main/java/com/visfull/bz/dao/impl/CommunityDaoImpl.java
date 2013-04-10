package com.visfull.bz.dao.impl;

import com.visfull.bz.dao.CommunityDao;
import com.visfull.bz.domain.BzCommunity;

public class CommunityDaoImpl extends HibernateBaseDaoImpl<BzCommunity,Integer> implements
		CommunityDao {

	public void save(BzCommunity object) {
		getSession().save(object);
	}

	public BzCommunity findByPK(Integer pk) {
		return (BzCommunity) getSession().get(BzCommunity.class,pk);
	}

	public void update(BzCommunity d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzCommunity d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Integer pk) {
		getSession().delete(getSession().get(BzCommunity.class,pk));
	}

}
