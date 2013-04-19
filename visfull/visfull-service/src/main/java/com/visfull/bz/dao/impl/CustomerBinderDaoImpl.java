package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CustomerBinderDao;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.emnu.TargetType;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class CustomerBinderDaoImpl extends HibernateBaseDaoImpl<BzCustomerBinder,Long>
		implements CustomerBinderDao {

	public void save(BzCustomerBinder object) {
		getSession().save(object);
	}

	public BzCustomerBinder findByPK(Long pk) {
		return (BzCustomerBinder)getSession().get(BzCustomerBinder.class,pk);
	}

	public void update(BzCustomerBinder d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzCustomerBinder d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzCustomerBinder cusomerBinder = (BzCustomerBinder) session.get(BzCustomerBinder.class,pk);
    	session.delete(cusomerBinder);
	}

	public Pageable<BzCustomerBinder> findCustomerBinderByPage(
			Condition condition, Integer pageSize, Integer pageNo) {
        Pageable<BzCustomerBinder> page = new Pageable<BzCustomerBinder>();
        Criteria criteria =getSession().createCriteria(BzCustomerBinder.class);
        long id = condition.getId();
        if(id > 0){
            criteria.add(Restrictions.eq("targetId", id));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzCustomerBinder> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

	public List<BzCustomerBinder> findcuBinders(String targetCode,
			TargetType targetType, Date startDate, Date endDate) {
		
        Criteria criteria =getSession().createCriteria(BzCustomerBinder.class);
        if(targetCode!=null&&!"".equals(targetCode)){
            criteria.add(Restrictions.eq("targetCode", targetCode));
        }else {
        	criteria.add(Restrictions.eq("1", 2));
		}
        if(targetType!=null){
        	criteria.add(Restrictions.eq("targetType", targetType));
        }
        if(startDate!=null&&endDate!=null){
            criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        criteria.setProjection(null);
        List<BzCustomerBinder> data = criteria.list();
		
		return data;
	}

	public List<BzCustomerBinder> findcustomerBinders(String phoneNo,
			Date startDate, Date endDate) {
        Criteria criteria =getSession().createCriteria(BzCustomerBinder.class);
        if(phoneNo!=null&&!"".equals(phoneNo)){
            criteria.add(Restrictions.eq("customerPhone", phoneNo));
        }else {
        	criteria.add(Restrictions.eq("1", 2));
		}
        if(startDate!=null&&endDate!=null){
            criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        criteria.setProjection(null);
        List<BzCustomerBinder> data = criteria.list();
		
		return data;
	}

	public List<BzCustomerBinder> findcuBinders(List<String> targetCodes,
			TargetType targetType, Date startDate, Date endDate) {
		
        Criteria criteria =getSession().createCriteria(BzCustomerBinder.class);
        if(targetCodes!=null&&!targetCodes.isEmpty()){
            criteria.add(Restrictions.in("targetCode", targetCodes));
        }else {
        	criteria.add(Restrictions.eq("1", 2));
		}
        if(targetType!=null){
        	criteria.add(Restrictions.eq("targetType", targetType));
        }
        if(startDate!=null&&endDate!=null){
            criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        criteria.setProjection(null);
        List<BzCustomerBinder> data = criteria.list();
		
		return data;
	}

	public BzCustomerBinder findCustomerBinder(String targetCode,
			String customerPhone) {
		Criteria criteria =getSession().createCriteria(BzCustomerBinder.class);
        criteria.add(Restrictions.eq("customerPhone", customerPhone));
        criteria.add(Restrictions.eq("targetCode", targetCode));
        criteria.setProjection(null);
        List<BzCustomerBinder> data = criteria.list();
		if (data!=null&&!data.isEmpty()) {
			return data.get(0);
		}else {
			return null;
		}
	}
	
}
