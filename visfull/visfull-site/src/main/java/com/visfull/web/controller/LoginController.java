package com.visfull.web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    
    private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping("/login")
    public String loginSystem(HttpSession session){
        String operator = "login";
        session.setAttribute("operator", operator);
        LOGGER.debug("login {} ,{}", operator ,session.getAttribute("operator"));
        return "redirect:/main/init";
    }
//    @RequestMapping("/")
//    public String init(HttpServletRequest request,HttpServletResponse response,Model model){
//        
//       String ip = CommonUtil.getIpAddress(request);
//       System.out.println("client ip : "+ip);
//       model.addAttribute("ClientIP",ip);
//       return "login";
//    }
}
