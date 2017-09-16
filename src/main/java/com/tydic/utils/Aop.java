package com.tydic.utils;

import java.util.Date;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.tydic.custom.annotations.GetPerforms;


@Aspect
@Component
public class Aop {
	private final Logger logger = LoggerFactory
			.getLogger(Aop.class);
	public Date start ;
	@Value("${perform.judge.time}")
	public long setTime;
	Date end;
	@Pointcut("@annotation(com.tydic.custom.annotations.GetPerforms)")
	public void getPerforms(){
		
	}
	@Before("getPerforms()")
	public void checkStart(){
		start = new Date();
	}
	@After("getPerforms()")
	public void checkEnd(){
		String msg = "";
		end = new Date();
		long time = end.getTime()-start.getTime();
		msg=setTime>time?"性能较好":"性能较差";
		logger.info("方法耗时: "+time+" ms ,"+msg);
		
	}
}
