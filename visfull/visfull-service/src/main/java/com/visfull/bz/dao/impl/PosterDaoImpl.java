package com.visfull.bz.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.PosterDao;
import com.visfull.bz.domain.BzPoster;
import com.visfull.bz.emnu.TargetType;

@Repository
public class PosterDaoImpl extends HibernateBaseDaoImpl<BzPoster, Long> implements
		PosterDao {

	public void save(BzPoster object) {
		getSession().save(object);
	}

	public BzPoster findByPK(Long pk) {
		return (BzPoster)getSession().get(BzPoster.class,pk);
	}

	public void update(BzPoster d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzPoster d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
		Session session = getSession();
		session.delete(findByPK(pk));
	}

	public List<BzPoster> findBzPosters(Long id) {
        Criteria criteria =getSession().createCriteria(BzPoster.class);
        if(id!=null){
            criteria.add(Restrictions.eq("owner", id));
        }
        criteria.setProjection(null);
        List<BzPoster> data = criteria.list();
        return data;
	}

	public List<BzPoster> findBzPosters(Long id, TargetType ownerType) {
        Criteria criteria =getSession().createCriteria(BzPoster.class);
        if(id!=null){
            criteria.add(Restrictions.eq("owner", id));
        }
        if(ownerType!=null){
        	criteria.add(Restrictions.eq("ownerType", ownerType));
        }
        criteria.setProjection(null);
        List<BzPoster> data = criteria.list();
        return data;
	}

	public List<BzPoster> findBzPosters(List<Long> posterIds) {
        Criteria criteria =getSession().createCriteria(BzPoster.class);
        if(posterIds!=null){
        	criteria.add(Restrictions.in("id", posterIds));
        }
        criteria.setProjection(null);
        List<BzPoster> data = criteria.list();
        return data;
	}
}
