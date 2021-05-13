package StreamDataCollector;

import Stream.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

        // 다수준 그룹화
        // 두 인수를 받는 팩토리 메서드 Collectors.gourpingBy 를 이용해서 항목을 다수준으로 그룹화 가능
        // 바깥 쪽 groupingBy 메서드에 스트림의 항목을 분류할 두 번째 기준을 정의하는 내부 groupingBy 를 전달해서 두 수준으로 스트림의 항목을 그룹화
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishedByTypeCaloricLevel = Dish.menu.stream().collect(
                Collectors.groupingBy(Dish::getType,        // 외부 맵 키 : 첫 번째 수준의 분류 함수에서 분류한 키값 'FISH, MEAT, OTHER' 를 키값으로 갖음
                        Collectors.groupingBy(dish -> {     // 외부 맵 값 : 두 번째 수준의 분류 함수의 기준 'NORMAL, DIET, FAT' 을 키값으로 갖음
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        }))
        );
        System.out.println("[다수준 그룹화] : " + dishedByTypeCaloricLevel);

        // 서브그룹으로 데이터 수집
        // 메뉴 > 요리 개수 종류별 계산
        Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));
        System.out.println("[서브 그룹] : " + typesCount);

        // 메뉴 > 요리별 가장 높은 칼로리 추출
        Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("[서브 그룹 > 요리별 가장 높은 칼로리 추출] : " + mostCaloricByType);
    }

    public enum CaloricLevel { DIET, NORMAL, FAT }
}
