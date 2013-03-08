package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzServer;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface ServerDao extends HibernateBaseDao<BzServer, Long>{
	public Pageable<BzServer> findServerByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public List<BzServer> findBzServers(long spId,long id);
	
	public BzServer getBzServerByCode(String serverCode);
}
