package com.visfull.bz.service;

import java.util.Date;
import java.util.List;

import com.visfull.bz.domain.BzArea;
import com.visfull.bz.domain.BzBlackWhite;
import com.visfull.bz.domain.BzCallRecord;
import com.visfull.bz.domain.BzCommunity;
import com.visfull.bz.domain.BzCustomer;
import com.visfull.bz.domain.BzCustomerBinder;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzPositionInfo;
import com.visfull.bz.domain.City;
import com.visfull.bz.domain.County;
import com.visfull.bz.domain.Province;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;
import com.visfull.bz.domain.BzOperator;
import com.visfull.bz.domain.BzPoster;
import com.visfull.bz.domain.BzServer;
import com.visfull.bz.domain.BzServiceprovider;
import com.visfull.bz.domain.BzSignIn;
import com.visfull.bz.emnu.TargetType;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface BzInfoService {
	
	public void  addOperator(BzOperator operator);
	
	public void  deleteOperator(Long id);
	
	public BzOperator getBzOperator(Long id);
	
	public void  updateOperator(BzOperator operator);
	
	public Pageable<BzOperator> findOperatorsByCondition(Condition condition,Pageable<BzOperator> page);
	
	public BzOperator getOperatorByCode(String opCode);
	
	public BzServer addServer(BzServer server);
	
	public void deleteServer(Long id);
	
	public void updateServer(BzServer server);
	
	public Pageable<BzServer> findServersByCondition(Condition condition,Pageable<BzServer> page);
	
	public BzServer getBzServer(Long id);
	
	public BzServer getBzServerByCode(String serverCode);
	
	public List<BzServer> findServers(long spId,long id);
	
	public Pageable<BzBlackWhite> findBlackWhiteByPage(Condition condition,Pageable<BzBlackWhite> page);
	
	public List<BzBlackWhite> findBlackWhites(Condition condition);
	
	public void addBlackWhite(BzBlackWhite blackWhite) throws Exception;
	
	public List<BzBlackWhite> findBlackWhiteAll();
	
	public void deleteBlackWhite(Long id);
	
	public BzBlackWhite getBlackWhite(Long id);
	
	public void updateBlackWhite(BzBlackWhite blackWhite);
	
	
	public void  addServiceProvider(BzServiceprovider serviceprovider);
	
	public void  deleteServiceProvider(Long id);
	
	public BzServiceprovider getServiceprovider(Long id);
	
	public BzServiceprovider getServiceproviderByCode(String spCode);
	
	public void  updateServiceProvider(BzServiceprovider serviceprovider);
	
	public Pageable<BzServiceprovider> findProvidersByCondition(Condition condition,Pageable<BzServiceprovider> page);
	
	public List<BzServiceprovider> findProvidersByCondition(Condition condition);
	
	public List<BzServiceprovider> findServiceproviders(long pId,long spId);
	
	
	public void addSignIn(BzSignIn signIn);
	
	public void addCustomer(BzCustomer customer);
	
	public Pageable<BzCustomer> findCustomerByCondition(Condition condition,Pageable<BzCustomer> page);
	
	public BzCustomer getCustomerByPhone(String phone);
	
	public void updateCustomer(BzCustomer customer);
	
	public void deleteCustomer(Long id);
	
	public BzCustomer getCustomer(Long id);
	
	public void addCustomerBinders(List<BzCustomerBinder> customerBinders);
	
	public void addCustomerBinder(BzCustomerBinder customerBinder);
	
	
	public void recordCallIn(String callingNum,String calledNum,Long currentTime,Long duration);
	
	public void checkAndAddCustomer(String phoneNo);
	
	public Pageable<BzCustomerBinder> findServiceRecordByCondition(Condition condition,Pageable<BzCustomerBinder> page);
	
	
	
	public void saveCallRecords(List<BzCallRecord> callRecords);
	
	public void addTreeNode(BzDataTree dataTree);
	
	public void updateTreeNode(BzDataTree dataTree);
	
	public BzDataTree getBzDataTree(Long id);
	
	public List<BzDataTree> findDataTreeAll(DataType dataType);
	
	public List<BzDataTree> findDataTreeAll(DataType dataType,NodeType nodeType);
	
	public BzDataTree addCatalog(String dataName,Long pid);
	
	public void deleteCatalog(Long id);
	
	public void updateCataglog(Long id,String dataName);
	
	public void addPoster(BzPoster poster);
	
	public List<BzCustomerBinder> findCustomerBinders(String targetCode,TargetType targetType,Date startDate,Date endDate);
	
	public List<BzCustomerBinder> findCustomerBindersCustomer(String phoneNo,Date startDate,Date endDate);
	
	public List<BzPoster> findBzPosters(Long ownerId);
	
	public List<BzPoster> findBzPosters(Long ownerId,TargetType ownerType);
	
	public void updatePosters(List<BzPoster> posters);
	
	public void updateCustomerBinder(BzCustomerBinder customerBinder);
	
	public void deletePosters(List<Long> posterIds);
	
	public List<BzPoster> findBzPosters(List<Long> posterIds);
	
	public List<Province> findProvincesAll();
	
	public List<City> findCityList(Integer provinceId);
	
	public List<County> findCountyList(Integer cityId);
	
	public List<BzArea> findAreasAll();
	
	public void addArea(BzArea area);
	
	public void deleteArea(Integer id);
	
	public void updateArea(BzArea area);
	
	public Pageable<BzArea> findAreaPageable(Condition condition,Pageable<BzArea> page);
	
	public void addCommunity(BzCommunity community);
	
	public void deleteCommunity(Integer id);
	
	public void updateCommunity(BzCommunity community);
	
	public Pageable<BzCommunity> findCommunityPageable(Condition condition,Pageable<BzCommunity> page);
	
	public BzCustomerBinder findCustomerBinder(String targetCode,String customerPhone);
	
	public List<BzArea> findAreaList(Integer countyId);
	public List<BzCommunity> findCommunities(Integer areaId);
	
	public void addPositionInfo(BzPositionInfo positionInfo);
	
	
}
