package com.visfull.bz.dao;

import java.util.Date;
import java.util.List;

import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.emnu.TargetType;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface CustomerBinderDao extends HibernateBaseDao<BzCustomerBinder,Long> {
	
	public Pageable<BzCustomerBinder> findCustomerBinderByPage(Condition condition,Integer pageSize,Integer pageNo);
	
	public List<BzCustomerBinder> findcuBinders(String targetCode,
			TargetType targetType, Date startDate, Date endDate);
	
	public List<BzCustomerBinder> findcustomerBinders(String phoneNo,
			Date startDate, Date endDate);
}
