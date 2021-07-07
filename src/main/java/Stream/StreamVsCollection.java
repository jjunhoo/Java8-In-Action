package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {
    public static void main(String...args){
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);

        // 아래 주석 해제 시 IllegalStateException 발생 : 스트림은 생성 후 한 번만 소비 가능
        // s.forEach(System.out::println);
    }
}
