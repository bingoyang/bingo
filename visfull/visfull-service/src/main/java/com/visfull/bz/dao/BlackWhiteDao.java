package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface BlackWhiteDao extends HibernateBaseDao<BzBlackWhite,Long> {
	
	public List<BzBlackWhite> findBlackWhiteAll();
	
	public Pageable<BzBlackWhite> findBlackWhitesByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public List<BzBlackWhite> findBlackWhites(Condition condition);
}
