package com.visfull.bz.dao;

import com.visfull.bz.domain.BzSignIn;
import com.visfull.bz.vo.Condition;
import com.visfull.bz.vo.Pageable;

public interface SignInDao extends HibernateBaseDao<BzSignIn, Long> {
	
	public Pageable<BzSignIn> findSignInByPage(Condition condition,Integer pageSize,Integer pageNo);
}
