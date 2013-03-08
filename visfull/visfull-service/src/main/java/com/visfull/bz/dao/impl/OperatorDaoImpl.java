package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.OperatorDao;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class OperatorDaoImpl extends HibernateBaseDaoImpl<BzOperator, Long>
		implements OperatorDao {

	public void save(BzOperator object) {
		getSession().save(object);
	}

	public BzOperator findByPK(Long pk) {
		return (BzOperator) getSession().get(BzOperator.class, pk);
	}

	public void update(BzOperator d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzOperator d) {
		getSession().saveOrUpdate(d);
	}

    public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzOperator operator = (BzOperator) session.get(BzOperator.class,pk);
    	session.delete(operator);
    }

	public Pageable<BzOperator> findOperatorByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzOperator> page = new Pageable<BzOperator>();
        Criteria criteria =getSession().createCriteria(BzOperator.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        OpStatus status = condition.getStatus();
        String phone = condition.getPhone();
        String opName = condition.getOpName();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(status!=null){
            criteria.add(Restrictions.eq("status", status));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("opPhone", phone));
        }
        if(opName != null && !"".equals(opName)){
        	criteria.add(Restrictions.like("opName",opName));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzOperator> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

	public BzOperator findOperatorByCode(String opCode) {
        BzOperator operator = null;
		
		Criteria criteria =getSession().createCriteria(BzOperator.class);
        if(opCode!=null&&!"".equals(opCode)){
            criteria.add(Restrictions.like("opCode", opCode));
            List<BzOperator> data = criteria.list();
            if (data != null && data.size() > 0) {
            	operator = data.get(0);
    		}
        }
		return operator;
	}
	

}
