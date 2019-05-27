package com.learning.collection;

import org.junit.Test;

public class HashingDemo {

    @Test
    public void test() {
        System.out.println("11111aaaaaaaaaa".hashCode());
        System.out.println("21111aaaaaaaaaa".hashCode());
        System.out.println("aaaaa".hashCode());
        System.out.println("aaaaa".hashCode());
        System.out.println(new Long(1).hashCode());

    }
}
