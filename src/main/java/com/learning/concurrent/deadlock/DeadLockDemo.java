package com.learning.concurrent.deadlock;

public class DeadLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getId() + "获得锁1");

                    synchronized (lock2) {
                        System.out.println(Thread.currentThread().getId() + "获得锁2");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getId() + "获得锁2");

                    synchronized (lock1) {
                        System.out.println(Thread.currentThread().getId() + "获得锁1");
                    }
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
