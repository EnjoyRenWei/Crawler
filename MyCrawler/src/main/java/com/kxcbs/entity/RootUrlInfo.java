package com.kxcbs.entity;

import java.util.Date;

public class RootUrlInfo {
	private String rootUrlId;
	private String url;
	private Date crawl_time;
	public String getRootUrlId() {
		return rootUrlId;
	}
	public void setRootUrlId(String rootUrlId) {
		this.rootUrlId = rootUrlId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCrawl_time() {
		return crawl_time;
	}
	public void setCrawl_time(Date crawl_time) {
		this.crawl_time = crawl_time;
	}
	
}
