package com.learning.collection;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockQueueDemo {

    @Test
    public void test() {
        LinkedBlockingQueue<String> lq = new LinkedBlockingQueue<>();
        System.out.println(lq.peek());
        System.out.println(lq.poll());
        System.out.println(lq.remove());
        try {
            System.out.println(lq.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lq.add("1");
        lq.offer("2");
        try {
            lq.put("3");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
