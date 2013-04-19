package com.visfull.facade.service;

import java.util.List;
import java.util.Map;

import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.vo.Condition;
import com.visfull.utils.JsonResult;
import com.visfull.vo.Operator;
import com.visfull.vo.ResultBean;
import com.visfull.vo.Server;
import com.visfull.vo.ServiceProvider;

public interface FacadeService {
	
	Map<String, List<String>> findBlackWhiteList(Condition condition);
	
	Operator getOperatorByCode(String code);
	
	List<ServiceProvider> findServiceProviders(long pId,long spId);
	
	List<Server> findServers(long spId,long id);
	
	JsonResult bindServerAndCustomer(String jsonString);
	
	ResultBean addOperator(String jsonString);
	
	ResultBean addServiceProvider(String jsonString);
	
	ResultBean addServer(String jsonString);
	
	void updateOperator(String jsonString);
	
	void updateServiceProvider(String jsonString);
	
	void updateServer(String jsonString);
	
	ResultBean appUserLogin(String code,String pwd,BzCustomerBinder.TargetType userType);
	
	
	List<BzServiceprovider> findBzServiceproviders(long pId);
	
	List<BzServer> findBzServers(long spId);
	
	ResultBean updateCustomer(String jsonString);
	
	ResultBean uploadCallRecord(String jsonString);
	
	ResultBean findservicerecord(Condition condition);
	
	ResultBean findbinderrecord(Condition condition);
	
	ResultBean updateServiceRecord(String jsonString);
	
	List<BzDataTree> findBzDataTrees(DataType dataType);
	
	public void deletePosters(String posterIds);
	
	public ResultBean getOperatorJsonResult(Long opId,String opCode);
	
	public ResultBean getServiceProviderJsonResult(Long spId,String spCode);
	
	public ResultBean getServerJsonResult(Long serverId,String serverCode);
	
	public void addPositionInfo(String jsonString);
}
