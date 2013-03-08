package com.visfull.bz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visfull.bz.dao.DemoDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoDao demoDao;
    
    public void addBlackWhite(BzBlackWhite blackWhite) {
        demoDao.saveObj(blackWhite);
    }

}
