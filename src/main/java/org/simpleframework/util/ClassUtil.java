package org.simpleframework.util;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.INTERNAL;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 类加载工具类
 * ClassUtil
 *
 * @Author xuan
 * @Date 2021/1/5 14:08
 */
@Slf4j
public class ClassUtil {

    public static final String FILE_PROTOCOL = "file";

    /**
     * 获取包下类的集合
     *
     * @param packageName
     * @return 类集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        /*
        获取类加载器
         */
        ClassLoader classLoader = getClassLoader();
        /*
        通过类加载器获取资源资源的唯一地址
        由于classloader的getResource方法需要的是反斜杠分割的包名称，所以需要进行替换
         */
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {
            log.warn("未获取到资源：{}", packageName);
            return null;
        }
        /**
         * 依据不同的资源类型，采用不同的方式获取资源的集合
         */
        Set<Class<?>> classSet = null;
        //过滤出文件类型的资源
        if (url.getProtocol().equalsIgnoreCase(FILE_PROTOCOL)) {
            classSet = new HashSet<Class<?>>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        //TODO 此处还可以增加if语句块并加入处理其他资源的逻辑，如jar包
        return classSet;
    }

    /**
     * 获取目标package中的所有class文件（包括子package里的class文件）
     *
     * @param emptyClassSet 类在目标的集合
     * @param fileSource    文件或者目录
     * @param packageName   包名称
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            //如果不是目录则返回
            return;
        }

        //如果是文件夹，则获取下面的所有文件夹或文件,其中包含过滤操作
        File[] files = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    //获取文件的绝对值路径
                    String absolutePath = file.getAbsolutePath();
                    if (absolutePath.endsWith(".class")) {
                        //若是class文件，则直接加载
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            private void addToClassSet(String absolutePath) {
                //获取完整的类名
                absolutePath = absolutePath.replace(File.separator, ".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //通过反射机制获取Class对象并加入classSet
                Class<?> targetClass = loadClass(className);
                emptyClassSet.add(targetClass);
            }
        });

        if (files != null) {
            for (File f : files) {
                //递归处理目录
                extractClassFile(emptyClassSet, f, packageName);
            }
        }

    }

    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("加载类异常：{}", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取类加载器
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }


    /**
     *  实例化class
     * @param clazz class类型
     * @param accessible  是否支持创建私有
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<?> clazz,boolean accessible) {
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(accessible);
            return (T) constructor.newInstance();
        } catch (Exception e) {
            log.error("simpleframework : newInstance error");
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置类的属性值
     * @param field
     * @param target
     * @param value
     * @param accessible
     */
    public static void setField(Field field,Object target,Object value,boolean accessible){
        field.setAccessible(accessible);
        try {
            /*
            关于Field的set方法，入参说明如下：
            target: the object whose field should be modified
            value: the new value for the field of {@code target}
            Field本身是通过Class对象获取的，是属于Class对象的，不属于具体的实体，所以调用field设置属性值时必须传入具体的实现
             */
            field.set(target,value);
        } catch (IllegalAccessException e) {
           log.error("setField error:{}",e);
           throw new RuntimeException(e);
        }
    }

}
