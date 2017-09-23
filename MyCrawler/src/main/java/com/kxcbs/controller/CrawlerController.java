package com.kxcbs.controller;


import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kxcbs.custom.annotations.GetPerforms;
import com.kxcbs.entity.ResultMessage;
import com.kxcbs.service.CrawlerService;
import com.kxcbs.utils.Constant;


@RestController
@RequestMapping("/api")
public class CrawlerController {
	@Resource
	private CrawlerService crawlerService;
	
	@GetPerforms
	@RequestMapping(value="/crawler",method=RequestMethod.GET)
	public ResultMessage crawlHtml(@RequestParam(name = "base_domain", required = false) String base_domain,
			@RequestParam(name = "containStr", required = false) String containStr,
			@RequestParam(name = "store_path", required = false) String store_path,
			@RequestParam(name = "suffix", required = false) String suffix) throws Exception{
		ResultMessage result = new ResultMessage();
		String data = null;
		String message = null;
		try {
			data = crawlerService.crawlHtmls(base_domain,containStr,store_path,suffix);
			result.setCode(Constant.SUCCESS_CODE);
			message="爬取成功";
				
		} catch (Exception e) {
			result.setCode(Constant.ERROR_CODE);
			message=e.getMessage();
		}
		result.setMessage(message);
		result.setData(data);
	    return result;
	}

}
