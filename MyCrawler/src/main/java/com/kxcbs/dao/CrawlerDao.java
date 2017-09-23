package com.kxcbs.dao;

import org.springframework.stereotype.Repository;

import com.kxcbs.entity.RootUrlInfo;
import com.kxcbs.entity.UrlInfo;

@Repository
public interface CrawlerDao {
	public String test();
	public void addHtmlInfo(UrlInfo ui);
	public long addRootUrlInfo(RootUrlInfo rui);
	public Long queryRootUrlId(String url);
}
