package my.study.colibear._01_basic._05_default_method_and_java_module;

public class JavaDefaultMethod {
    public static void main(String[] args) {

    }
}

interface DefaultMethod {
    default void method() {
        System.out.println("this is default method");
    }
}
