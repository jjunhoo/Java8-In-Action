package StreamDataCollector;

import Stream.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Grouping {

    public static void main(String[] args) {
        // 그룹화
        /*
         * Collectors.groupingBy 를 이용해서 아래와 같이 그룹화를 쉽게 할 수 있음
         */
        // Dish.Type 과 일치하는 모든 요리를 추출하는 함수를 groupingBy 메서드로 전달
        // 이 함수를 기준으로 스트림이 그룹화되므로 이를 '분류 함수' 라고 부름
        // 그룹화 함수가 반환하는 키, 각 키에 대응하는 스트림의 모든 항목 리스트를 값으로 갖는 맵 반환
        Map<Dish.Type, List<Dish>> dishedByType = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType));
        System.out.println("[dishedByType] : " + dishedByType);

        // 400 칼로리 이하 'diet', 400 ~ 700 칼로리 'normal', 700 칼로리 초과 'fat' 분류
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dish.menu.stream().collect(
                Collectors.groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );
        System.out.println("[dishesByCaloricLevel] : " + dishesByCaloricLevel);
        System.out.println("[NORMAL] : " + dishesByCaloricLevel.get(CaloricLevel.NORMAL));
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }
}
