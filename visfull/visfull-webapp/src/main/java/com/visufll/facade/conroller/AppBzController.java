package com.visufll.facade.conroller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzOperator.OpStatus;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServer.ServerStatus;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.domain.BzServiceprovider.SpStatus;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.vo.Condition;
import com.visfull.facade.service.FacadeService;
import com.visfull.system.domain.AuthSession;
import com.visfull.system.service.SessionService;
import com.visfull.utils.AccessValidators;
import com.visfull.utils.JsonUtils;
import com.visfull.utils.RenderUtils;
import com.visfull.utils.SessionUtil;
import com.visfull.vo.ResultBean;

@Controller
@RequestMapping("/app")
public class AppBzController {
	
	private static Logger logger = LoggerFactory.getLogger(AppBzController.class);
	
	@Autowired
	private BzInfoService bzInfoService;
	@Autowired
	private FacadeService facadeService;
	@Autowired
	private SessionService sessionService;
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public void appLogin(@RequestParam("code")String code,@RequestParam("password")String pwd,HttpServletResponse response,HttpServletRequest request){		
		ResultBean result = new ResultBean();
		try {
			result = facadeService.appUserLogin(code, pwd, null);
			if("0000".equals(result.getResultCode())){
				AuthSession session = new AuthSession();
				Map<String,Object> data = new HashMap<String, Object>();
				data.put("code",code);
				data.put("userType",result.getUserType());
				data.put("loginTime", new Date());
				session.setId(SessionUtil.generateSessionId());
				session.setData(JsonUtils.toJson(data));
				session.setCreateDate(new Date());
				sessionService.saveSession(session);
				response.addHeader("sessionid", session.getId());
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage(e.getMessage());
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping(value = "/addoperator",method = RequestMethod.POST)
	public void addOperator(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/addoperator");
		if(validResult){
			try{
				result = facadeService.addOperator(jsonString);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping(value = "/addserviceprovider",method = RequestMethod.POST)
	public void addServiceProvider(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/addserviceprovider");
		if(validResult){
			try{
				result = facadeService.addServiceProvider(jsonString);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping(value = "/addserver",method = RequestMethod.POST)
	public void addServer(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/addserver");
		if(validResult){
			try{
				result = facadeService.addServer(jsonString);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
    @RequestMapping(value = "/deleteoperator/{id}",method = RequestMethod.GET)
    public void deleteOperator(@PathVariable("id")Long id,HttpServletResponse response,HttpServletRequest request){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/deleteoperator");
		if(validResult){
			try{
				BzOperator operator = new BzOperator();
				operator.setId(id);
				operator.setStatus(OpStatus.STOP);
				bzInfoService.updateOperator(operator);
				result.setResultCode("0000");
				result.setMessage("删除运营商成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
	
    @RequestMapping("/deleteprovider/{id}")
    public void deleteServiceProvider(@PathVariable("id")Long id,HttpServletResponse response,HttpServletRequest request){
    	
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/deleteprovider");
		if(validResult){
			try{
				BzServiceprovider serviceprovider = new BzServiceprovider();
				serviceprovider.setId(id);
				serviceprovider.setStatus(SpStatus.STOP);
				bzInfoService.updateServiceProvider(serviceprovider);
				result.setResultCode("0000");
				result.setMessage("删除服务提供商成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
	
    @RequestMapping("/deleteserver/{id}")
    public void deleteServer(@PathVariable("id")Long id,HttpServletResponse response,HttpServletRequest request){
    	
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/deleteserver");
		if(validResult){
			try{
				BzServer server = new BzServer();
				server.setId(id);
				server.setStatus(ServerStatus.STOP);
		    	bzInfoService.updateServer(server);
				result.setResultCode("0000");
				result.setMessage("删除服务人员成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
    
    @RequestMapping(value= "/updateoperator",method = RequestMethod.POST)
    public void updateOperator(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
    	
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/updateoperator");
		if(validResult){
			try{
				facadeService.updateOperator(jsonString);
				result.setResultCode("0000");
				result.setMessage("更新运营商信息成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
    
    @RequestMapping(value= "/updateserviceprovider",method = RequestMethod.POST)
    public void updateServiceProvider(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
    	
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/updateserviceprovider");
		if(validResult){
			try{
				facadeService.updateServiceProvider(jsonString);
				result.setResultCode("0000");
				result.setMessage("更新服务提供商成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
    
    @RequestMapping(value= "/updateserver",method = RequestMethod.POST)
    public void updateServer(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
    	
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/updateserver");
		if(validResult){
			try{
				facadeService.updateServer(jsonString);
				result.setResultCode("0000");
				result.setMessage("更新服务人员信息成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
    }
    
	@RequestMapping("/serviceproviderlist/{opId}")
	public void listServiceProvider(@PathVariable long opId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get listServiceProvider info by : {}",opId);
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/serviceproviderlist");
		if(validResult){
			try{
				List<BzServiceprovider> dataProviders = facadeService.findBzServiceproviders(opId);
				List<Object> dataObjects = new ArrayList<Object>();
				dataObjects.addAll(dataProviders);
				result.setResultCode("0000");
				result.setMessage("获取服务提供商列表成功！");
				result.setDataObjects(dataObjects);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/servers/{spId}")
	public void getServerInfo(@PathVariable long spId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get servers info by : {}",new Object[]{spId});
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/servers");
		ResultBean result = new ResultBean();
		if(true){
			try{
				List<BzServer> dataServers = facadeService.findBzServers(spId);
				List<Object> dataObjects = new ArrayList<Object>();
				dataObjects.addAll(dataServers);
				result.setResultCode("0000");
				result.setMessage("获取服务人员列表成功！");
				result.setDataObjects(dataObjects);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	
	
	@RequestMapping("/calling/record/{callingNum}/{calledNum}/{currentTime}/{duration}")
	public void receiveCallRecord(@PathVariable("callingNum")String callingNum,@PathVariable("calledNum")String calledNum,@PathVariable("currentTime")Long currentTime,@PathVariable("duration")Long duration,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		boolean validResult = AccessValidators.validAccessUrl(request.getSession(),"/calling/record/");
		if(true){
			try{
				bzInfoService.recordCallIn(callingNum, calledNum, currentTime,duration);
				result.setResultCode("0000");
				result.setMessage("记录来电信息成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping(value="/uploadcallrecord",method = RequestMethod.POST)
	public void uploadCallRecord(@RequestParam("jsonString")String jsonString,HttpServletResponse response,HttpServletRequest request){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/uploadcallrecord");
		if(validResult){
			try{
				result = facadeService.uploadCallRecord(jsonString);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/queryserviceprovider")
	public void queryServiceProvider(HttpServletRequest request, HttpServletResponse response,Condition condition){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/queryserviceprovider");
		if(true){
			try{
				List<BzServiceprovider> dataProviders = bzInfoService.findProvidersByCondition(condition);
				List<Object> dataObjects = new ArrayList<Object>();
				dataObjects.addAll(dataProviders);
				result.setResultCode("0000");
				result.setMessage("获取服务提供商列表成功！");
				result.setDataObjects(dataObjects);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/querycataloginfo")
	public void queryCatalogInfo(HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/querycataloginfo");
		if(true){
			try{
				List<BzDataTree> catalogs = facadeService.findBzDataTrees(DataType.CATALOG);
				List<Object> dataObjects = new ArrayList<Object>();
				dataObjects.addAll(catalogs);
				result.setResultCode("0000");
				result.setMessage("获取分类信息列表成功！");
				result.setDataObjects(dataObjects);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/deleteposter/{posterIds}")
	public void deletePoster(@PathVariable String posterIds,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
//		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
//		boolean validResult = AccessValidators.validAccessUrl(session,"/deleteposter");
		if(true){
			try{
				facadeService.deletePosters(posterIds);
				result.setResultCode("0000");
				result.setMessage("删除海报成功！");
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				result.setResultCode("0001");
				result.setMessage(e.getMessage());
			}
		}else {
			result.setResultCode("0010");
			result.setMessage("权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
}
