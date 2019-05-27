package com.learning.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @description: 日期工具类demo
 * @author: wufenyun
 * @date: 2018-06-08 11
 **/
public class DateDemo {

    @Test
    public void localDate() {
        LocalDate l1 = LocalDate.now();
        printDate(l1);
        LocalDate l2 = l1.plusWeeks(4);
        printDate(l2);
        print(l1.isAfter(l2));
        print(l2.toEpochDay());
        Period p = Period.between(l1,l2);
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());

        System.out.println("两天之间的差在天数   : " + ChronoUnit.DAYS.between(l1,l2));
    }

    @Test
    public void localTime() {
        LocalTime t1 = LocalTime.now();
        printTime(t1);
        print(t1.toSecondOfDay());
    }

    @Test
    public void dataTime() {
        LocalDateTime ldt = LocalDateTime.now();
        printDateTime(ldt);
        LocalDateTime ldt2 = ldt.plusYears(3);
        System.out.println("两天之间的差在DAYS   : " + ChronoUnit.DAYS.between(ldt,ldt2));
    }

    public void printDateTime(LocalDateTime ldt) {
        System.out.println(ldt.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public void printTime(LocalTime localTime) {
        System.out.println(localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    public void printDate(LocalDate localDate) {
        System.out.println(localDate.format(DateTimeFormatter.BASIC_ISO_DATE));
    }

    public void print(Object obj) {
        System.out.println(obj);
    }

    @Test
    public void time() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime.toString());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(df));

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString());
    }

    @Test
    public void jiaotiPrint() throws InterruptedException {
        Object object = new Object();
        boolean flag = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        try {
                            object.wait();
                            System.out.println("aaa");
                            object.notify();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"ta").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (object) {
                        try {
                            System.out.println("bbb");
                            object.notify();
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        },"tb").start();

        Thread.sleep(1000000);
    }

}