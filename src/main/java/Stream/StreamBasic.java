package stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class StreamBasic {
    public static void main(String...args){
        // Java 7
        // getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        System.out.println("---");
        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }
    // 400 칼로리 이상인 음식 추출 (JAVA 7)
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d: dishes){
            if(d.getCalories() > 400){
                lowCaloricDishes.add(d);
            }
        }
        // 칼로리 순으로 정렬
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            public int compare(Dish d1, Dish d2){
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });
        // 400 이상 칼로리 음식 이름 추출
        for(Dish d: lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
    // 400 칼로리 이상인 음식 추출 (JAVA 8)
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes.stream()  // 스트림 시작
                .filter(d -> d.getCalories() > 400)   // 400 이상 칼로리 음식 필터
                .sorted(comparing(Dish::getCalories)) // 400 이상 칼로리 음식 정렬
                .filter(d -> d.isVegetarian())
                .map(Dish::getName)                   // 400 이상 칼로리 음식 이름 매핑
                .collect(toList());                   // 스트림을 통해 추출된 데이터 리스트 반환
    }
}
