package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzDataTree;
import com.visfull.bz.domain.BzDataTree.DataType;
import com.visfull.bz.domain.BzDataTree.NodeType;

public interface DataTreeDao extends HibernateBaseDao<BzDataTree, Long> {
	
	List<BzDataTree> findAllByDataType(DataType dataType);
	
	List<BzDataTree> findAllByDataType(DataType dataType,NodeType nodeType);
}
