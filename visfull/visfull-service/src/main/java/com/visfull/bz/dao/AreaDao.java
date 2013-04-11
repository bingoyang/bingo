package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzArea;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface AreaDao extends HibernateBaseDao<BzArea,Integer> {
	
	public Pageable<BzArea> findAreaByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public List<BzArea> findAreaAll();
}
