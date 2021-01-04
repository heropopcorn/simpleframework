package demo.annotationtest;

import java.lang.reflect.Method;

public class AnnotationMain {

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        System.out.println("===========================");
        AAA aaa = new AAA();
        aaa.a();
        Method a = aaa.getClass().getMethod("a");
        /**
         * 注解底层原理就是通过生成代理类来获取注解中的属性。
         * 在获取类上注解中的值时，jdk会生成注解接口的代理类，然后代理类中会生成注解属性值同名的方法（暂时记为方法1），
         * 方法的返回值则是属性的类型，
         * 其中有一段代码：(String)super.h.invoke(this, m3, (Object[])null)//这个this是InvocationHandler类的实体，m3则是（方法1的Method类型的实现）
         * invoke方法点进去就进入到了接口InvocationHandler(java.lang.reflect)，这个是后需要找到实际调用了哪个实现类的方法，
         * 猜测（有经验的才猜测）为注解相关的类，后来就找到了AnnotationInvocationHandler(sun.reflect.annotation)类，经过debug确定调用的是其中的实现方法，
         * 其中的成员变量memberValues存储了注解相关的信息
         * ps:整个注解底层实现原理暂时还是没有理解，具体是怎样调用到AnnotationInvocationHandler中的invoke方法的暂时还没有理清楚
         *
         */
        Thread thread = new Thread();

        AnnotationTest annotation = a.getAnnotation(AnnotationTest.class);
        String a1 = annotation.a();
        System.out.println(a1);
    }
}
