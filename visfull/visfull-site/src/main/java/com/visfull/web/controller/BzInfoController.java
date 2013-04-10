package com.visfull.web.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.domain.City;
import com.visfull.bz.domain.County;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;
import com.visfull.system.domain.SystemDict;
import com.visfull.system.service.ResourceService;
import com.visfull.web.vo.AjaxDone;

@Controller
@RequestMapping("/bz")
public class BzInfoController {

    private static Logger LOGGER = LoggerFactory.getLogger(BzInfoController.class);

    @Autowired
    private BzInfoService bzInfoService;
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/addblackwhite/{navTabId}")
    public @ResponseBody AjaxDone  addBlackWhite(@PathVariable("navTabId")String navTabId,BzBlackWhite blackWhite ) {
        blackWhite.setCreateDate(new Date());
        try {
            bzInfoService.addBlackWhite(blackWhite);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }

    @RequestMapping("/toaddblackwhite")
    public String toAddBlackWhite() {
        return "add_black_white";
    }

    @RequestMapping("/toblackwhitequery")
    public String toBlackWhiteQuery(ModelMap map) {
        
        return "black_white_list";
    }

    @RequestMapping("/blackwhitelist")
    public String queryBlackWhiteList(ModelMap map,Pageable<BzBlackWhite> page,Condition condition) {
        
        page = bzInfoService.findBlackWhiteByPage(condition, page);
        map.put("page", page);
        return "black_white_list";
    }
    
    @RequestMapping("/deleteblackwhite/{id}")
    public @ResponseBody AjaxDone deleteBlackWhite(@PathVariable("id")Long id){
        bzInfoService.deleteBlackWhite(id);
        
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/toupdateblackwhite/{id}")
    public String toEditBlackWhite(@PathVariable("id")Long id,ModelMap map){
    	BzBlackWhite blackWhite = bzInfoService.getBlackWhite(id);
    	map.put("blackWhite",blackWhite);
    	return "edit_blackwhite";
    }
    
    @RequestMapping("/updateblackwhite/{navTabId}")
    public @ResponseBody AjaxDone updateBlackWhite(@PathVariable("navTabId")String navTabId,BzBlackWhite blackWhite){
    	
    	bzInfoService.updateBlackWhite(blackWhite);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/initoperatorquery")
    public String initOperatorQuery(){
    	
    	return "operator_list";
    }
    
    @RequestMapping("/initaddoperator")
    public String initAddOperator(){
    	
    	return "add_operator";
    }
    @RequestMapping("/addoperator")
    public @ResponseBody AjaxDone addOperator(BzOperator operator){
    	
    	operator.setCreateDate(new Date());
    	
    	bzInfoService.addOperator(operator);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/operatorlist")
    public String queryOperatorList(ModelMap map,Pageable<BzOperator> page,Condition condition){
    	
        page = bzInfoService.findOperatorsByCondition(condition,page);
        map.put("page", page);
    	return "operator_list";
    }
    
    @RequestMapping("/deleteoperator/{id}")
    public @ResponseBody AjaxDone deleteOperator(@PathVariable("id")Long id){
    	
    	bzInfoService.deleteOperator(id);
        
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/initeditoperator/{id}")
    public String initEditOperator(@PathVariable("id")long id,ModelMap map){
    	
    	BzOperator operator = bzInfoService.getBzOperator(id);
    	map.put("operator",operator);
    	
    	return "edit_operator";
    }
    
    @RequestMapping("/updateoperator")
    public @ResponseBody AjaxDone updateOperator(BzOperator operator){
    	
    	bzInfoService.updateOperator(operator);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/initproviderquery")
    public String initServiceProviderQuery(){
    	
    	return "serviceprovider_list";
    }
    
    @RequestMapping("/initaddprovider")
    public String initAddServiceProvider(){
    	
    	return "add_serviceprovider";
    }
    @RequestMapping("/addprovider/{navTabId}")
    public @ResponseBody AjaxDone addprovider(@PathVariable("navTabId")String navTabId,BzServiceprovider provider){
    	
    	provider.setCreateDate(new Date());
    	
    	bzInfoService.addServiceProvider(provider);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/initeditprovider/{id}")
    public String initEditProvider(@PathVariable("id")long id,ModelMap map){
    	
    	BzServiceprovider provider = bzInfoService.getServiceprovider(id);
    	map.put("provider",provider);
    	return "edit_provider";
    }
    
    
    @RequestMapping("/updateprovider")
    public @ResponseBody AjaxDone updateProvider(BzServiceprovider provider){
    	
    	bzInfoService.updateServiceProvider(provider);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/providerlist")
    public String queryServiceProviderList(ModelMap map,Pageable<BzServiceprovider> page,Condition condition){
    	
    	page = bzInfoService.findProvidersByCondition(condition, page);
        
        map.put("page", page);
    	return "serviceprovider_list";
    }
    
    @RequestMapping("/deleteprovider/{id}")
    public @ResponseBody AjaxDone deleteServiceProvider(@PathVariable("id")Long id){
    	
    	bzInfoService.deleteServiceProvider(id);
        
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/initselectoperator")
    public String initSelectOperator(){
    	return "operator_select";
    }
    
    @RequestMapping("/selectoperatorlist")
    public String queryOperatorListForSelect(ModelMap map,Pageable<BzOperator> page,Condition condition){
    	
        page = bzInfoService.findOperatorsByCondition(condition,page);
        map.put("page", page);
    	return "operator_select";
    }
    
    
    @RequestMapping("/initserverquery")
    public String initServerQuery(){
    	
    	return "server_list";
    }
    
    @RequestMapping("/initaddserver")
    public String initAddServer(){
    	
    	return "add_server";
    }
    @RequestMapping("/addserver/{navTabId}")
    public @ResponseBody AjaxDone addserver(@PathVariable("navTabId")String navTabId,BzServer server){
    	
    	server.setCreateDate(new Date());
    	
    	bzInfoService.addServer(server);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    
    @RequestMapping("/initeditserver/{id}")
    public String initEditServer(@PathVariable("id")long id,ModelMap map){
    	
    	BzServer server =  bzInfoService.getBzServer(id);
    	
    	map.put("server",server);
    	
    	return "edit_server";
    }
    
    @RequestMapping("/updateserver")
    public @ResponseBody AjaxDone updateServer(BzServer server){
    	
    	bzInfoService.updateServer(server);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/serverlist")
    public String queryServerList(ModelMap map,Pageable<BzServer> page,Condition condition){
    	
    	page = bzInfoService.findServersByCondition(condition, page);
        
        map.put("page", page);
    	return "server_list";
    }
    @RequestMapping("/queryservicerecord/{id}")
    public String queryServiceRecord(@PathVariable("id")Long id,ModelMap map,Pageable<BzCustomerBinder> page,Condition condition){
    	condition.setId(id);
    	page = bzInfoService.findServiceRecordByCondition(condition, page);
    	 map.put("page", page);
    	 map.put("serverId", id);
    	return "service_record_list";
    }
    
    
    @RequestMapping("/deleteserver/{id}")
    public @ResponseBody AjaxDone deleteServer(@PathVariable("id")Long id){
    	
    	bzInfoService.deleteServer(id);
        
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/initselectprovider")
    public String initSelectProvider(){
    	return "provider_select";
    }
    
    @RequestMapping("/selectproviderlist")
    public String queryProviderListForSelect(ModelMap map,Pageable<BzServiceprovider> page,Condition condition){
    	
    	page = bzInfoService.findProvidersByCondition(condition, page);
        map.put("page", page);
    	return "provider_select";
    }
    @RequestMapping("/initcustomerquery")
    public String initCustomerQuery(){
    	
    	return "customer_list";
    }
    
    @RequestMapping("/initaddcustomer")
    public String initAddCustomer(){
    	
    	return "add_customer";
    }
    @RequestMapping("/addcustomer")
    public @ResponseBody AjaxDone addCustomer(BzCustomer customer){
    	
    	customer.setCreateDate(new Date());
    	bzInfoService.addCustomer(customer);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/customerlist")
    public String queryCustomerList(ModelMap map,Pageable<BzCustomer> page,Condition condition){
    	
        page = bzInfoService.findCustomerByCondition(condition, page);
        map.put("page", page);
    	return "customer_list";
    }
    
    @RequestMapping("/deletecustomer/{id}")
    public @ResponseBody AjaxDone deleteCustomer(@PathVariable("id")Long id){
    	
    	bzInfoService.deleteCustomer(id);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/initeditcustomer/{id}")
    public String initEditCustomer(@PathVariable("id")long id,ModelMap map){
    	
    	BzCustomer customer = bzInfoService.getCustomer(id);
    	map.put("customer",customer);
    	
    	return "edit_customer";
    }
    
    @RequestMapping("/updatecustomer")
    public @ResponseBody AjaxDone updateCustomer(BzCustomer customer){
    	
    	bzInfoService.updateCustomer(customer);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/initcatalogmanger")
    public String initCatalogManger(){
    	return "catalog_tree";
    }
    
    @RequestMapping("/listnodetree")
    public @ResponseBody List<BzDataTree> listNodeTree(){
    	List<BzDataTree> result = bzInfoService.findDataTreeAll(null);
    	return result;
    }
    
    @RequestMapping("/toaddtreenode/{id}")
    public String toAddTreeNode(@PathVariable("id")Long id,ModelMap map){
    	map.put("pId", id);
    	return "tree_node_add";
    }
    
    @RequestMapping("/addtreenode/{navTabId}")
    public @ResponseBody AjaxDone  addTreeNode(@PathVariable("navTabId")String navTabId,BzDataTree dataTree){
    	
    	bzInfoService.addTreeNode(dataTree);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/updatetreenode/{navTabId}")
    public @ResponseBody AjaxDone  updateTreeNode(@PathVariable("navTabId")String navTabId,BzDataTree dataTree){
    	
    	bzInfoService.updateTreeNode(dataTree);
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/gettreenode/{id}")
    public String gettreenode(@PathVariable("id")Long id,ModelMap map){
    	BzDataTree dataTree = bzInfoService.getBzDataTree(id);
    	map.put("node", dataTree);
    	return "tree_node_edit";
    }
    
    @RequestMapping("/listcatalog/{dataType}")
    public @ResponseBody List<BzDataTree> listCatalog(@PathVariable("dataType") DataType dataType){
    	List<BzDataTree> result = bzInfoService.findDataTreeAll(dataType);
    	return result;
    }
    
    @RequestMapping("/addcatalog")
    public @ResponseBody BzDataTree addCataglog(String dataName,Long pid){
    	return bzInfoService.addCatalog(dataName, pid);
    }
    
    @RequestMapping("/deletecatalog/{id}")
    public @ResponseBody String deleteCatalog(@PathVariable("id")Long id){
    	bzInfoService.deleteCatalog(id);
    	return "0000";
    }
    
    @RequestMapping("/updatecatalog")
    public @ResponseBody String updateCatalog(String dataName,Long id){
    	bzInfoService.updateCataglog(id, dataName);
    	return "0000";
    }
    
    @RequestMapping("/cataloglookup")
    public String catalogLookup(ModelMap map){
    	return "catalog_lookup";
    }
    
    @RequestMapping("/adddict/{navTabId}")
    public @ResponseBody AjaxDone  addDict(@PathVariable("navTabId")String navTabId,SystemDict systemDict) {
        try {
        	resourceService.addSystemDict(systemDict);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }

    @RequestMapping("/toadddict")
    public String toAddDict() {
        return "add_dict";
    }

    @RequestMapping("/todictquery")
    public String toDictQuery(ModelMap map) {
        return "dict_list";
    }

    @RequestMapping("/dictlist")
    public String queryDictList(ModelMap map,com.visfull.system.vo.Pageable<SystemDict> page,com.visfull.system.vo.Condition condition) {
        
        page = resourceService.findDictByPage(condition,page.getPageSize(),page.getPageNo());
        map.put("page", page);
        return "dict_list";
    }
    
    @RequestMapping("/deletedict/{id}")
    public @ResponseBody AjaxDone deleteDict(@PathVariable("id")Long id){
        
    	resourceService.deleteSystemDict(id);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        return ajaxDone;
    }
    
    @RequestMapping("/toupdatedict/{id}")
    public String toEditDict(@PathVariable("id")Long id,ModelMap map){
    	SystemDict systemDict = resourceService.getSystemDict(id);
    	map.put("dict",systemDict);
    	return "edit_dict";
    }
    
    @RequestMapping("/updatedict/{navTabId}")
    public @ResponseBody AjaxDone updateDict(@PathVariable("navTabId")String navTabId,SystemDict systemDict){
    	
    	resourceService.updateSystemDict(systemDict);
    	
        AjaxDone ajaxDone = new AjaxDone();
        ajaxDone.setStatusCode("200");
        ajaxDone.setMessage("success!");
        ajaxDone.setRel(navTabId);
        ajaxDone.setCallbackType("closeCurrent");
        return ajaxDone;
    }
    
    @RequestMapping("/citylist/{value}")
    public @ResponseBody Map<Object,String> queryCityList(@PathVariable("value")Integer provinceId){
        Map<Object,String> resultMap = new HashMap<Object, String>();
    	List<City> result = bzInfoService.findCityList(provinceId);
    	if(result!=null&&!result.isEmpty()){
    		for (City city : result) {
				resultMap.put(city.getId(),city.getName());
			}
    	}
        return resultMap;
    }
    
    @RequestMapping("/countylist/{value}")
    public @ResponseBody Map<Object,String> queryCountyList(@PathVariable("value")Integer cityId){
        Map<Object,String> resultMap = new HashMap<Object, String>();
    	List<County> result = bzInfoService.findCountyList(cityId);
    	if(result!=null&&!result.isEmpty()){
    		for (County county : result) {
				resultMap.put(county.getId(),county.getName());
			}
    	}
        return resultMap;
    }
}
