package demo.annotationtest;

public class AAA {

    @AnnotationTest(a = "abc")
    public void a() {
        System.out.println("aaaa");
    }
}
