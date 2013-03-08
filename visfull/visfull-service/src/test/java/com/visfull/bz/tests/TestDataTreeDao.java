package com.visfull.bz.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.visfull.bz.dao.DataTreeDao;
import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;

@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class TestDataTreeDao  extends AbstractJUnit4SpringContextTests{
	
	@Autowired
	private DataTreeDao dataTreeDao;
	
	@Test
	public void saveDataTreeTest(){
		BzDataTree dataTree = new BzDataTree();
		dataTree.setDataName("分类1");
		dataTree.setDataType(DataType.CATALOG);
		dataTree.setNodeType(NodeType.ROOT);
		dataTree.setPid(0L);
		
		dataTreeDao.save(dataTree);
	}

}
