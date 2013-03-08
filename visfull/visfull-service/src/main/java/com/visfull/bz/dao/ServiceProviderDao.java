package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface ServiceProviderDao extends HibernateBaseDao<BzServiceprovider,Long>{
	
	public Pageable<BzServiceprovider> findProviderByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public List<BzServiceprovider> findProviderAll(Condition condition);
	
	public List<BzServiceprovider> findBzServiceproviders(long pId,long spId);
	
	public BzServiceprovider getServiceproviderByCode(String spCode);
}
