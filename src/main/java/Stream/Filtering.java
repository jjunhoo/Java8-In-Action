package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {
    public static void main(String[] args) {
        // Filtering with predicate
        // isVegetarian = true인 item만 추출 (filter)
        List<Dish> vegetarianMenu = Dish.menu.stream()
                                        .filter(Dish::isVegetarian)
                                        .collect(Collectors.toList());
        vegetarianMenu.forEach(System.out::println);
        System.out.println("==========================================================");

        // Filtering unique elements (distinct)
        // 7개의 숫자 List에서 짝수인 숫자만 중복없이 추출
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        System.out.println("==========================================================");

        // Truncating a stream
        // 300 칼로리가 넘는 음식 3개만 추출 (limit)
        List<Dish> dishesLimit3 = Dish.menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());
        dishesLimit3.forEach(System.out::println);
        System.out.println("==========================================================");

        // Skipping elements
        // 300 칼로리가 넘는 음식 중에서 2개까지는 skip 후 3번째부터 추출
        List<Dish> dishesSkip2 = Dish.menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());
        dishesSkip2.forEach(System.out::println);
    }
}
