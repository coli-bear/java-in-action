package my.study.colibear._01_basic._04_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class JavaStream {
    public static void main(String[] args) {
        new JavaStream().stream();
    }

    private void stream() {
        List<Integer> integers = new ArrayList<>(1000000);

        for (int i = 0; i < 1000000; i++) {
            integers.add(i);
        }
        TimeTemplate.run(integers, i -> i.stream().map(IntegerUtils::convertToString).toArray());
        TimeTemplate.run(integers, i -> i.parallelStream().map(IntegerUtils::convertToString).toArray());

        System.out.println("diff stream and parallelStream");
        // stream : 22 ms
        TimeTemplate.run(integers, i -> i.stream().filter(IntegerUtils::filterEvenFrom).map(IntegerUtils::convertToString).collect(Collectors.toList()));
        // parallelStream : 44 ms
        TimeTemplate.run(integers, i -> i.parallelStream().filter(IntegerUtils::filterEvenFrom).map(IntegerUtils::convertToString).collect(Collectors.toList()));



    }
}

class TimeTemplate <V> {
    static <V extends Collection> void run(V value, Consumer<V> consumer) {
        long start = System.currentTimeMillis();
        consumer.accept(value);
        long end = System.currentTimeMillis();
        System.out.println("time= " + (end - start) + " ms");
    }

}

class IntegerUtils {
    static <T> String convertToString(T i) {
        return "[" + i + "]";
    }

    public static boolean filterEvenFrom(Integer s) {
        return (s & 1) == 0;
    }

}
