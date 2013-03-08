package com.visfull.bz.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.ServerDao;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Repository
public class ServerDaoImpl extends HibernateBaseDaoImpl<BzServer, Long> implements
		ServerDao {

	public void save(BzServer object) {
		getSession().save(object);
	}

	public BzServer findByPK(Long pk) {
		return (BzServer) getSession().get(BzServer.class,pk);
	}

	public void update(BzServer d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzServer d) {
		getSession().saveOrUpdate(d);
	}

    public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzServer server = (BzServer) session.get(BzServer.class, pk);
    	session.delete(server);
    }

	public Pageable<BzServer> findServerByPage(Condition condition,
			Integer pageSize, Integer pageNo) {
        Pageable<BzServer> page = new Pageable<BzServer>();
        Criteria criteria =getSession().createCriteria(BzServer.class);
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
            criteria.add(Restrictions.like("phone", phone));
        }
        if(opName != null && !"".equals(opName)){
        	criteria.add(Restrictions.like("serverName",opName));
        }
        int totalCount = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue(); 
        criteria.setProjection(null);
        List<BzServer> data = criteria.setFirstResult(pageSize*(pageNo-1)).setMaxResults(pageSize).list();
        page.setData(data);
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        page.setTotal(totalCount);
        return page;
	}

	@SuppressWarnings("unchecked")
	public List<BzServer> findBzServers(long spId, long id) {
        Criteria criteria =getSession().createCriteria(BzServer.class);
        if(id > 0){
            criteria.add(Restrictions.eq("id", id));
        }
        criteria.add(Restrictions.eq("spId", spId));
        List<BzServer> data = criteria.list();
        return data;
	}

	public BzServer getBzServerByCode(String serverCode) {
		
		Criteria criteria =getSession().createCriteria(BzServer.class);
        if(serverCode!=null&&!"".equals(serverCode)){
            criteria.add(Restrictions.like("serverCode", serverCode));
            List<BzServer> data = criteria.list();
            if (data != null && data.size() > 0) {
            	return data.get(0);
    		}
        }
		return null;
	}


}
