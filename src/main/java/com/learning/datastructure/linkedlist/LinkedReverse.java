package com.learning.datastructure.linkedlist;

import org.junit.Test;

public class LinkedReverse {

    @Test
    public void demo() {
        SinglyLinkNode head = new SinglyLinkNode();
        SinglyLinkNode temp = head;
        for(int i=0;i<10;i++) {
            temp.setValue(i);
            temp.setNext(new SinglyLinkNode());
            temp = temp.getNext();
        }

        SinglyLinkNode prev = head;
        SinglyLinkNode next = prev.getNext();
        prev.setNext(null);
        SinglyLinkNode nn = next.getNext();
        while(next!=null && next.getNext()!=null) {
            next.setNext(prev);
            prev = next;
            next = nn;
            if(next==null) {
                break;
            }
            nn = next.getNext();
        }

        while(prev!=null && prev.getNext()!=null) {
            System.out.println(prev.getValue());
            prev = prev.getNext();
        }
    }

    public static class SinglyLinkNode {
        private int value;
        private SinglyLinkNode next;

        public SinglyLinkNode getNext() {
            return next;
        }

        public void setNext(SinglyLinkNode next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
