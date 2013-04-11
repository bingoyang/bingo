package com.visfull.bz.dao;

import com.visfull.bz.domain.BzCommunity;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface CommunityDao extends HibernateBaseDao<BzCommunity,Integer> {

	public Pageable<BzCommunity> findCommunityByPage(Condition condition,Integer pageSize,Integer pageNo);
}
