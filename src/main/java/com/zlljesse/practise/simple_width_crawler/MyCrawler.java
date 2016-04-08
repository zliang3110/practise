package com.zlljesse.practise.simple_width_crawler;

import java.util.Set;

/**
 * Created by Administrator on 2016/4/8.
 */
public class MyCrawler {
    /**
     * 使用种子初始化url队列
     * @param seeds
     */
    private void initCrawlerWithSeeds(String... seeds){
        for (int i = 0; i < seeds.length; i++) {
            LinkQueue.addUnVisitedUrl(seeds[i]);
        }
    }

    /**
     * 抓取过程
     * @param seads
     */
    public void crawling(String... seads){
        //定义过滤器，提取以http://www.baidu.com开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if(url.startsWith("http://www.baidu.com")){
                    return true;
                }else {
                    return false;
                }
            }
        };

        //初始化队列
        initCrawlerWithSeeds(seads);

        //待抓取的链接不空且抓取连接数<=100
        while (!LinkQueue.unVisitedUrlEmpty()&&LinkQueue.getVisitedUrlNum()<=100){
            //对头url出队
            String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
            //下载网页
            DownloadFile downloadFile = new DownloadFile();
            downloadFile.downloadFile(visitUrl);
            //将改url放入已处理的集合中
            LinkQueue.addVisitedUrl(visitUrl);
            //提取下载网页中的url
            Set<String> links = HtmlParserTool.extractLinks(visitUrl,filter);
            for (String link : links){
                LinkQueue.addUnVisitedUrl(link);
            }
        }
    }

    /**
     * 爬虫入口
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(">>>>>开始运行>>>>>");
        MyCrawler crawler = new MyCrawler();
        crawler.crawling("http://www.baidu.com");
        System.out.println(">>>>>运行结束>>>>>");
    }
}
