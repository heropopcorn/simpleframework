package demo.singleton;


/**
* 单例模式-懒汉模式-基于内部类实现
* D
* @Author xuan
* @Date  2021/1/5 16:58
*
*/
public class D {

    private D() {
    }    // 单例持有者

    private static class InstanceHolder {
        private final static D instance = new D();
    }

    public static D getInstance() {        // 调用内部类属性
        return InstanceHolder.instance;
    }
}