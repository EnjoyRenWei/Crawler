package com.kxcbs.config;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.http.HttpStatus;

import com.kxcbs.dao.CrawlerDao;
import com.kxcbs.entity.Config;
import com.kxcbs.entity.UrlInfo;
import com.kxcbs.exceptions.CrawlException;
import com.kxcbs.utils.GenerateShortUrl;
import com.kxcbs.utils.SpringContextUtil;
import com.kxcbs.utils.StringUtils;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 处理html
 * 
 * @author renwei
 *
 */
//@Component
//@Transactional
public class CrawlerConfig extends WebCrawler {
//	private CrawlerDao crawlerDao = (CrawlerDao) SpringContextUtil
//			.getBean(CrawlerDao.class);
	private CrawlerDao crawlerDao = SpringContextUtil.getBean(CrawlerDao.class);
	private Config con = SpringContextUtil.getBean(Config.class);
	/*@Value("${html.store.path}")
	private String crawlStorageFolder;*/
	// 后缀名
	//private String suffix = con.getSuffix();
	private String suffix;
	//private String store_path = con.getStorePath();
	private String store_path;
	//private String containtStr = con.getContainStr();
	private String containStr;
	//private long rootUrlId = con.getRootUrlId();
	private long rootUrlId;
	
	UrlInfo ui = new UrlInfo();

	private final Pattern FILTERS = Pattern
			.compile(".*\\.(bmp|gif|jpe?g|png|tiff?|pdf|ico|xaml|pict|rif|pptx?|ps"
					+ "|mid|mp2|mp3|mp4|wav|wma|au|aiff|flac|ogg|3gp|aac|amr|au|vox"
					+ "|avi|mov|mpe?g|ra?m|m4v|smil|wm?v|swf|aaf|asf|flv|mkv"
					+ "|zip|rar|gz|7z|aac|ace|alz|apk|arc|arj|dmg|jar|lzip|lha|js|css)"
					+ "(\\?.*)?$");
	private final Pattern CONTERS = Pattern.compile(".*\\.(htm|html)"
			+ "(\\?.*)?$");

	
	@SuppressWarnings("unchecked")
	@Override
	public void onStart() {
		Map<String,Object> map = (Map<String, Object>) myController.getCustomData();
		containStr = (String) map.get("containStr");
		System.err.println(containStr);
		store_path = (String) map.get("storePath");
		System.err.println(store_path);
		suffix = (String) map.get("suffix");
		System.err.println(suffix);
		rootUrlId = (long) map.get("root_url_id");
		System.err.println(rootUrlId);
	}


	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		//System.out.println("containtStr:"+containStr);
		//System.out.println("store_path:"+store_path);
		//System.out.println("suffix:"+suffix);
		String href = url.getURL().toLowerCase();
		// return !FILTERS.matcher(href).matches()
		// && href.startsWith("http://www.scu.edu.cn/");
		return CONTERS.matcher(href).matches() && href.contains(containStr);
	}

	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		//获取短url
		String short_url = GenerateShortUrl.shortUrl(url);
		String parent_short_url=null;
		if(!StringUtils.isEmpty(page.getWebURL().getParentUrl()))
			parent_short_url=GenerateShortUrl.shortUrl(page.getWebURL().getParentUrl());
		
		System.err.println("URL: " + url);
		System.err.println("short URL: " + short_url);
		String fileName = "";
		try {
			if (StringUtils.isEmpty(store_path))
				store_path = "F:/htmls";
			//用MD5值的第一个数做第一层目录，第二个数第二层目录
			String path=store_path+"/"+short_url.substring(0,1)+"/"+short_url.substring(1,2);
			
			File file = new File(path);
			if(!file.exists())
				file.mkdirs();
			if(StringUtils.isEmpty(suffix))
				suffix=".html";
			fileName = short_url + suffix;
			String file_path = path+ "/" + fileName;
			

			if (page.getParseData() instanceof HtmlParseData) {
				HtmlParseData htmlParseData = (HtmlParseData) page
						.getParseData();
				String html = htmlParseData.getHtml();
				// Set<WebURL> links = htmlParseData.getOutgoingUrls();
				// 将html内容写入文件
				FileWriter bw=new FileWriter(file_path,true);
				bw.write(html);
				//FileOutputStream fos = new FileOutputStream(store_path);
				//OutputStreamWriter oStreamWriter = new OutputStreamWriter(fos, "GBK");
				//oStreamWriter.append(html);
				//oStreamWriter.close();
				bw.close();
				
				ui.setUrl(url);
				ui.setShort_url(short_url);
				ui.setStore_path(path);
				ui.setParent_short_url(parent_short_url);
				ui.setStatus(200);
				ui.setRoot_url_id(rootUrlId);
				
				crawlerDao.addHtmlInfo(ui);
			}
		} catch (CrawlException e) {
			throw new CrawlException(e.getMessage(), e);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			throw new CrawlException("插入爬取html信息到数据库异常");
		}
	}

	@Override
	protected void handlePageStatusCode(WebURL webUrl, int statusCode,
			String statusDescription) {
		
		String url = webUrl.getURL();
		//获取短url
		String short_url = GenerateShortUrl.shortUrl(url);
		String parent_short_url=null;
		if(!StringUtils.isEmpty(webUrl.getParentUrl()))
			parent_short_url=GenerateShortUrl.shortUrl(webUrl.getParentUrl());
		if (statusCode != HttpStatus.SC_OK) {
			ui.setUrl(url);
			ui.setShort_url(short_url);
			ui.setParent_short_url(parent_short_url);
			ui.setStore_path("");
//			if (StringUtils.isEmpty(store_path))
//				store_path = "F:/htmls";
//			
//			String fileName = short_url + suffix;
//			String path = store_path+ "/" + fileName;
//			ui.setStore_path(path);
			
			if (statusCode == HttpStatus.SC_NOT_FOUND) {
				ui.setStatus(404);
				System.out.println("======================>");
			} else {
				ui.setStatus(403);
			}
			
			crawlerDao.addHtmlInfo(ui);
		}
	}
	

}
