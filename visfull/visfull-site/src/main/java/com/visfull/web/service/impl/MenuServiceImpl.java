package com.visfull.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.visfull.system.domain.AuthResource;
import com.visfull.system.service.ResourceService;
import com.visfull.web.service.MenuService;
import com.visfull.web.vo.TreeNode;

@Service
public class MenuServiceImpl implements MenuService {
    
    private static Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
    
    @Resource
    private ResourceService resourceService;

    public List<TreeNode> getTreeNodes(Long userId) {
        LOGGER.debug("find TreeNodes by userId : {}",userId);
       List<TreeNode> treeNodes = new ArrayList<TreeNode>(); 
       List<AuthResource> authResources = resourceService.initMenusByUserId(userId);
       TreeNode treeNode = null;
       for (AuthResource authResource : authResources) {
           treeNode = new TreeNode();
           treeNode.setId(authResource.getId());
           treeNode.setpId(authResource.getResourcePid());
           treeNode.setNodeName(authResource.getResourceName());
           treeNode.setTarget("navTab");
           treeNode.setUrl(authResource.getTargetUrl());
           treeNodes.add(treeNode);
       }
       return treeNodes;
    }

}
