package StreamDataCollector;

import Stream.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.toList;

public class Partitioning {
    public static void main(String[] args) {
        // 분할
        // 분할 함수는 boolean 반환
        // 채식 요리와 비채식 요리 분류
        Map<Boolean, List<Dish>> partitionedMenu = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));
        System.out.println("[partitionedMenu] : " + partitionedMenu);

        List<Dish> vegetarianDishes = Dish.menu.stream().filter(Dish::isVegetarian).collect(toList());
        System.out.println("[vegetarianDishes] : " + vegetarianDishes);
    }
}
