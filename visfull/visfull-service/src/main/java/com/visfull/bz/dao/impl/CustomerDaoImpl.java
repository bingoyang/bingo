package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CustomerDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.emnu.Gender;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class CustomerDaoImpl extends HibernateBaseDaoImpl<BzCustomer,Long> implements
		CustomerDao {

	public void save(BzCustomer object) {
		getSession().save(object);
	}

	public BzCustomer findByPK(Long pk) {
		return (BzCustomer)getSession().get(BzCustomer.class,pk);
	}

	public void update(BzCustomer d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzCustomer d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzCustomer customer = (BzCustomer) session.get(BzCustomer.class,pk);
    	session.delete(customer);
	}

	public BzCustomer getCustomerByPhone(String phone) {
        Criteria criteria =getSession().createCriteria(BzCustomer.class);
        
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("bindPhone", phone));
        }
        List<BzCustomer> result = criteria.list();
        if(result!=null&&!result.isEmpty()){
        	return result.get(0);
        }
        return null;
	}

	public Pageable<BzCustomer> findCustomerByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzCustomer> page = new Pageable<BzCustomer>();
        Criteria criteria =getSession().createCriteria(BzCustomer.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        Gender gender = condition.getGender();
        String phone = condition.getPhone();
        String opName = condition.getOpName();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(gender!=null){
            criteria.add(Restrictions.eq("gender", gender));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("bindPhone", phone));
        }
        if(opName != null && !"".equals(opName)){
        	criteria.add(Restrictions.like("name",opName));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzCustomer> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}
}
