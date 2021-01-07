package org.simpleframework.core;

import demo.singleton.B;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.annotation.Component;
import org.simpleframework.core.annotation.Controller;
import org.simpleframework.core.annotation.Repository;
import org.simpleframework.core.annotation.Service;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanContainer {
    /*
    beanMap:key为Class，value为Class通过newInstance创建的对象
     */
    private static final ConcurrentHashMap<Class<?>, Object> beanMap = new ConcurrentHashMap();

    /*
        注解类的Class列表
     */
    private static final List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class, Controller.class, Service.class, Repository.class);

    /*
    容器是否被加载过
    由于实际情况下容器的加载可能比较慢，此处设置标识字段用于避免容器重复加载
     */
    private boolean loaded = false;

    public boolean isLoaded() {
        return loaded;
    }

    public static BeanContainer getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    /**
     * 扫描加载所有Bean
     *
     * @param packageName
     */
    public synchronized void loadBeans(String packageName) {
        /*
        判断容器是否被加载过
        */
        if (isLoaded()) {
            log.warn("simpleframework:BeanContainer has been loaded");
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("simpleframework:extract nothing from packageName:{}", packageName);
            return;
        }
        for (Class<?> clazz : classSet) {
            for (Class<? extends Annotation> annotation : BEAN_ANNOTATION) {
                if (clazz.isAnnotationPresent(annotation)) {
                    //如果加载的类存在注解，则将目标作为key，目标实例作为value存入beanMap中
                    beanMap.put(clazz, ClassUtil.newInstance(clazz, true));
                }
            }
        }
        loaded = true;
    }

    /**
     * 添加bena
     *
     * @param clazz
     * @param bean
     * @return
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 删除bean
     *
     * @param clazz
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 获取bean
     *
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取容器管理的所有Bean实例集合
     *
     * @return
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 通过注解获取容器管理的Class对象集合
     *
     * @param annotation
     * @return
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }

        HashSet<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            if (clazz.isAnnotationPresent(annotation)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }


    /**
     * 根据接口或者父类获取容器管理的Class集合
     *
     * @param interfaceOrClass
     * @return
     */
    public Set<Class<?>> getClassesBySuper(Class<?> interfaceOrClass) {
        Set<Class<?>> keySet = getClasses();
        if (ValidationUtil.isEmpty(keySet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        HashSet<Class<?>> classSet = new HashSet<>();
        for (Class<?> clazz : keySet) {
            //判断clazz是否为interfaceOrClass的实现类或者子类
            if (interfaceOrClass.isAssignableFrom(clazz) && !clazz.equals(interfaceOrClass)) {
                classSet.add(clazz);
            }
        }
        return classSet.size() > 0 ? classSet : null;
    }

    /**
     * 获取容器管理的Bean实例数量
     * @return
     */
    public int size(){
        return beanMap.size();
    }

    private enum ContainerHolder {
        HOLDER;
        private BeanContainer instance;

        ContainerHolder() {
            instance = new BeanContainer();
        }

    }


}
