package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {

    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行IOC
     */
    public void doIoc() {
        //1、遍历容器中所有的Class对象
        if (ValidationUtil.isEmpty(beanContainer.getClasses())) {
            log.warn("simpleframework:empty classset in BeanContainer");
            return;
        }
        //2、遍历Class对象的所有成员变量
        for (Class<?> clazz : beanContainer.getClasses()) {
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                //3、找出被Autowired标记的成员
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4、获取成员类型
                    Class<?> fieldClass = field.getType();
                    //5、获取成员在容器中的实例
                    Object fieldValue = getFieldInstance(fieldClass, autowiredValue);
                    if (fieldValue == null) {
                        throw new RuntimeException("unable to inject relevant type:" + fieldClass.getName() + " autowiredValue is:" + autowiredValue);
                    } else {
                        //6、通过反射将对应的成员变量实例注入到成员变量所在类中
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, targetBean, fieldValue, true);
                    }
                }
            }
        }


    }

    /**
     * 根据Class获取其在beanContainer里的实例或者实现类实例
     *
     * @param fieldClass
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {

        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue != null) {
            //如果类本身已经被容器管理了，则直接返回实例
            return fieldValue;
        } else {
            //获取接口的实现类
            Class<?> implementedClass = getImplementedClass(fieldClass, autowiredValue);
            if (implementedClass != null) {
                return beanContainer.getBean(implementedClass);
            } else {
                return null;
            }
        }
    }

    private Class<?> getImplementedClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(autowiredValue)) {
                //如果用户未指定具体的注入类型
                if (classSet.size() == 1) {
                    //返回第一个
                    return classSet.iterator().next();
                } else {
                    //TODO 确认此处是否需要优化，因为方法最底部是返回null而非抛出异常，所以此处是否可以直接返回null,具体null的问题已经在外部做出了处理
                    throw new RuntimeException("multiple implemented classes for {}" + fieldClass.getName());
                }
            } else {
                //指定了具体的注入类型
                for (Class<?> clazz : classSet) {
                    if (autowiredValue.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
