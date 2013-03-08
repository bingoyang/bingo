package com.visfull.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visfull.system.dao.AuthResourceDao;
import com.visfull.system.domain.AuthResource;
import com.visfull.system.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private AuthResourceDao authResourceDao;

    public List<AuthResource> initMenusByUserId(Long userId) {
        
        return authResourceDao.findAuthResourcesByType("MENU");
    }
}
