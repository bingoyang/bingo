package com.visfull.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visfull.web.service.MenuService;
import com.visfull.web.vo.TreeNode;

@SuppressWarnings("restriction")
@Controller
@RequestMapping("/menus")
public class MenuConroller {
    
    private static Logger LOGGER = LoggerFactory.getLogger(MenuConroller.class);
    
    @Resource
    private MenuService menuService;
    
    
    @RequestMapping("/init")
    @ResponseBody
    public List<TreeNode> initMenus(){
        LOGGER.debug("init menus ... ");
        List<TreeNode> menus = menuService.getTreeNodes(0L);
        
        return menus;
    }
    
}
