package com.tydic.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tydic.dao.TestDao;
import com.tydic.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Resource
	private TestDao testDao;

	public String test() {
		String result = testDao.test();
		return result;
	}

}
