package com.visfull.system.dao;

import java.util.List;

import com.visfull.system.domain.SystemDict;
import com.visfull.system.vo.Condition;
import com.visfull.system.vo.Pageable;

public interface SystemDictDao extends HibernateBaseDao<SystemDict,Long> {
	
	public List<SystemDict> findDictsByType(String ditcType);
	
	public Pageable<SystemDict> findDictByPageable(Condition condition, Integer pageSize, Integer pageNo);

}
