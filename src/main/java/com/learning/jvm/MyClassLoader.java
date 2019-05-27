package com.learning.jvm;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String myPath = "D://" + name.replace(".","/") + ".class";
        byte[] buff = null;
        try {
            buff = Files.readAllBytes(Paths.get(myPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class clazz = defineClass("",buff,0,buff.length);
        return clazz;
    }

    @Test
    public void demo() throws ClassNotFoundException {
       /* ClassLoader currentClassloader = MyClassLoader.class.getClassLoader();
        String pp = "d:\\";
        MyClassLoader classLoader = new MyClassLoader(currentClassloader,pp);
        Class clazz = classLoader.loadClass("com/learning/collection/HashMapDemo");
        System.out.println(clazz);*/
    }

    @Test
    public void test() {
        ArrayList list = new ArrayList();
        list.add(null);
        list.add(null);
        list.add(null);
        list.forEach(e->{
            System.out.println(e);
        });
    }
}
