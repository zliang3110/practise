package com.zlljesse.practise.simple_width_crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zliang on 2016/4/7.
 */
public class LinkQueueTest {
    @Test
    public void addUnVisitedUrlTest(){
        String str = "123";
        LinkQueue.addUnVisitedUrl("123");
        LinkQueue.addUnVisitedUrl("123");
        LinkQueue.addUnVisitedUrl(str);
        Assert.assertEquals(1,LinkQueue.getUnVisitedUrl().getQueueSize());
    }
}
