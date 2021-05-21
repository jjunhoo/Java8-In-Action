package StreamDataCollector;

import Stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Partitioning {
    public static void main(String[] args) {
        // 분할
        // 분할 함수는 boolean 반환
        // 채식 요리와 비채식 요리 분류
        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("[partitionedMenu] : " + partitionedMenu);

        List<Dish> vegetarianDishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println("[vegetarianDishes] : " + vegetarianDishes);

        // 채식, 비채식 요리 partitioning 및 요리 종류 분류
        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)) // 분할 함수, 2번째 컬렉터
        );
        System.out.println("[vegetarianDishesByType] "  + vegetarianDishesByType);

        // 채식, 비채식 요리 각각의 그룹에서 가장 칼로리가 높은 요리 추출
        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get
                        ))
        );
        System.out.println("[mostCaloricPartitionedByVegetarian] : " + mostCaloricPartitionedByVegetarian);

        // 채식, 비채식 요리별 500 칼로리가 넘는 요리 partitioning
        Map<Boolean, Map<Boolean, List<Dish>>> collect1 = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, partitioningBy(dish -> dish.getCalories() > 500))
        );
        System.out.println("[collect1] : " + collect1);

        // 채식, 비채식 요리별 개수
        Map<Boolean, Long> collect2 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println("[collect2] : " + collect2);

        // 숫자를 소수와 비소수로 분할하기
        System.out.println("[isPrime] : " + isPrime(5));
    }
    /* 숫자를 소수와 비소수로 분할하기 */
    private static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate).noneMatch(i -> candidate % i == 0);
    }
}
