package com.visfull.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visfull.web.vo.DemoForm;
import com.visfull.web.vo.Menu;
import com.visfull.web.vo.MenuData;

@Controller
@RequestMapping("/main")
public class InitController {
    
    @RequestMapping("/init")
    public String initMain(){
        return "init";
    }
    
    @RequestMapping("/initblackwhite")
    public String toAddBlackWhite(){
    	return "add_black_white";
    }
    
    @RequestMapping("/reciveData")
    public void reciveData(HttpServletRequest request,HttpServletResponse response){
        
        System.out.println(request.getParameter("xmlData"));
    }
    
    @RequestMapping(value="/submitBean",method=RequestMethod.POST)
    public @ResponseBody String submitBean(@Valid DemoForm demoForm){
        System.out.println("ssssssssssssssss");
        return demoForm.getName();
    }
    
    @RequestMapping(value="/getbody",method=RequestMethod.GET)
    public void requestBody(@RequestBody String body,Writer writer){
        try {
            writer.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   @RequestMapping(value="/queryMenuData",method=RequestMethod.GET)
   public @ResponseBody MenuData queryMenuData(){
       MenuData menuData = new MenuData();
       List<Menu> menus = new ArrayList<Menu>();
       menuData.setPage(1);
       menuData.setTotal(1);
       menuData.setRecords(9);
       Menu menu = null;
       for(int i = 1;i<10;i++){
           menu = new Menu();
           
           menu.setId(i);
           
           menu.setCell(new Object[]{"menu"+i,i==9?"http://www.baidu.com":"#",i,(i-1),i==9?true:false,false});
           
           menus.add(menu);
       }
       menuData.setRows(menus);
       return menuData;
   }
    
   @RequestMapping("/testlongtime")
   public @ResponseBody String testLongTime(){
       String resultString="success!!!";
       try {
        Thread.sleep(1000*300);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
       return resultString;
   }
   
   @RequestMapping("/synctaskdeal")
   public @ResponseBody String syncTaskDeal(HttpServletRequest request,HttpServletResponse response){
       
//       ExecutorService executorService = Executors.newFixedThreadPool(5);
//       
//       SyncTask syncTask = new SyncTask();
//       
//       executorService.execute(syncTask);
//       
//       executorService.shutdown();
       
       System.out.println(request.getHeader("host"));
       
       return "task is done";
   }
   @RequestMapping("/testrequest")
   public void testResponseWriter(HttpServletRequest request,HttpServletResponse response){
       
       try {
        Thread.sleep(5000);
        response.getWriter().print("retrun ...");
        response.flushBuffer();
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
   
}
