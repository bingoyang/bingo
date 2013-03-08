package com.visfull.bz.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visfull.bz.dao.BlackWhiteDao;
import com.visfull.bz.dao.CallRecordDao;
import com.visfull.bz.dao.CustomerBinderDao;
import com.visfull.bz.dao.CustomerDao;
import com.visfull.bz.dao.DataTreeDao;
import com.visfull.bz.dao.OperatorDao;
import com.visfull.bz.dao.PosterDao;
import com.visfull.bz.dao.ServerDao;
import com.visfull.bz.dao.ServiceProviderDao;
import com.visfull.bz.dao.SignInDao;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzPoster;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.domain.BzSignIn;
import com.visfull.bz.emnu.TargetType;
import com.visfull.bz.service.BzInfoService;
import com.visfull.bz.util.BeanUtils;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

@Service
public class BzInfoServiceImpl implements BzInfoService {

	@Autowired
	private BlackWhiteDao blackWhiteDao;
	@Autowired
	private OperatorDao operatorDao;
	@Autowired
	private ServiceProviderDao serviceProviderDao;
	@Autowired
	private ServerDao serverDao;
	@Autowired
	private SignInDao signInDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerBinderDao customerBinderDao;
	@Autowired
	private CallRecordDao callRecordDao;
	@Autowired
	private DataTreeDao dataTreeDao;
	@Autowired
	private PosterDao posterDao;


	public void addOperator(BzOperator operator) {
		operatorDao.save(operator);
	}

	public void deleteOperator(Long id) {
		operatorDao.deleteByPK(id);
	}

	public void updateOperator(BzOperator operator) {
		BzOperator d = operatorDao.findByPK(operator.getId());
		try {
			BeanUtils.copyProperties(d, operator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pageable<BzOperator> findOperatorsByCondition(Condition condition,
			Pageable<BzOperator> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();

		page = operatorDao.findOperatorByPage(condition, pageSize, pageNo);

		return page;
	}

	public BzServer addServer(BzServer server) {
		serverDao.save(server);
		return server;
	}

	public void deleteServer(Long id) {
		serverDao.deleteByPK(id);
	}

	public void updateServer(BzServer server) {
		BzServer d = serverDao.findByPK(server.getId());
		try {
			BeanUtils.copyProperties(d, server);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pageable<BzServer> findServersByCondition(Condition condition,
			Pageable<BzServer> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();

		page = serverDao.findServerByPage(condition, pageSize, pageNo);
		return page;
	}

	public void addBlackWhite(BzBlackWhite blackWhite) throws Exception {
		blackWhiteDao.save(blackWhite);
	}

	public List<BzBlackWhite> findBlackWhiteAll() {
		return blackWhiteDao.findBlackWhiteAll();
	}

	public Pageable<BzBlackWhite> findBlackWhiteByPage(Condition condition,
			Pageable<BzBlackWhite> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();

		page = blackWhiteDao.findBlackWhitesByPage(condition, pageSize, pageNo);
		return page;
	}

	public void deleteBlackWhite(Long id) {
		blackWhiteDao.deleteByPK(id);
	}

	public void addServiceProvider(BzServiceprovider serviceprovider) {
		serviceProviderDao.save(serviceprovider);
	}

	public void deleteServiceProvider(Long id) {
		serviceProviderDao.deleteByPK(id);
	}

	public void updateServiceProvider(BzServiceprovider serviceprovider) {
		BzServiceprovider d = serviceProviderDao.findByPK(serviceprovider
				.getId());
		try {
			BeanUtils.copyProperties(d, serviceprovider);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pageable<BzServiceprovider> findProvidersByCondition(
			Condition condition, Pageable<BzServiceprovider> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();

		page = serviceProviderDao.findProviderByPage(condition, pageSize,
				pageNo);
		return page;
	}

	public void addSignIn(BzSignIn signIn) {
		signInDao.save(signIn);
	}

	public List<BzBlackWhite> findBlackWhites(Condition condition) {
		return blackWhiteDao.findBlackWhites(condition);
	}

	public BzOperator getOperatorByCode(String opCode) {
		return operatorDao.findOperatorByCode(opCode);
	}

	public List<BzServiceprovider> findServiceproviders(long pId, long spId) {

		return serviceProviderDao.findBzServiceproviders(pId, spId);
	}

	public List<BzServer> findServers(long spId, long id) {
		List<BzServer> servers = serverDao.findBzServers(spId, id);
		
		if(servers!=null&&!servers.isEmpty()){
			for (BzServer bzServer : servers) {
				bzServer.setPosters(posterDao.findBzPosters(bzServer.getId()));
			}
		}
		return servers;
	}

	public BzBlackWhite getBlackWhite(Long id) {
		return blackWhiteDao.findByPK(id);
	}

	public void updateBlackWhite(BzBlackWhite blackWhite) {
		BzBlackWhite d = blackWhiteDao.findByPK(blackWhite.getId());
		d.setPhone(blackWhite.getPhone());
		d.setType(blackWhite.getType());
		// blackWhiteDao.update(blackWhite);
	}

	public BzOperator getBzOperator(Long id) {
		return operatorDao.findByPK(id);
	}

	public void addCustomer(BzCustomer customer) {
		customerDao.save(customer);
	}

	public void addCustomerBinders(List<BzCustomerBinder> customerBinders) {

	}

	public void addCustomerBinder(BzCustomerBinder customerBinder) {
		customerBinderDao.save(customerBinder);
	}

	public BzCustomer getCustomerByPhone(String phone) {
		return customerDao.getCustomerByPhone(phone);
	}

	public BzServer getBzServerByCode(String serverCode) {
		return serverDao.getBzServerByCode(serverCode);
	}

	public BzServiceprovider getServiceproviderByCode(String spCode) {
		return serviceProviderDao.getServiceproviderByCode(spCode);
	}

	public BzServiceprovider getServiceprovider(Long id) {
		return serviceProviderDao.findByPK(id);
	}

	public BzServer getBzServer(Long id) {

		return serverDao.findByPK(id);
	}

	public Pageable<BzCustomer> findCustomerByCondition(Condition condition,
			Pageable<BzCustomer> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();
		return customerDao.findCustomerByPage(condition, pageSize, pageNo);
	}

	public BzCustomer getCustomer(Long id) {
		return customerDao.findByPK(id);
	}

	public void updateCustomer(BzCustomer customer) {
		BzCustomer d = customerDao.findByPK(customer.getId());
		try {
			BeanUtils.copyProperties(d, customer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteCustomer(Long id) {
		customerDao.deleteByPK(id);
	}

	public void recordCallIn(String callingNum, String calledNum,
			Long currentTime,Long duration) {
		BzCallRecord bzCallRecord = new BzCallRecord();
		bzCallRecord.setCalledNum(calledNum);
		bzCallRecord.setCallingNum(callingNum);
		bzCallRecord.setCallingTime(currentTime);
		bzCallRecord.setDuration(duration);
		bzCallRecord.setCreateDate(new Date());
		callRecordDao.save(bzCallRecord);

		BzCustomer customer = customerDao.getCustomerByPhone(callingNum);
		if (customer == null) {
			customer = new BzCustomer();
			customer.setBindPhone(callingNum);
			customer.setCreateDate(new Date());
			customerDao.save(customer);
		}
	}

	public void checkAndAddCustomer(String phoneNo) {
		BzCustomer customer = customerDao.getCustomerByPhone(phoneNo);
		if (customer == null) {
			customer = new BzCustomer();
			customer.setBindPhone(phoneNo);
			customer.setCreateDate(new Date());
			customerDao.save(customer);
		}
	}

	public Pageable<BzCustomerBinder> findServiceRecordByCondition(
			Condition condition, Pageable<BzCustomerBinder> page) {
		int pageSize = page.getPageSize();
		int pageNo = page.getPageNo();
		return customerBinderDao.findCustomerBinderByPage(condition, pageSize,
				pageNo);
	}

	public void saveCallRecords(List<BzCallRecord> callRecords) {

		for (BzCallRecord bzCallRecord : callRecords) {
			bzCallRecord.setCreateDate(new Date());
			callRecordDao.save(bzCallRecord);

			switch (bzCallRecord.getCallType()) {
			case CALLIN:
				checkAndAddCustomer(bzCallRecord.getCallingNum());
				break;

			case CALLOUT:
				checkAndAddCustomer(bzCallRecord.getCalledNum());
				break;
			}
		}
	}

	public List<BzDataTree> findDataTreeAll(DataType dataType) {
		return dataTreeDao.findAllByDataType(dataType);
	}
	
	public BzDataTree addCatalog(String dataName,Long pid) {
		BzDataTree dataTree= new BzDataTree();
			dataTree.setDataName(dataName);
			dataTree.setDataType(DataType.CATALOG);
			dataTree.setPid(pid);
			dataTree.setNodeType(NodeType.NODE);
			dataTree.setCreateDate(new Date());
		dataTreeDao.save(dataTree);
		return dataTree;
	}

	public void deleteCatalog(Long id) {
		dataTreeDao.deleteByPK(id);
	}

	public void updateCataglog(Long id, String dataName) {
		BzDataTree dataTree = dataTreeDao.findByPK(id);
		dataTree.setDataName(dataName);
	}

	public void addPoster(BzPoster poster) {
		posterDao.save(poster);
	}

	public List<BzCustomerBinder> findCustomerBinders(String targetCode,
			TargetType targetType, Date startDate, Date endDate) {
		List<BzCustomerBinder> data = customerBinderDao.findcuBinders(targetCode, targetType, startDate, endDate);
		if(data!=null&&!data.isEmpty()){
			for (BzCustomerBinder bzCustomerBinder : data) {
				bzCustomerBinder.setPosters(posterDao.findBzPosters(bzCustomerBinder.getId()));
			}
		}
		return customerBinderDao.findcuBinders(targetCode, targetType, startDate, endDate);
	}

	public List<BzCustomerBinder> findCustomerBindersCustomer(String phoneNo,
			Date startDate, Date endDate) {
		return customerBinderDao.findcustomerBinders(phoneNo, startDate, endDate);
	}

	public List<BzPoster> findBzPosters(Long ownerId) {
		
		return posterDao.findBzPosters(ownerId);
	}

	public void updateCustomerBinder(BzCustomerBinder customerBinder) {
		
		BzCustomerBinder binder = customerBinderDao.findByPK(customerBinder.getId());
		binder.setTargetName(customerBinder.getTargetName());
		binder.setServiceInfo(customerBinder.getServiceInfo());
	}

	public List<BzDataTree> findDataTreeAll(DataType dataType, NodeType nodeType) {
		return dataTreeDao.findAllByDataType(dataType, nodeType);
	}

	public List<BzServiceprovider> findProvidersByCondition(Condition condition) {
		return serviceProviderDao.findProviderAll(condition);
	}

	public void updatePosters(List<BzPoster> posters) {
		for (BzPoster bzPoster : posters) {
			posterDao.saveOrUpdate(bzPoster);
		}
	}

	public List<BzPoster> findBzPosters(Long ownerId, TargetType ownerType) {
		return posterDao.findBzPosters(ownerId, ownerType);
	}

	public void deletePosters(List<Long> posterIds) {
		for (Long posterId : posterIds) {
			posterDao.deleteByPK(posterId);
		}
	}

	public List<BzPoster> findBzPosters(List<Long> posterIds) {
		return posterDao.findBzPosters(posterIds);
	}

}
