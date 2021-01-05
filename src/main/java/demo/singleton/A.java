package demo.singleton;

/**
 * 单例模式-饿汉模式
 * A
 *
 * @Author xuan
 * @Date 2021/1/5 15:56
 */
public class A {
    private static final A instance = new A();//私有静态成员：类加载被执行，无法被外部直接访问，无法被修改

    private A() {
    } //私有构造方法，外部无法访问

    public static A getA() { //提供单例方法
        return instance;
    }
}