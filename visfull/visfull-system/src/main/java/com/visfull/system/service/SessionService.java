package com.visfull.system.service;

import com.visfull.system.domain.AuthSession;

public interface SessionService {
	
	public void saveSession(AuthSession session);
	
	public AuthSession getSession(String sessionId);
	
	public void updateSession(AuthSession session);
	
}
