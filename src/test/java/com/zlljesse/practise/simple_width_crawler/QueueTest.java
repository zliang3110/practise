package com.zlljesse.practise.simple_width_crawler;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/4/7.
 */
public class QueueTest {
    @Test
    public void enQueueTest(){
        Queue queue = new Queue();
        for (int i=0;i<10;i++){
            queue.enQueue(i);
        }
        for (int i = 0; i < 10; i++) {
            int element = (Integer) queue.deQueue();
            Assert.assertEquals(i,element);
        }

    }
}
