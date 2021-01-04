package demo;

public interface TestInterFace<T, N> {
    T testT();
    N testN();
}

class A implements TestInterFace<String, Integer> {
    @Override
    public String testT() {
        return null;
    }
    @Override
    public Integer testN() {
        return null;
    }
}

class B<T, N> implements TestInterFace<T, N> {
    @Override
    public T testT() {
        return null;
    }
    @Override
    public N testN() {
        return null;
    }

    public  <A> A testA(A a){
        System.out.println(a);
        return a;
    }
}