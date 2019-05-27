package com.learning.datastructure.linkedlist;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by Administrator on 2019/3/29.
 */
public class LinkedLists<E> {

    private Node<E> head;

    //1->2->3->4  2->1->3->4  3->2->1->4
    //4->3->2->1
    //单链表翻转
    public void reverse() {
        Node currentNode = head.next;
        Node nextNode;
        Node tempHead = head;
        tempHead.next = null;

        while(currentNode != null) {
            nextNode = currentNode.next;
            currentNode.next = tempHead;
            tempHead = currentNode;
            currentNode = nextNode;
        }
        head = tempHead;
    }

    public void add(E e) {
        Node<E> node = new Node<>(e);
        if(null == head) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    public void print() {
        Node current = head;
        while (current!=null) {
            System.out.println(current.value);
            current = current.next;
        }
    }

    private static class Node<E> {
        private Node<E> next;
        private E value;

        public Node(E e) {
            this.value = e;
        }
    }

    public static void main(String[] args) {
        int a= 1048576;
        System.out.println((a/6026677f));
        /*LinkedLists linkedLists = new LinkedLists();
        linkedLists.add(1);
        linkedLists.add(2);
        linkedLists.add(3);
        linkedLists.print();
        linkedLists.reverse();
        linkedLists.print();*/
    }
}
