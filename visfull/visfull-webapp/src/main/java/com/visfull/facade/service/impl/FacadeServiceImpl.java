package com.visfull.facade.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzBlackWhite.PhoneType;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzPositionInfo;
import com.visfull.bz.domain.BzPoster;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.emnu.TargetType;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.vo.Condition;
import com.visfull.facade.service.FacadeService;
import com.visfull.utils.JsonResult;
import com.visfull.utils.JsonUtils;
import com.visfull.vo.CustomerBinder;
import com.visfull.vo.CustomerBinderBean;
import com.visfull.vo.CustomerServiceBean;
import com.visfull.vo.Operator;
import com.visfull.vo.ResultBean;
import com.visfull.vo.Server;
import com.visfull.vo.ServiceProvider;

@Service
public class FacadeServiceImpl implements FacadeService {
	
	private static Logger logger = LoggerFactory.getLogger(FacadeServiceImpl.class);
	
	@Autowired
	private BzInfoService bzInfoService;
	@Autowired
	private ConfigInfo configInfo;
	
	
	public Map<String, List<String>> findBlackWhiteList(Condition condition) {
		
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		List<BzBlackWhite> data = bzInfoService.findBlackWhites(condition);
		
		List<String> whiteList = new ArrayList<String>();
		List<String> blackList = new ArrayList<String>();
		
		for (BzBlackWhite blackWhite : data) {
			if (PhoneType.BLACK.equals(blackWhite.getType())) {
				blackList.add(blackWhite.getPhone());
			}
			if (PhoneType.WHITE.equals(blackWhite.getType())) {
				whiteList.add(blackWhite.getPhone());
			}
		}
		result.put("white", whiteList);
		result.put("black", blackList);
		
		return result;
	}

	public Operator getOperatorByCode(String code) {
		BzOperator bzOperator = bzInfoService.getOperatorByCode(code);
		Operator operator = new Operator(); 
		operator.setId(bzOperator.getId());
		operator.setName(bzOperator.getOpName());
		operator.setCode(bzOperator.getOpCode());
		operator.setPhoneNo(bzOperator.getOpPhone());
		operator.setServiceIntroduce(bzOperator.getOpServiceintroduce());
		return operator;
	}

	public List<ServiceProvider> findServiceProviders(long pId, long spId) {
		
		List<ServiceProvider> result = new ArrayList<ServiceProvider>();
		List<BzServiceprovider> data = bzInfoService.findServiceproviders(pId, spId);
		
		for (BzServiceprovider bzServiceprovider : data) {
			ServiceProvider serviceProvider = new ServiceProvider();
			
			serviceProvider.setId(bzServiceprovider.getId());
			serviceProvider.setCode(bzServiceprovider.getServiceCode());
			serviceProvider.setLinkMan(bzServiceprovider.getLinkMan());
			serviceProvider.setName(bzServiceprovider.getServiceName());
			serviceProvider.setPhoneNo(bzServiceprovider.getPhoneNo());
			serviceProvider.setServiceIntroduce(bzServiceprovider.getServiceIntroduce());
			serviceProvider.setpId(bzServiceprovider.getOpId());
			result.add(serviceProvider);
		}
		return result;
	}

	public List<Server> findServers(long spId, long id) {
		List<Server> result = new ArrayList<Server>();
		List<BzServer> data = bzInfoService.findServers(spId, id);
		if(data != null && data.size()> 0){
			for (BzServer bzServer : data) {
				Server server = new Server();
				server.setId(bzServer.getId());
				server.setName(bzServer.getServerName());
				server.setPhoneNo(bzServer.getPhone());
				server.setSkillDescribe(bzServer.getSkillIntroduce());
				server.setSpId(bzServer.getSpId());
				result.add(server);
			}
		}
		return result;
	}

	public JsonResult bindServerAndCustomer(String jsonString) {
		JsonResult jsonResult = null;
		
		CustomerBinderBean customerBinderBean = JsonUtils.fromJson(jsonString, CustomerBinderBean.class);
		
		if(customerBinderBean != null&&customerBinderBean.getCustomerServiceInfo()!=null&&!customerBinderBean.getCustomerServiceInfo().isEmpty()){
			try {
				List<CustomerServiceBean> customerServiceBeans = customerBinderBean.getCustomerServiceInfo();
				BzCustomerBinder customerBinder = null;
				for (CustomerServiceBean customerServiceBean : customerServiceBeans) {
					BzCustomer cust = bzInfoService.getCustomerByPhone(customerServiceBean.getCustomerPhone());
					if(cust == null){
						BzCustomer customer = new BzCustomer();
						customer.setName(customerServiceBean.getCustomerName());
						customer.setBindPhone(customerServiceBean.getCustomerPhone());
						customer.setCreateDate(new Date());
						bzInfoService.addCustomer(customer);
						customerServiceBean.setCustomerId(customer.getId());
					}else{
						customerServiceBean.setCustomerId(cust.getId());
					}
					customerBinder = bzInfoService.findCustomerBinder(customerBinderBean.getTargetCode(), customerServiceBean.getCustomerPhone());
					if(customerBinder!=null){
						
						customerBinder.setTargetId(customerBinderBean.getTargetId());
						customerBinder.setTargetName(customerBinderBean.getTargetName());
						customerBinder.setTargetType(customerBinderBean.getTargetType());
						customerBinder.setCustomerId(customerServiceBean.getCustomerId());
						customerBinder.setCustomerName(customerServiceBean.getCustomerName());
						customerBinder.setDuration(customerServiceBean.getDuration()+customerBinder.getDuration());
						customerBinder.setCustomerPhone(customerServiceBean.getCustomerPhone());
						customerBinder.setServiceInfo(customerBinder.getServiceInfo()+"^"+customerServiceBean.getServiceInfo());
						customerBinder.setServiceDate(customerServiceBean.getServiceDate());
						customerBinder.setTargetCode(customerBinderBean.getTargetCode());
						
						bzInfoService.updateCustomerBinder(customerBinder);
						
					}else{
						customerBinder = new BzCustomerBinder();
						customerBinder.setTargetId(customerBinderBean.getTargetId());
						customerBinder.setTargetName(customerBinderBean.getTargetName());
						customerBinder.setTargetType(customerBinderBean.getTargetType());
						customerBinder.setCustomerId(customerServiceBean.getCustomerId());
						customerBinder.setCustomerName(customerServiceBean.getCustomerName());
						customerBinder.setDuration(customerServiceBean.getDuration());
						customerBinder.setCustomerPhone(customerServiceBean.getCustomerPhone());
						customerBinder.setServiceInfo(customerServiceBean.getServiceInfo());
						customerBinder.setServiceDate(customerServiceBean.getServiceDate());
						customerBinder.setTargetCode(customerBinderBean.getTargetCode());
						customerBinder.setCreateDate(new Date());
						
						bzInfoService.addCustomerBinder(customerBinder);
					}

					
					List<BzPoster> posters = customerServiceBean.getPosters();
					if (posters!=null&&!posters.isEmpty()) {
						for (BzPoster bzPoster : posters) {
							bzPoster.setCreateDate(new Date());
							bzPoster.setOwner(customerBinder.getId());
							bzInfoService.addPoster(bzPoster);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				jsonResult = new JsonResult("0001","数据插入异常！");
			}
		}else {
			jsonResult = new JsonResult("0002","json 报文格式异常！");
		}
		jsonResult = new JsonResult("0000","上传服务人员服务记录成功！");
		return jsonResult;
	}

	public ResultBean appUserLogin(String code, String pwd, BzCustomerBinder.TargetType userType) {
		
		
		BzOperator bzOperator = bzInfoService.getOperatorByCode(code);
		
		if (bzOperator != null) {
			String password = bzOperator.getOpPwd();
			if(pwd.equals(password)){
				List<Object> dataoObjects = new ArrayList<Object>();
				dataoObjects.add(bzOperator);
				return new ResultBean("0000","登录成功！",code,TargetType.SP,dataoObjects);
			}else {
				return new ResultBean("0003","密码错误！",code,TargetType.SP,null);
			}
		}
		
		BzServiceprovider bzServiceprovider = bzInfoService.getServiceproviderByCode(code);
		if (bzServiceprovider != null) {
			String password = bzServiceprovider.getServicePwd();
			if(pwd.equals(password)){
				List<Object> dataoObjects = new ArrayList<Object>();
				dataoObjects.add(bzServiceprovider);
				return new ResultBean("0000","登录成功！",code,TargetType.CP,dataoObjects);
			}else {
				return new ResultBean("0003","密码错误！",code,TargetType.CP,null);
			}
		}
		BzServer bzServer = bzInfoService.getBzServerByCode(code);
		
		if (bzServer != null) {
			String password = bzServer.getPwd();
			if(pwd.equals(password)){
				List<BzPoster> posters = bzInfoService.findBzPosters(bzServer.getId());
				bzServer.setPosters(posters);
				List<Object> dataoObjects = new ArrayList<Object>();
				dataoObjects.add(bzServer);
				return new ResultBean("0000","登录成功！",code,TargetType.SERVER,dataoObjects);
			}else {
				return new ResultBean("0003","密码错误！",code,TargetType.SERVER,null);
			}
		}
		
		return  new ResultBean("0002","未知用户！",code,null,null);
		
	}
	
	private ResultBean spLogin(String code,String pwd){
		ResultBean result = null;
		BzOperator bzOperator = bzInfoService.getOperatorByCode(code);
		if (bzOperator != null) {
			String password = bzOperator.getOpPwd();
			if(pwd.equals(password)){
				List<Object> dataoObjects = new ArrayList<Object>();
				dataoObjects.add(bzOperator);
				result = new ResultBean("0000","登录成功！",dataoObjects);
			}else {
				result = new ResultBean("0003","密码错误！",null);
			}
		}else {
			result = new ResultBean("0002","未知用户！",null);
		}
		return result;
	}
	

	public ResultBean addOperator(String jsonString) {
		ResultBean result = new ResultBean();
		
		BzOperator bzOperator = null;
		try {
			bzOperator = JsonUtils.fromJson(jsonString, BzOperator.class);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("sp json 格式异常！");
			return result;
		}
		try {
			bzOperator.setCreateDate(new Date());
			bzInfoService.addOperator(bzOperator);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("增加SP异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("增加SP成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(bzOperator);
		result.setDataObjects(data);
		return result;
	}

	public ResultBean addServiceProvider(String jsonString) {
		ResultBean result = new ResultBean();
		
		BzServiceprovider serviceprovider = null;
		try {
			serviceprovider = JsonUtils.fromJson(jsonString, BzServiceprovider.class);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("cp json 格式异常！");
			return result;
		}
		try {
			serviceprovider.setCreateDate(new Date());
			bzInfoService.addServiceProvider(serviceprovider);
			List<BzPoster> posters = serviceprovider.getPosters();
			if (posters!=null&&!posters.isEmpty()) {
				for (BzPoster bzPoster : posters) {
					bzPoster.setCreateDate(new Date());
					bzPoster.setOwner(serviceprovider.getId());
					bzPoster.setOwnerType(TargetType.SERVER);
					bzInfoService.addPoster(bzPoster);
				}
			}
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("增加cp 异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("增加CP成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(serviceprovider);
		result.setDataObjects(data);
		return result;
	}

	public ResultBean addServer(String jsonString) {
		ResultBean result = new ResultBean();
		
		BzServer server = null;
		try {
			server = JsonUtils.fromJson(jsonString, BzServer.class);
		} catch (Exception e) {
			result.setResultCode("0001");
			result.setMessage("server json 格式异常！");
			return result;
		}
		try {
			server.setCreateDate(new Date());
			bzInfoService.addServer(server);
			List<BzPoster> posters = server.getPosters();
			if (posters!=null&&!posters.isEmpty()) {
				for (BzPoster bzPoster : posters) {
					bzPoster.setCreateDate(new Date());
					bzPoster.setOwner(server.getId());
					bzPoster.setOwnerType(TargetType.SERVER);
					bzInfoService.addPoster(bzPoster);
				}
			}
		} catch (Exception e) {
			result.setResultCode("0001");
			result.setMessage("增加server异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("增加server成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(server);
		result.setDataObjects(data);
		return result;
	}

	public void updateOperator(String jsonString) {
		BzOperator bzOperator = JsonUtils.fromJson(jsonString, BzOperator.class);
		bzInfoService.updateOperator(bzOperator);
	}

	public void updateServiceProvider(String jsonString) {
		BzServiceprovider serviceprovider = JsonUtils.fromJson(jsonString, BzServiceprovider.class);
		bzInfoService.updateServiceProvider(serviceprovider);
		if(serviceprovider.getPosters()!=null&&!serviceprovider.getPosters().isEmpty()){
			bzInfoService.updatePosters(serviceprovider.getPosters());
		}
	}

	public void updateServer(String jsonString) {
		BzServer server = JsonUtils.fromJson(jsonString, BzServer.class);
		bzInfoService.updateServer(server);
		if(server.getPosters()!=null&&!server.getPosters().isEmpty()){
			bzInfoService.updatePosters(server.getPosters());
		}
	}

	public List<BzServiceprovider> findBzServiceproviders(long pId) {
		return bzInfoService.findServiceproviders(pId, 0L);
	}

	public List<BzServer> findBzServers(long spId) {
		return bzInfoService.findServers(spId, 0L);
	}

	public ResultBean updateCustomer(String jsonString) {
		ResultBean result = new ResultBean();
		BzCustomer bzCustomer = null;
		try {
			bzCustomer = JsonUtils.fromJson(jsonString, BzCustomer.class);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("json格式异常！");
			return result;
		}
		try {
			bzInfoService.updateCustomer(bzCustomer);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("更新用户信息异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("更新用户信息成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(bzCustomer);
		result.setDataObjects(data);
		return result;
	}

	public ResultBean uploadCallRecord(String jsonString) {
		ResultBean result = new ResultBean();
		List<BzCallRecord> records = null;
		try {
			records = JsonUtils.fromJson(jsonString, new TypeToken<List<BzCallRecord>>(){}.getType());
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("json格式异常！");
			return result;
		}
		try {
			bzInfoService.saveCallRecords(records);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("插入来电信息异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("插入来电信息成功！");
		return result;
		
	}

	public ResultBean findservicerecord(Condition condition) {
		ResultBean result = new ResultBean();
		try {
			List<BzCustomerBinder> dataBinders = bzInfoService.findCustomerBinders(condition.getTargetCode(),condition.getTargetType(), condition.getStartDate(),condition.getEndDate());
			List<Object> datalList = new ArrayList<Object>();
			datalList.addAll(dataBinders);
			result.setDataObjects(datalList);
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("查询服务信息异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("查询服务信息成功！");
		return result;
	}

	public ResultBean findbinderrecord(Condition condition) {
		ResultBean result = new ResultBean();
		List<Object> dataList = new ArrayList<Object>();
		try {
			List<BzCustomerBinder>  data = bzInfoService.findCustomerBindersCustomer(condition.getPhone(),condition.getStartDate(),condition.getEndDate());
			if(data!=null&&!data.isEmpty()){
				for (BzCustomerBinder bzCustomerBinder : data) {
					
					CustomerBinder customerBinder = new CustomerBinder();
					customerBinder.setCustomerId(bzCustomerBinder.getCustomerId());
					customerBinder.setCustomerName(bzCustomerBinder.getCustomerName());
					customerBinder.setCustomerPhone(bzCustomerBinder.getCustomerPhone());
					customerBinder.setTargetCode(bzCustomerBinder.getTargetCode());
					customerBinder.setTargetId(bzCustomerBinder.getTargetId());
					customerBinder.setTargetName(bzCustomerBinder.getTargetName());
					customerBinder.setTargetType(bzCustomerBinder.getTargetType());
					customerBinder.setCreateDate(bzCustomerBinder.getCreateDate());
					
					dataList.add(customerBinder);
				}
				result.setDataObjects(dataList);
			}
			
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("查询绑定信息异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("查询绑定信息成功！");
		return result;
	}

	public ResultBean updateServiceRecord(String jsonString) {
		ResultBean result = new ResultBean();
		
		List<BzCustomerBinder> customerBinders = null;
		try {
			customerBinders = JsonUtils.fromJson(jsonString, new TypeToken<List<BzCustomerBinder>>(){}.getType());
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("json格式异常！");
			return result;
		}
		
		try {
			for (BzCustomerBinder bzCustomerBinder : customerBinders) {
				bzInfoService.updateCustomerBinder(bzCustomerBinder);
				
				List<BzPoster> posters = bzCustomerBinder.getPosters();
				if (posters!=null&&!posters.isEmpty()) {
					for (BzPoster bzPoster : posters) {
						bzPoster.setCreateDate(new Date());
						bzPoster.setOwner(bzCustomerBinder.getId());
						bzInfoService.addPoster(bzPoster);
					}
				}
			}
		} catch (Exception e) {
			logger.debug(ExceptionUtils.getFullStackTrace(e));
			result.setResultCode("0001");
			result.setMessage("修改服务记录异常！");
			return result;
		}
		result.setResultCode("0000");
		result.setMessage("修改服务成功！");
		return result;
	}

	public List<BzDataTree> findBzDataTrees(DataType dataType) {
		List<BzDataTree> catalogs = new ArrayList<BzDataTree>();
		
		catalogs = bzInfoService.findDataTreeAll(dataType,NodeType.NODE);
		
		return catalogs;
	}

	public void deletePosters(String posterIds) {
		List<Long> posterIdList = new ArrayList<Long>();
		if(posterIds!=null&&!"".equals(posterIds)){
			String[] ids = posterIds.split(",");
			for (String string : ids) {
				posterIdList.add(Long.parseLong(string));
			}
			List<BzPoster> posters = bzInfoService.findBzPosters(posterIdList);
			for (BzPoster bzPoster : posters) {
				String url = bzPoster.getPosterUrl();
				url.replace(configInfo.getUploadPath(),configInfo.getHostPrefix());
				File file = new File(url);
				if(file.exists()){
					file.delete();
				}
			}
			bzInfoService.deletePosters(posterIdList);
		}		
	}

	public ResultBean getOperatorJsonResult(Long opId, String opCode) {
		ResultBean resultBean = new ResultBean();
		BzOperator operator = null;
		try {
			if (opId !=null && opId >0) {
				operator = bzInfoService.getBzOperator(opId);
			}
			if(opCode!=null&&!"".equals(opCode)){
				operator = bzInfoService.getOperatorByCode(opCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setResultCode("0001");
			resultBean.setMessage("获取运营商信息异常！");
		}
		
		resultBean.setResultCode("0000");
		resultBean.setMessage("获取运营商信息成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(operator);
		resultBean.setDataObjects(data);
		return resultBean;
	}

	public ResultBean getServiceProviderJsonResult(Long spId, String spCode) {
		ResultBean resultBean = new ResultBean();
		BzServiceprovider serviceProvider = null;
		try {
			if (spId !=null && spId >0) {
				serviceProvider = bzInfoService.getServiceprovider(spId);
			}
			if(spCode!=null&&!"".equals(spCode)){
				serviceProvider = bzInfoService.getServiceproviderByCode(spCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setResultCode("0001");
			resultBean.setMessage("获取服务提供商信息异常！");
		}
		resultBean.setResultCode("0000");
		resultBean.setMessage("获取服务提供商信息成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(serviceProvider);
		resultBean.setDataObjects(data);
		return resultBean;
	}

	public ResultBean getServerJsonResult(Long serverId, String serverCode) {
		ResultBean resultBean = new ResultBean();
		BzServer server = null;
		try {
			if (serverId !=null && serverId >0) {
				server = bzInfoService.getBzServer(serverId);
			}
			if(serverCode!=null&&!"".equals(serverCode)){
				server = bzInfoService.getBzServerByCode(serverCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultBean.setResultCode("0001");
			resultBean.setMessage("获取服务人员信息异常！");
		}
		resultBean.setResultCode("0000");
		resultBean.setMessage("获取服务人员信息成功！");
		List<Object> data = new ArrayList<Object>();
		data.add(server);
		resultBean.setDataObjects(data);
		return resultBean;
	}

	public void addPositionInfo(String jsonString) {
		BzPositionInfo positionInfo = new BzPositionInfo();
		positionInfo.setPositionData(jsonString);
		positionInfo.setCreateDate(new Date());
		bzInfoService.addPositionInfo(positionInfo);
	}
}
