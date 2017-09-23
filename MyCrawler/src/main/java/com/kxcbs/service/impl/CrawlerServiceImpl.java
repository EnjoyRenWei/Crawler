package com.kxcbs.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kxcbs.config.CrawlerConfig;
import com.kxcbs.dao.CrawlerDao;
import com.kxcbs.entity.RootUrlInfo;
import com.kxcbs.exceptions.CrawlException;
import com.kxcbs.service.CrawlerService;
import com.kxcbs.utils.StringUtils;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@Service
public class CrawlerServiceImpl implements CrawlerService {
	@Autowired
	private CrawlerDao crawlerDao;
	@Value("${html.store.path}")
	private String crawlStorageFolder;
	//@Autowired 
	//private Config con;
	
	private Map<String,Object> map = new HashMap<String,Object>();

	@Override
	public String crawlHtmls(String base_domain,String containStr,String store_path,String suffix) {
		try {
			// 判断要爬取的域名是否为空
			if (StringUtils.isEmpty(base_domain))
				throw new CrawlException("要爬取的域名为空！");
			
			// 判断存储html的文件是否存在
			File file = new File(crawlStorageFolder);
			if (!file.exists())
				file.mkdirs();

			int numberOfCrawlers = 5;

			CrawlConfig config = new CrawlConfig();
			// 爬取数据存放目录
			config.setCrawlStorageFolder(crawlStorageFolder + "/crawler");
			// 爬多少层，如入口A是1，A中有B，B中有C，那B是2，C中有D，C是3
			config.setMaxDepthOfCrawling(-1);
			// 最多爬取多少个网页
			config.setMaxPagesToFetch(-1);
			//如果意外终止掉了,配置恢复崩溃的爬虫
			//config.setResumableCrawling(true);

			// config.setShutdownOnEmptyQueue(false);
			// 同一个主机两个请求之间的延迟毫秒数，默认是200
			config.setPolitenessDelay(3000);

			PageFetcher pageFetcher = new PageFetcher(config);

			RobotstxtConfig robotstxtConfig = new RobotstxtConfig();

			RobotstxtServer robotstxtServer = new RobotstxtServer(
					robotstxtConfig, pageFetcher);

			CrawlController controller = new CrawlController(config,
					pageFetcher, robotstxtServer);

			controller.addSeed(base_domain);
			// controller.addSeed("http://www.scu.edu.cn/");
			// controller.addSeed("http://rsc.scu.edu.cn/rsc/");
			// controller.addSeed("http://news.scu.edu.cn/news2012/");
			// controller.addSeed("http://www.scu.edu.cn/portal2013/");
			
			RootUrlInfo rui = new RootUrlInfo();
			rui.setUrl(base_domain);
			//插入根域名信息，并返回主键id
			//long root_url_id = crawlerDao.addRootUrlInfo(rui);
			//查询根域名主键id
			Long root_url_id = crawlerDao.queryRootUrlId(base_domain);
			if(StringUtils.isEmpty(String.valueOf(root_url_id)))
				throw new CrawlException("没有此根域名信息");
			System.err.println("------------------>root_url_id<----------------"+root_url_id);
			
			//配置需要包含字符，存储路径
			if(StringUtils.isEmpty(containStr))
				throw new CrawlException("containStr不能为空!");
//			con.setContainStr(containStr);
			if(StringUtils.isEmpty(store_path))
				store_path = crawlStorageFolder+"/"+containStr.replace(".", "_");
//			con.setStorePath(store_path);
//			if(StringUtils.isEmpty(suffix))
//				suffix=".txt";
//			con.setSuffix(suffix);
//			con.setRootUrlId(root_url_id);
			map.put("containStr", containStr);
			map.put("storePath", store_path);
			map.put("suffix", suffix);
			map.put("root_url_id", root_url_id);
			controller.setCustomData(map);
			//开始爬取
			controller.start(CrawlerConfig.class, numberOfCrawlers);

			controller.waitUntilFinish();
			System.out
					.println("----------------------------->controller已经执行完毕！");
		} catch (CrawlException e) {
			e.printStackTrace();
			throw new CrawlException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new CrawlException("爬取失败！");
		}

		return "1111111111";
	}
}
