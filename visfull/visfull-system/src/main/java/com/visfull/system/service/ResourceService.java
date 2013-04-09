package com.visfull.system.service;

import java.util.List;

import com.visfull.system.domain.AuthResource;
import com.visfull.system.domain.SystemDict;
import com.visfull.system.vo.Condition;
import com.visfull.system.vo.Pageable;

public interface ResourceService {
    
    public List<AuthResource> initMenusByUserId(Long userId);
    
    public SystemDict getSystemDict(Long id);
    
    public void addSystemDict(SystemDict systemDict);
    
    public void deleteSystemDict(Long id);
    
    public void updateSystemDict(SystemDict systemDict);
    
    public Pageable<SystemDict> findDictByPage(Condition condition, Integer pageSize, Integer pageNo);

}
