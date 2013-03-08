package com.visfull.bz.dao;

import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface CustomerDao extends HibernateBaseDao<BzCustomer, Long> {
	BzCustomer  getCustomerByPhone(String phone);
	
	public Pageable<BzCustomer> findCustomerByPage(Condition condition,Integer pageSize,Integer pageNo);
	
}
