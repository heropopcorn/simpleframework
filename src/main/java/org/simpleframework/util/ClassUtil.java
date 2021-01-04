package org.simpleframework.util;

import java.net.URL;
import java.util.Set;

public class ClassUtil {

    /**
     * 获取包下类集合
     * @param packageName
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName){
        /*
        获取类加载器
         */
        ClassLoader classLoader = getClassLoader();
        /*
        通过类加载器获取加载的资源
        由于classloader的getResource方法需要的是反斜杠分割的包名称，所以需要进行替换
         */
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if(url == null){
            return null;
        }

        return null;
    }

    /**
     * 获取类加载器
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }
}
