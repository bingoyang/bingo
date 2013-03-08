package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.ServiceProviderDao;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class ServiceProviderDaoImpl extends HibernateBaseDaoImpl<BzServiceprovider, Long>
		implements ServiceProviderDao {

	public void save(BzServiceprovider object) {
		getSession().save(object);
	}

	public BzServiceprovider findByPK(Long pk) {
		return (BzServiceprovider) getSession().get(BzServiceprovider.class, pk);
	}

	public void update(BzServiceprovider d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzServiceprovider d) {
		getSession().saveOrUpdate(d);
	}

    public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzServiceprovider serviceprovider = (BzServiceprovider) session.get(BzServiceprovider.class, pk);
    	session.delete(serviceprovider);
        
    }

	@SuppressWarnings("unchecked")
	public Pageable<BzServiceprovider> findProviderByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzServiceprovider> page = new Pageable<BzServiceprovider>();
        Criteria criteria =getSession().createCriteria(BzServiceprovider.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        OpStatus status = condition.getStatus();
        String phone = condition.getPhone();
        String serviceName = condition.getOpName();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(status!=null){
            criteria.add(Restrictions.eq("status", status));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("phoneNo", phone));
        }
        if(serviceName != null && !"".equals(serviceName)){
        	criteria.add(Restrictions.like("serviceName",serviceName));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzServiceprovider> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

	@SuppressWarnings("unchecked")
	public List<BzServiceprovider> findBzServiceproviders(long pId, long spId) {
        Criteria criteria =getSession().createCriteria(BzServiceprovider.class);
        if(spId != 0)
        	criteria.add(Restrictions.eq("id", spId));
        criteria.add(Restrictions.eq("opId", pId));
        List<BzServiceprovider> data = criteria.list();
        return data;
	}

	public BzServiceprovider getServiceproviderByCode(String spCode) {
		Criteria criteria =getSession().createCriteria(BzServiceprovider.class);
        if(spCode!=null&&!"".equals(spCode)){
            criteria.add(Restrictions.like("serviceCode", spCode));
            List<BzServiceprovider> data = criteria.list();
            if (data != null && data.size() > 0) {
            	return data.get(0);
    		}
        }
		return null;
	}

	public List<BzServiceprovider> findProviderAll(Condition condition) {
        Criteria criteria =getSession().createCriteria(BzServiceprovider.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        OpStatus status = condition.getStatus();
        String phone = condition.getPhone();
        String serviceName = condition.getOpName();
        Long catalogId = condition.getCatalogId();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(status!=null){
            criteria.add(Restrictions.eq("status", status));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("phoneNo", phone));
        }
        if(serviceName != null && !"".equals(serviceName)){
        	criteria.add(Restrictions.like("serviceName",serviceName));
        }
        
        if(serviceName != null && !"".equals(serviceName)){
        	criteria.add(Restrictions.like("serviceName",serviceName));
        }
        
        if(catalogId != null){
        	criteria.add(Restrictions.eq("catalogId",catalogId));
        }
        criteria.setProjection(null);
        List<BzServiceprovider> data = criteria.list();
        
        return data;
	}

	
}
