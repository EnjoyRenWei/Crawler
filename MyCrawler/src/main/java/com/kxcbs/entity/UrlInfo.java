package com.kxcbs.entity;

import java.util.Date;

public class UrlInfo {
	/**
	 * 主键id
	 */
	private long url_id;
	/**
	 * 父级url id
	 */
	private long parent_url_id;
	/**
	 * 原url地址
	 */
	private String url;
	/**
	 * 短地址
	 */
	private String short_url;
	/**
	 * 父级短地址
	 */
	private String parent_short_url;
	/**
	 * html内容存储路径
	 */
	private String store_path;
	/**
	 * 状态:200成功,404未找到
	 */
	private int status;
	/**
	 * 根域名id
	 */
	private long root_url_id;
	/**
	 * 更新时间
	 */
	private Date update_date;
	public long getUrl_id() {
		return url_id;
	}
	public void setUrl_id(long url_id) {
		this.url_id = url_id;
	}
	public long getParent_url_id() {
		return parent_url_id;
	}
	public void setParent_url_id(long parent_url_id) {
		this.parent_url_id = parent_url_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShort_url() {
		return short_url;
	}
	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}
	public String getParent_short_url() {
		return parent_short_url;
	}
	public void setParent_short_url(String parent_short_url) {
		this.parent_short_url = parent_short_url;
	}
	public String getStore_path() {
		return store_path;
	}
	public void setStore_path(String store_path) {
		this.store_path = store_path;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getRoot_url_id() {
		return root_url_id;
	}
	public void setRoot_url_id(long root_url_id) {
		this.root_url_id = root_url_id;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	

}
