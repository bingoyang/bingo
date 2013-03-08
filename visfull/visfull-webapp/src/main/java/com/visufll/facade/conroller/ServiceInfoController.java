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

import com.visfull.bz.domain.BzSignIn;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.vo.Condition;
import com.visfull.facade.service.FacadeService;
import com.visfull.system.domain.AuthSession;
import com.visfull.system.service.SessionService;
import com.visfull.utils.AccessValidators;
import com.visfull.utils.JsonResult;
import com.visfull.utils.JsonUtils;
import com.visfull.utils.RenderUtils;
import com.visfull.vo.AreaInfo;
import com.visfull.vo.Operator;
import com.visfull.vo.ResultBean;
import com.visfull.vo.Server;
import com.visfull.vo.ServiceProvider;

@Controller
public class ServiceInfoController {
	private static Logger logger = LoggerFactory.getLogger(ServiceInfoController.class);
	
	@Autowired
	private BzInfoService bzInfoService;
	@Autowired
	private FacadeService facadeService;
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping("/blackwhitelist")
	public void getBlackWhiteList(HttpServletRequest request, HttpServletResponse response,Condition condition){
		logger.info("obtain black&white list.");
		Map<String, List<String>> result = facadeService.findBlackWhiteList(condition);
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/operator/{opCode}")
	public void getOperatorInfo(@PathVariable String opCode,HttpServletRequest request, HttpServletResponse response){
		logger.info("get operator info by : {}",opCode);
		Operator operator = facadeService.getOperatorByCode(opCode);
		RenderUtils.renderJson(JsonUtils.toJson(operator), response);
	}
	
	@RequestMapping("/serviceprovider/{opId}/{spId}")
	public void getServiceProviderInfo(@PathVariable long opId,@PathVariable long spId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get serviceprovider info by : {} ,{}",opId,spId);
		List<ServiceProvider> result = facadeService.findServiceProviders(opId, spId);
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/serviceproviderlist/{opId}")
	public void listServiceProvider(@PathVariable long opId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get listServiceProvider info by : {}",opId);
		List<ServiceProvider> result = facadeService.findServiceProviders(opId, 0L);
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/servers/{spId}/{sId}")
	public void getServerInfo(@PathVariable long spId,@PathVariable long sId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get servers info by : {},{}",new Object[]{spId,sId});
		List<Server> result = facadeService.findServers(spId, sId);
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/serverslist/{spId}")
	public void serversList(@PathVariable long spId,HttpServletRequest request, HttpServletResponse response){
		logger.info("get servers info by : {}",new Object[]{spId});
		List<Server> result = facadeService.findServers(spId, 0L);
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
	
	@RequestMapping("/signin/{phoneNo}")
	public void signIn(@PathVariable String phoneNo,HttpServletRequest request, HttpServletResponse response){
		logger.info("user sign in by : {}",phoneNo);
		BzSignIn signIn = new BzSignIn();
		signIn.setSigninPhone(phoneNo);
		signIn.setSigninTime(new Date());
		bzInfoService.addSignIn(signIn);
		JsonResult jsonResult = new JsonResult("0000","签到成功");
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping("/bindprovider/{phoneNo}/{pId}")
	public void bindProvider(@PathVariable String phoneNo,@PathVariable long pId,HttpServletRequest request, HttpServletResponse response){
		logger.info("bind user {} and service provider {}",phoneNo,pId);
		
		JsonResult jsonResult = new JsonResult("0000","绑定成功");
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping("/bindserver/{phoneNo}/{sId}")
	public void bindServer(@PathVariable String phoneNo,@PathVariable long sId,HttpServletRequest request, HttpServletResponse response){
		logger.info("bind user {} and server {}",phoneNo,sId);
		
		JsonResult jsonResult = new JsonResult("0000","绑定成功");
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping("/addserverinfo")
	public void addServerInfo(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		logger.info("add server info : {}",jsonString);
		
		JsonResult jsonResult = new JsonResult("0000","服务人员添加成功");
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping("/addpositioninfo")
	public void addPositionInfo(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		logger.info("add position info : {}",jsonString);
		
		JsonResult jsonResult = new JsonResult("0000","位置信息提交成功！");
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping("/serverbindcustomer")
	public void bindCustomer(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		logger.info("bind server bind customer: {}",jsonString);
		
		JsonResult jsonResult = facadeService.bindServerAndCustomer(jsonString);
		
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping(value="/uploadservicerecord",method=RequestMethod.POST)
	public void uploadServiceRecord(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		AuthSession session = sessionService.getSession(request.getHeader("sessionid"));
		boolean validResult = AccessValidators.validAccessUrl(session,"/uploadservicerecord");
		JsonResult jsonResult = null;
		if(validResult){
			try{
				jsonResult = facadeService.bindServerAndCustomer(jsonString);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getFullStackTrace(e));
				jsonResult = new JsonResult("0001",e.getMessage());
			}
		}else {
			jsonResult = new JsonResult("0010","权限不够，禁止访问！");
		}
		RenderUtils.renderJson(JsonUtils.toJson(jsonResult), response);
	}
	
	@RequestMapping(value="/updateservicerecord",method = RequestMethod.POST)
	public void updateServiceRecord(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		boolean validResult = AccessValidators.validAccessUrl(request.getSession(),"/updateservicerecord");
		if(true){
			try{
				result = facadeService.updateServiceRecord(jsonString);
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
	
	@RequestMapping(value="/updatecustomerinfo",method = RequestMethod.POST)
	public void updateCustomerInfo(@RequestParam String jsonString,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		boolean validResult = AccessValidators.validAccessUrl(request.getSession(),"/updatecustomerinfo");
		if(true){
			try{
				result = facadeService.updateCustomer(jsonString);
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
	
	
	@RequestMapping(value="/findservicerecord")
	public void findServiceRecord(Condition condition,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		boolean validResult = AccessValidators.validAccessUrl(request.getSession(),"/findservicerecord");
		if(true){
			try{
				result = facadeService.findservicerecord(condition);;
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
	
	@RequestMapping(value="/findbinderrecord")
	public void findBinderRecord(Condition condition,HttpServletRequest request, HttpServletResponse response){
		ResultBean result = new ResultBean();
		boolean validResult = AccessValidators.validAccessUrl(request.getSession(),"/findbinderrecord");
		if(true){
			try{
				result = facadeService.findbinderrecord(condition);
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
	
	@RequestMapping("/userrelationinfo/{phoneNo}")
	public void getUserRelationInfo(@PathVariable String phoneNo,HttpServletRequest request, HttpServletResponse response){

		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Operator> result3 = new ArrayList<Operator>();
		
		for (int i = 0; i < 15; i++) {
			
			Operator operator = new Operator();
			operator.setId(new Long(i+1));
			operator.setName("operator"+i);
			operator.setLinkMan("刘德华"+i);
			operator.setPhoneNo(phoneNo);
			operator.setServiceIntroduce("服务周到态度热情...");
			operator.setAreaInfos(new ArrayList<AreaInfo>());
			operator.setServiceProviders(new ArrayList<ServiceProvider>());
			
			result3.add(operator);
		}
		
		List<ServiceProvider> result1 = new ArrayList<ServiceProvider>();
		for (int i = 0; i < 15; i++) {
			ServiceProvider serviceProvider = new ServiceProvider();
			serviceProvider.setId(new Long(i+1));
			serviceProvider.setLinkMan("小明"+i);
			serviceProvider.setName("家政"+i);
			serviceProvider.setPhoneNo("1111111111");
			serviceProvider.setServiceIntroduce("专业敬业");
			serviceProvider.setAreaInfos(new ArrayList<AreaInfo>());
			serviceProvider.setServers(new ArrayList<Server>());
			
			result1.add(serviceProvider);
		}
		
		List<Server> result2 = new ArrayList<Server>();
		
		for (int i = 0; i < 20; i++) {
			Server server = new Server();
			server.setId(new Long(i+1));
			server.setName("小红"+i);
			server.setPhoneNo("7788521");
			server.setSkillDescribe("做饭洗碗拖地");
			result2.add(server);
		}
		
		result.put("operator",result3);
		result.put("serviceprovider", result1);
		result.put("server", result2);
		
		RenderUtils.renderJson(JsonUtils.toJson(result), response);
	}
		
}
