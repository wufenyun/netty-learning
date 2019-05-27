package com.learning.collection;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapDemo {

    @Test
    public void test() {
        TreeMap<Integer,Integer> tree = new TreeMap<>();
        tree.put(5,5);
        tree.put(2,2);
        tree.put(3,3);
        tree.put(6,6);

        SortedMap head = tree.headMap(3);
        head.keySet().forEach(k->{
            System.out.println(k);
        });

        SortedMap tail = tree.tailMap(3);
        tail.keySet().forEach(k->{
            System.out.println(k);
        });

        System.out.println(tree.firstKey());
    }
}
