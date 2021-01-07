package demo.singleton;

/**
* 单例模式-懒汉模式-基于双层检查锁实现
* B
* @Author xuan
* @Date  2021/1/5 15:53
*
*/
public class B {
    private volatile static B instance;
    private B() {}
    public static B getB() {
        //第一次检测
        if (instance == null) {
            //同步
            synchronized (B.class) {
                //第二次检测
                if (instance == null) {
                    /**
                     * 初始化instance
                     * instance变量需要使用volatile修饰的原因：
                     * instance = new B()这一步，本质上分为三步（通过javap -c反编译类可以发现）：
                     * 1、memory = allocate();//分配对象内存空间
                     * 2、instance(memory);//初始化对象
                     * 3、instance = memory;//设置instance指向刚分配的内存地址，此时instance！=null
                     * 如果不加volatile修饰，由于上面的2和3不存在依赖关系，所以可能被程序执行器任意调整顺序，所以可能出现以下情况：
                     * 线程A没有执行第2步而是先执行了第3步，那么此时如果有另一个线程（假设为B）开始调用getB方法,此时instance已经不为null了，
                     * 所以B线程会直接获取返回值，但是此时B获取的instance仅仅是被分配了内存地址，并没有被实际初始化，B在使用instance时就会出现问题
                     * 加入volatile关键字可以避免指令被重排序，从而避免这个问题
                     */
                    instance = new B();
                }
            }
        }
        return instance;
    }

}
