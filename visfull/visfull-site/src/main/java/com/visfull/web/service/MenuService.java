package com.visfull.web.service;

import java.util.List;

import com.visfull.web.vo.TreeNode;

public interface MenuService {
    
    public List<TreeNode> getTreeNodes(Long userId);
}
