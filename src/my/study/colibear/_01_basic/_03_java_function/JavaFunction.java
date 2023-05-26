package my.study.colibear._01_basic._03_java_function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaFunction {

    public static void main(String[] args) {
        new JavaFunction().firstCitizen();
    }
    public void firstCitizen() {
        // 익명 클래스
        System.out.println("[non-named class]");
        MethodReference mr1 = new MethodReference() {
            @Override
            public boolean predicate(Class clazz) {
                return clazz != null;
            }
        };

        List<String> stringList = new ArrayList<>();
        System.out.println("mr1.test(stringList.getClass()) = " + mr1.predicate(stringList.getClass()));
        List<Map> mapList = new ArrayList<>();
        System.out.println("mr1.test(mapList.getClass()) = " + mr1.predicate(mapList.getClass()));
        Integer integer = 10;
        System.out.println("mr1.test(integer.getClass()) = " + mr1.predicate(integer.getClass()));

        System.out.println("\n[lambda]");
        // 람다
        MethodReference mr2 = clazz -> clazz.getName().equals(Integer.class.getName());
        System.out.println("mr2.test(Integer.class) = " + mr2.predicate(Integer.class));
        System.out.println("mr2.test(String.class) = " + mr2.predicate(String.class));

        // method reference

        MethodReference stp = new StringTypePredicate();

        Wrapper w1 = (m, v) -> m.predicate(v);
        System.out.println("w1.run(stp, Integer.class) = " + w1.run(stp, Integer.class));
        System.out.println("w1.run(stp, String.class) = " + w1.run(stp, String.class));
        System.out.println("\n[method reference]");
        Wrapper w2 = MethodReference::predicate;
        System.out.println("w2.run(stp, Integer.class) = " + w2.run(stp, Integer.class));
        System.out.println("w2.run(stp, String.class) = " + w2.run(stp, String.class));

    }
}

@FunctionalInterface
interface MethodReference {
    boolean predicate(Class clazz);
}

class StringTypePredicate implements MethodReference {

    public StringTypePredicate() {

    }

    @Override
    public boolean predicate(Class clazz) {
        return String.class.getName().equals(clazz.getName());
    }
}

interface Wrapper<T extends MethodReference, V extends Class> {
    boolean run(T t, V v);
}
