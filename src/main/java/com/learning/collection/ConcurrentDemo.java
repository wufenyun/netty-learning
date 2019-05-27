package com.learning.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * Created by Administrator on 2018/5/21.
 */
public class ConcurrentDemo {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>();
        map.put("aa",null);
        map.put(null,1);
        System.out.println(map.size());
    }
}
