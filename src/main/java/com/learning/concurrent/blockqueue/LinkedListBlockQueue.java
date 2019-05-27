package com.learning.concurrent.blockqueue;

import java.util.LinkedList;

/**
 * Created by Administrator on 2019/3/29.
 */
public class LinkedListBlockQueue<E> implements BlockQueue<E> {

    private Object object = new Object();
    private LinkedList list = new LinkedList();
    private int size = 16;

    public LinkedListBlockQueue(int size) {
        this.size = size;
    }

    @Override
    public void put(E o) {
        try {
            synchronized (object) {
                if (list.size() >= size) {
                    object.wait();
                }
                list.add(o);
                object.notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E take() {
        try {
            synchronized (object) {
                if (list.size() < 1) {
                    object.wait();
                }
                E e = (E) list.removeLast();
                object.notify();
                return e;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        LinkedListBlockQueue<Integer> linkedListBlockQueue = new LinkedListBlockQueue(2);
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    linkedListBlockQueue.put(1);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(linkedListBlockQueue.take());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        producer.start();
        consumer.start();

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
