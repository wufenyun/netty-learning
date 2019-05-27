package com.learning.concurrent.threadpool;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @description: demo
 * @author: wufenyun
 * @date: 2018-05-22 17
 **/
public class ThreadpoolDemo {

    @Test
    public void fixedpool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,1,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());

        while(true) {
            printPoolMsg(threadPoolExecutor);
            sleepSecond(1);
        }
    }

    @Test
    public void cachepool() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,5,1,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1));

        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());

        while(true) {
            printPoolMsg(threadPoolExecutor);
            sleepSecond(1);
        }
    }

    @Test
    public void cachepoolSynQueue() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,3,1,TimeUnit.SECONDS,
                new SynchronousQueue<>());

        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());

        while(true) {
            printPoolMsg(threadPoolExecutor);
            sleepSecond(1);
        }
    }

    @Test
    public void threadException() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 1, TimeUnit.SECONDS,
                new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"mythread-"+ RandomUtils.nextLong());
            }
        });

        threadPoolExecutor.execute(new MyTaskThrowException());
        threadPoolExecutor.execute(new MyTaskThrowException());
        sleepSecond(3);
        threadPoolExecutor.execute(new MyTask());
        threadPoolExecutor.execute(new MyTask());

        while(true) {
            printPoolMsg(threadPoolExecutor);
            sleepSecond(1);
        }
    }

    private void printPoolMsg(ThreadPoolExecutor threadPoolExecutor) {
        System.out.print("activeCount:" + threadPoolExecutor.getActiveCount() + "  ");
        System.out.print("getCompletedTaskCount:" + threadPoolExecutor.getCompletedTaskCount() + "  ");
        System.out.print("getQueuesize:" + threadPoolExecutor.getQueue().size() + "  ");
        System.out.println("getPoolSize:" + threadPoolExecutor.getPoolSize() + "  ");
    }

    private final class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "1 is running");
            sleepSecond(2);
            System.out.println(Thread.currentThread().getName() + "complete");
        }
    }

    private final class MyTaskThrowException implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "1 is running");
            sleepSecond(2);
            System.out.println(Thread.currentThread().getName() + "complete");
            throw new RuntimeException();
        }
    }

    private void await() {
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleepSecond(int time) {
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {
        ExecutorService executor = Executors.newFixedThreadPool(1, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"pool thread");
            }
        });

        Future<String> future = executor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return null;
            }
        });

        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "1 is running");
            }
        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "2 is running");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}