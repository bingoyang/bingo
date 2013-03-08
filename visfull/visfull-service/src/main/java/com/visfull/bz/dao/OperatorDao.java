package com.visfull.bz.dao;

import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface OperatorDao extends HibernateBaseDao<BzOperator, Long>{
	
	public Pageable<BzOperator> findOperatorByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public BzOperator  findOperatorByCode(String opCode);
}
