package com.visfull.bz.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.DataTreeDao;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;

@Repository
public class DataTreeDaoImpl extends HibernateBaseDaoImpl<BzDataTree, Long>
		implements DataTreeDao {

	public void save(BzDataTree object) {
		getSession().save(object);
	}

	public BzDataTree findByPK(Long pk) {
		return (BzDataTree)getSession().get(BzDataTree.class, pk);
	}

	public void update(BzDataTree d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzDataTree d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
		Session session = getSession();
		session.delete(findByPK(pk));
	}

	public List<BzDataTree> findAllByDataType(DataType dataType) {
        Criteria criteria =getSession().createCriteria(BzDataTree.class);
        if(dataType!=null){
            criteria.add(Restrictions.eq("dataType", dataType));
        }
        criteria.setProjection(null);
        List<BzDataTree> result = criteria.list();
        return result;
	}

	public List<BzDataTree> findAllByDataType(DataType dataType,
			NodeType nodeType) {
        Criteria criteria =getSession().createCriteria(BzDataTree.class);
        if(dataType!=null){
            criteria.add(Restrictions.eq("dataType", dataType));
            criteria.add(Restrictions.eq("nodeType", nodeType));
        }
        criteria.setProjection(null);
        List<BzDataTree> result = criteria.list();
        return result;
	}
}
