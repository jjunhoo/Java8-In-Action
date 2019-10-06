package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Reducing {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        // numbers List 총합
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println("sum : " + sum);
        System.out.println("========================================");

        // numbers List 총합
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum2 : " + sum2);
        System.out.println("========================================");

        // numbers List 가장 큰 수
        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println("max : " + max);
        System.out.println("========================================");

        // numbers List 가장 작은 수
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);
        System.out.println("========================================");

        // 메뉴의 칼로리 총합
        int calories = Dish.menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println("Number of calories : " + calories);
    }
}
