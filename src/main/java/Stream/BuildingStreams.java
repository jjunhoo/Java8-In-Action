package Stream;

import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String...args)  {
        Stream<String> stream = Stream.of("Java8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
    }
}
