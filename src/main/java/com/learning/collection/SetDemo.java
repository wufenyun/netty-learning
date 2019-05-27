package com.learning.collection;

import org.junit.Test;

import java.util.*;

public class SetDemo {

    @Test
    public void demo() {
        List<String> arrayList = new ArrayList<String>();
        arrayList.add("s1");
        arrayList.add("s3");
        arrayList.add("s4");
        arrayList.add("s5");
        arrayList.add("s2");
        System.out.println("ArrayList:==========================");
        for(String arrList : arrayList){
            System.out.println(arrList);
        }
        List<String> linkedList = new LinkedList<String>();
        linkedList.add("s1");
        linkedList.add("s3");
        linkedList.add("s4");
        linkedList.add("s5");
        linkedList.add("s2");
        System.out.println("LinkedList:===========================");
        for(String linkList : linkedList){
            System.out.println(linkList);
        }

        Set<String> hashSet = new HashSet<String>();
        hashSet.add("s1");
        hashSet.add("s3");
        hashSet.add("s4");
        hashSet.add("s5");
        hashSet.add("s2");
        System.out.println("HashSet:==============================");
        for(String hashst: hashSet){
            System.out.println(hashst);
        }

        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.add("s1");
        linkedHashSet.add("s3");
        linkedHashSet.add("s4");
        linkedHashSet.add("s5");
        linkedHashSet.add("s2");
        System.out.println("LinkedHashSet:=========================");
        for(String linkedst : linkedHashSet){
            System.out.println(linkedst);
        }

        Set<String> treeSet = new TreeSet<String>();
        treeSet.add("s1");
        treeSet.add("s3");
        treeSet.add("s4");
        treeSet.add("s5");
        treeSet.add("s2");
        System.out.println("TreeSet:==============================");
        for(String treest : treeSet){
            System.out.println(treest);
        }

    }

    @Test
    public void set() {
        Set<String> set = new TreeSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        set.add("4");
        set.add("5");
        for (String str:set) {
            System.out.println(str);
        }
    }
}
