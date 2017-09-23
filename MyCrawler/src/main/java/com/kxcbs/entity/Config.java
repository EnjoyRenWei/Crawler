package com.kxcbs.entity;

import org.springframework.stereotype.Component;

@Component
public class Config {
	/**
	 * 存储路径
	 */
	private String storePath;
	/**
	 * url要包含的字符
	 */
	private String containStr;
	/**
	 * 保存html内容的文件后缀名
	 */
	private String suffix;
	private long rootUrlId;
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	public String getContainStr() {
		return containStr;
	}
	public void setContainStr(String containStr) {
		this.containStr = containStr;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public long getRootUrlId() {
		return rootUrlId;
	}
	public void setRootUrlId(long rootUrlId) {
		this.rootUrlId = rootUrlId;
	}
	
	
}
