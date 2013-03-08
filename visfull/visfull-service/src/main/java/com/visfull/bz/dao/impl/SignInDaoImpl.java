package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.SignInDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzSignIn;
import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class SignInDaoImpl extends HibernateBaseDaoImpl<BzSignIn,Long> implements
		SignInDao {

	public void save(BzSignIn object) {
		getSession().save(object);
	}

	public BzSignIn findByPK(Long pk) {
		return (BzSignIn) getSession().get(BzSignIn.class,pk);
	}

	public void update(BzSignIn d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzSignIn d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
		Session session = getSession();
		BzSignIn signIn = (BzSignIn) session.get(BzSignIn.class, pk);
		session.delete(signIn);
	}

	public Pageable<BzSignIn> findSignInByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzSignIn> page = new Pageable<BzSignIn>();
        Criteria criteria =getSession().createCriteria(BzBlackWhite.class);
        Date startDate = condition.getStartDate();
        Date endDate = condition.getEndDate();
        PhoneType type = condition.getPhoneType();
        String phone = condition.getPhone();
        if(startDate!=null&&endDate!=null){
               criteria.add(Restrictions.between("createDate",startDate,endDate));
        }
        if(phone!=null&&!"".equals(phone)){
            criteria.add(Restrictions.like("signinPhone", phone));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzSignIn> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

}
