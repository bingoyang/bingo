package com.visfull.bz.dao.impl;

import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.PositionInfoDao;
import com.visfull.bz.domain.BzPositionInfo;

@Repository
public class PositionInfoDaoImpl extends HibernateBaseDaoImpl<BzPositionInfo,Long > implements
		PositionInfoDao {

	public void save(BzPositionInfo object) {
		getSession().save(object);
	}

	public BzPositionInfo findByPK(Long pk) {
		return (BzPositionInfo) getSession().get(BzPositionInfo.class,pk);
	}

	public void update(BzPositionInfo d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzPositionInfo d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
		getSession().delete(getSession().get(BzPositionInfo.class, pk));
	}
}
