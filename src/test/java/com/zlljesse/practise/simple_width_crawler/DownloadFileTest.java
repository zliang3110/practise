package com.zlljesse.practise.simple_width_crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zliang3110 on 2016/4/7.
 */
public class DownloadFileTest {
    @Test
    public void getFileNameTest(){
        String url = "http://www.zliang3110.com/index?user=1";
        String contentType = "text/html";
        DownloadFile downloadFile = new DownloadFile();
        String filename = downloadFile.getFileName(url,contentType);
        Assert.assertEquals("www.zliang3110.com/index.html",filename);
    }
    @Test
    public void downloanFileTest(){
        String url = "http://www.baidu.com";
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.downloadFile(url);
    }
}
