package com.visfull.system.dao.impl;

import java.util.List;

import com.visfull.system.dao.SystemDictDao;
import com.visfull.system.domain.SystemDict;

public class SystemDictDaoImpl extends HibernateBaseDaoImpl<SystemDict,Long> implements SystemDictDao {

    public void save(SystemDict object) {

    }

    public SystemDict findByPK(Long pk) {
        return null;
    }
    public void update(SystemDict d) {

    }
    public void saveOrUpdate(SystemDict d) {

    }
	public List<SystemDict> findDictsByType() {
		return null;
	}
}
 