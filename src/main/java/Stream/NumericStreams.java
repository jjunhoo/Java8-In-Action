package stream;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);

        // List의 element를 각각 forEach를 통해서 출력
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        // menu 리스트의 칼로리를 mapToInt 하여 총합 추출
        int calories = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println("Number of calories : " + calories);

        // mapToInt를 사용하여 추출한 최대칼로리 음식값이 있는 경우, maxCalories 출력 / 없는 경우 max = 1 출력
        OptionalInt maxCalories = Dish.menu.stream().mapToInt(Dish::getCalories).max();
        int max;
        if (maxCalories.isPresent()) {
            max = maxCalories.getAsInt();
        } else {
            max = 1;
        }
        System.out.println("max : " + max);

        // 1 ~ 100 사이 숫자 생성 후 짝수인 숫자만 추출
        IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
        System.out.println("evenNumbers : " + evenNumbers.count());

        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                                                                                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0).boxed()
                                                                                                        .map(b -> new int[]{a, b, (int) Math.sqrt(a*a + b*b)}));
        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
