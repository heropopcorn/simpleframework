package demo.singleton;

/**
* ����ģʽ-����ģʽ
* B
* @Author xuan
* @Date  2021/1/5 15:53
*
*/
public class B {
    private volatile static B instance;
    private B() {}
    public static B getB() {
        //��һ�μ��
        if (instance == null) {
            //ͬ��
            synchronized (B.class) {
                //�ڶ��μ��
                if (instance == null) {
                    /**
                     * ��ʼ��instance
                     * instance������Ҫʹ��volatile���ε�ԭ��
                     * instance = new B()��һ���������Ϸ�Ϊ������ͨ��javap -c����������Է��֣���
                     * 1��memory = allocate();//��������ڴ�ռ�
                     * 2��instance(memory);//��ʼ������
                     * 3��instance = memory;//����instanceָ��շ�����ڴ��ַ����ʱinstance��=null
                     * �������volatile���Σ����������2��3������������ϵ�����Կ��ܱ�����ִ�����������˳�����Կ��ܳ������������
                     * �߳�Aû��ִ�е�2��������ִ���˵�3������ô��ʱ�������һ���̣߳�����ΪB����ʼ����getB����,��ʱinstance�Ѿ���Ϊnull�ˣ�
                     * ����B�̻߳�ֱ�ӻ�ȡ����ֵ�����Ǵ�ʱB��ȡ��instance�����Ǳ��������ڴ��ַ����û�б�ʵ�ʳ�ʼ����B��ʹ��instanceʱ�ͻ��������
                     * ����volatile�ؼ��ֿ��Ա���ָ������򣬴Ӷ������������
                     */
                    instance = new B();
                }
            }
        }
        return instance;
    }

}
