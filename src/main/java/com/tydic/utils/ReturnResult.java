package com.tydic.utils;

import com.tydic.entity.ResultMessage;

public class ReturnResult {
	/**
	 * 
	 * @Title: getResult
	 * @Description: 返回结果
	 * @date 2017-7-17 下午2:31:14
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static ResultMessage getResult(String code, String message,
			Object data) {
		ResultMessage rm = new ResultMessage();
		rm.setCode(code);
		rm.setMessage(message);
		rm.setData(data);
		return rm;
	}
}
