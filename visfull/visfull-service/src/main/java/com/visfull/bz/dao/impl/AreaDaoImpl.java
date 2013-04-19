package com.visfull.bz.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.AreaDao;
import com.visfull.bz.domain.BzArea;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class AreaDaoImpl extends HibernateBaseDaoImpl<BzArea,Integer> implements AreaDao {

	public void save(BzArea object) {
		getSession().save(object);
	}

	public BzArea findByPK(Integer pk) {
		return (BzArea) getSession().get(BzArea.class,pk);
	}

	public void update(BzArea d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzArea d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Integer pk) {
		getSession().delete(getSession().get(BzArea.class, pk));
	}

	public Pageable<BzArea> findAreaByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
		
        Pageable<BzArea> page = new Pageable<BzArea>();
        Criteria criteria =getSession().createCriteria(BzArea.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }

        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzArea> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

	public List<BzArea> findAreaAll() {
		List<BzArea> areas = new ArrayList<BzArea>();
        Pageable<BzArea> page = new Pageable<BzArea>();
        Criteria criteria =getSession().createCriteria(BzArea.class);
        criteria.setProjection(null);
        areas = criteria.list();
		return areas;
	}

	public List<BzArea> findAreaList(Integer countyId) {
		List<BzArea> areas = new ArrayList<BzArea>();
        Pageable<BzArea> page = new Pageable<BzArea>();
        Criteria criteria =getSession().createCriteria(BzArea.class);
        criteria.setProjection(null);
        criteria.add(Restrictions.eq("countyId", countyId));
        areas = criteria.list();
		return areas;
	}
	
}
