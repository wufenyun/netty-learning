package com.learning.spi;

import sun.applet.AppletClassLoader;
import sun.reflect.Reflection;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ServiceLoader;

public class MyContextClassLoad {

    public static void testContextClassLoader() {

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext()) {
            Driver driver = (Driver) iterator.next();
            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());
        }
        System.out.println("current thread contextloader:" + Thread.currentThread().getContextClassLoader());

        System.out.println("current loader:" + MyContextClassLoad.class.getClassLoader());
        System.out.println("ServiceLoader loader:" + ServiceLoader.class.getClassLoader());
    }

    public static void main(String[] arg) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) System.out.println(Reflection.getCallerClass());

        try {
            Thread.currentThread().setContextClassLoader(new URLClassLoader(new URL[]{new URL("E:\\javaprogram\\jdk\\jdk1.8\\jdk\\bin\\java")}));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        testContextClassLoader();
        String url = "jdbc:mysql://localhost:3306/test";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = java.sql.DriverManager.getConnection(url, "name", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}