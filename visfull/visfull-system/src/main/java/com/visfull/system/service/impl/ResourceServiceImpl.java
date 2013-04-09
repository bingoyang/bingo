package com.visfull.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visfull.system.dao.AuthResourceDao;
import com.visfull.system.dao.SystemDictDao;
import com.visfull.system.domain.AuthResource;
import com.visfull.system.domain.SystemDict;
import com.visfull.system.service.ResourceService;
import com.visfull.system.vo.Condition;
import com.visfull.system.vo.Pageable;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private AuthResourceDao authResourceDao;

    @Autowired
    private SystemDictDao systemDictDao;
    
    public List<AuthResource> initMenusByUserId(Long userId) {
        
        return authResourceDao.findAuthResourcesByType("MENU");
    }

	public void addSystemDict(SystemDict systemDict) {
		systemDict.setCreateDate(new Date());
		systemDictDao.save(systemDict);
	}

	public void deleteSystemDict(Long id) {
		
		systemDictDao.deleteByPK(id);
	}

	public void updateSystemDict(SystemDict systemDict) {
		
		systemDictDao.update(systemDict);
	}

	public Pageable<SystemDict> findDictByPage(Condition condition,Integer pageSize, Integer pageNo) {
		
		return systemDictDao.findDictByPageable(condition, pageSize, pageNo);
	}

	public SystemDict getSystemDict(Long id) {
		return systemDictDao.findByPK(id);
	}
}
