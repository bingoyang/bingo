package com.visfull.system.service;

import java.util.List;

import com.visfull.system.domain.AuthResource;

public interface ResourceService {
    
    public List<AuthResource> initMenusByUserId(Long userId);

}
