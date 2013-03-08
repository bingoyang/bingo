package com.visfull.bz.dao;

import java.util.List;

import com.visfull.bz.domain.BzPoster;
import com.visfull.bz.emnu.TargetType;

public interface PosterDao extends HibernateBaseDao<BzPoster,Long> {
	
	List<BzPoster> findBzPosters(Long id);
	
	List<BzPoster> findBzPosters(Long id,TargetType ownerType);
	
	List<BzPoster> findBzPosters(List<Long> posterIds);
	
}
