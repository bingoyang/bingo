package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.BlackWhiteDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class BlackWhiteDaoImpl extends HibernateBaseDaoImpl<BzBlackWhite, Long> implements
		BlackWhiteDao {

	public void save(BzBlackWhite object) {
		getSession().save(object);
	}

	public BzBlackWhite findByPK(Long pk) {
		return (BzBlackWhite) getSession().get(BzBlackWhite.class, pk);
	}
	
	public void update(BzBlackWhite d) {

	}
	public void saveOrUpdate(BzBlackWhite d) {

	}

	public List<BzBlackWhite> findBlackWhiteAll() {
		String hql = "from BzBlackWhite bw ";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

    public Pageable<BzBlackWhite> findBlackWhitesByPage(Condition condition, Integer pageSize, Integer pageNo) {
        Pageable<BzBlackWhite> page = new Pageable<BzBlackWhite>();
        Criteria criteria =getSession().createCriteria(BzBlackWhite.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        PhoneType type = condition.getPhoneType();
        String phone = condition.getPhone();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(type!=null){
            criteria.add(Restrictions.eq("type", type));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("phone", phone));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzBlackWhite> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
    }

    public void deleteByPK(Long pk) {
        Session session = getSession();
        BzBlackWhite bzBlackWhite = (BzBlackWhite)session.get(BzBlackWhite.class,pk);
        session.delete(bzBlackWhite);
    }

	@SuppressWarnings("unchecked")
	public List<BzBlackWhite> findBlackWhites(Condition condition) {
		
        Criteria criteria =getSession().createCriteria(BzBlackWhite.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        PhoneType type = condition.getPhoneType();
        String phone = condition.getPhone();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(type!=null){
            criteria.add(Restrictions.eq("type", type));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("phone", phone));
        }
        return criteria.list();
	}	
}
