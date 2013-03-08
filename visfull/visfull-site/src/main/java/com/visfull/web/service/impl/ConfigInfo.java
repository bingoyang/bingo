package com.visfull.web.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigInfo {
	
	@Value("#{settings['cfg.upload.path']}")
	private String uploadPath ;
	
	@Value("#{settings['cfg.host.prefix']}")
	private String hostPrefix ;
	
	@Value("#{settings['thumb.height']}")
	private int height;
	
	@Value("#{settings['thumb.width']}")
	private int width;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getHostPrefix() {
		return hostPrefix;
	}

	public void setHostPrefix(String hostPrefix) {
		this.hostPrefix = hostPrefix;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
