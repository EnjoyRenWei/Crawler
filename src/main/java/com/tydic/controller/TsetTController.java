package com.tydic.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tydic.custom.annotations.GetPerforms;
import com.tydic.entity.ResultMessage;
import com.tydic.service.TestService;
import com.tydic.utils.ReturnResult;

@RestController
@RequestMapping("/api")
public class TsetTController {
	@Resource
	private TestService testService;

	@GetPerforms
	@RequestMapping("/test")
	public ResultMessage test(GetPerforms myAnnotation) {
		ResultMessage result = null;
		String str = testService.test(); 
		result = ReturnResult.getResult("1", "查询成功", str);
		return result;
	}
}
