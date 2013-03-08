package com.visfull.bz.dao.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.visfull.bz.dao.CallRecordDao;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.domain.BzServer;

@Repository
public class CallRecordDaoImpl extends HibernateBaseDaoImpl<BzCallRecord,Long> implements
		CallRecordDao {

	public void save(BzCallRecord object) {
		getSession().save(object);
	}

	public BzCallRecord findByPK(Long pk) {
		return (BzCallRecord)getSession().get(BzCallRecord.class,pk);
	}

	public void update(BzCallRecord d) {
		getSession().update(d);
	}

	public void saveOrUpdate(BzCallRecord d) {
		getSession().saveOrUpdate(d);
	}

	public void deleteByPK(Long pk) {
    	Session session = getSession();
    	BzCallRecord record = (BzCallRecord)getSession().get(BzCallRecord.class,pk);
    	session.delete(record);
	}

}
