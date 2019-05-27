
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

public class ClassLoaderDemo {

    @Test
    public void demo() throws ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class clazz = classLoader.loadClass("java.lang.Long");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());

        System.out.println(classLoader);
        System.out.println(ClassLoaderDemo.class.getClassLoader());
    }


    @Test
    public void t1() {
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader  = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("the Thread appClassLoader : " + classLoader);
        System.out.println();

        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }

}


