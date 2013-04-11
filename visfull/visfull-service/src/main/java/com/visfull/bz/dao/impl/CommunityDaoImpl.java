package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CommunityDao;
import com.visfull.bz.domain.BzCommunity;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class CommunityDaoImpl extends HibernateBaseDaoImpl<BzCommunity,Integer> implements
		CommunityDao {

	public void save(BzCommunity object) {
		getSession().save(object);
	}

	public BzCommunity findByPK(Integer pk) {
		return (BzCommunity) getSession().get(BzCommunity.class,pk);
	}

	public void update(BzCommunity d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzCommunity d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Integer pk) {
		getSession().delete(getSession().get(BzCommunity.class,pk));
	}

	public Pageable<BzCommunity> findCommunityByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzCommunity> page = new Pageable<BzCommunity>();
        Criteria criteria =getSession().createCriteria(BzCommunity.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }

        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzCommunity> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

}
