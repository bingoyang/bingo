package com.visfull.system.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.system.dao.SystemDictDao;
import com.visfull.system.domain.SystemDict;
import com.visfull.system.vo.Condition;
import com.visfull.system.vo.Pageable;

@Repository
public class SystemDictDaoImpl extends HibernateBaseDaoImpl<SystemDict,Long> implements SystemDictDao {

    public void save(SystemDict object) {
    	getSession().save(object);
    }

    public SystemDict findByPK(Long pk) {
        return (SystemDict)getSession().get(SystemDict.class, pk);
    }
    public void update(SystemDict d) {
    	getSession().update(d);
    }
    public void saveOrUpdate(SystemDict d) {
    	getSession().saveOrUpdate(d);
    }
    
	@SuppressWarnings("unchecked")
	public List<SystemDict> findDictsByType(String dictType) {
		
		Criteria criteria = getSession().createCriteria(SystemDict.class);
		if(dictType!=null&&!"".equals(dictType)){
			criteria.add(Restrictions.eq("dictType",dictType));
			return criteria.list();
		}else {
			return null;
		}
	}

	public void deleteByPK(Long pk) {
		getSession().delete(getSession().get(SystemDict.class,pk));
	}

	public Pageable<SystemDict> findDictByPageable(Condition condition, Integer pageSize, Integer pageNo) {
        Pageable<SystemDict> page = new Pageable<SystemDict>();
        Criteria criteria =getSession().createCriteria(SystemDict.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<SystemDict> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}
}
 