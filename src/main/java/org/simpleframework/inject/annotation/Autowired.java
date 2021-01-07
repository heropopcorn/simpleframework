package org.simpleframework.inject.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    /**
     * spring框架中使用@Qualifier注解供用户指定注入的bean,此处为了模拟简便直接定义属性值用于指定bean
     * @return
     */
    String value() default "";
}
