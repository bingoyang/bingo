package com.visfull.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visfull.system.dao.AuthSessionDao;
import com.visfull.system.domain.AuthSession;
import com.visfull.system.service.SessionService;

@Service
public class SesionServiceImpl implements SessionService {
	
	@Autowired
	private AuthSessionDao authSessionDao;

	public void saveSession(AuthSession session) {
		authSessionDao.save(session);
	}

	public AuthSession getSession(String sessionId) {
		return authSessionDao.findByPK(sessionId);
	}

	public void updateSession(AuthSession session) {
		AuthSession d = authSessionDao.findByPK(session.getId());
		d.setData(session.getData());
	}
}
