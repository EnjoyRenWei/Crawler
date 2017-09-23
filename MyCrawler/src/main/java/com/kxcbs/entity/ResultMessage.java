package com.kxcbs.entity;

public class ResultMessage {
	// 状态码
	private String code = "";
	// 返回信息
	private String message = "";
	// 返回数据
	private Object data = "";

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
