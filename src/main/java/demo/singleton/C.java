package demo.singleton;

/**
* 单例模式-懒汉模式-基于枚举实现
* C
* @Author xuan
* @Date  2021/1/5 16:58
*
*/
public class C {
    private C() {
    }

    public static C getInstance() {
        return ContainerHolder.HOLDER.instance;
    }

    private enum ContainerHolder {
        HOLDER;
        private C instance;
        private ContainerHolder() {
            instance = new C();
        }
    }

}
