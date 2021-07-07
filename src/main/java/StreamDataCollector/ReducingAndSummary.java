package streamdatacollector;

import stream.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReducingAndSummary {
    // Collector 인터페이스 구현은 스트림의 요소를 어떤 식으로 도출할지를 지정한다.
    // toList : 각 요소를 리스트로 생성
    // groupingBy : 각 키 버킷 그리고 각 키 버킷에 대응하는 요소 리스트를 값으로 포함하는 맵(Map) 생성
    // => 문제를 해결하는 과정에서 다중 루프와 조건문을 추가하며 가독성과 유지보수성이 크게 떨어지는 명령형 코드를 다수준으로 그룹화하여 해결 가능

    /* Collector 에서 제공하는 메서드의 기능은 크게 3가지로 구분
       1. 스트림 요소를 하나의 값으로 리듀스하고 요약
       2. 요소 그룹화
       3. 요소 분할
     */

    public static void main(String[] args) {
        // [ 리듀싱과 요약 ]
        // Collector 로 스트림의 모든 항목을 하나의 결과로 합치기
        long howManyDished = Dish.menu.stream().collect(Collectors.counting());
        System.out.println("[howManyDished] : " + howManyDished);

        // 불필요 과정 생략
        long howManyDished2 = Dish.menu.stream().count();
        System.out.println("[howManyDished2] : " + howManyDished2);

        // [ 스트림값에서 최댓값과 최솟값 검색 ]
        // 칼로리로 요리를 비교하는 Comparator 구현
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(Collectors.maxBy(dishCaloriesComparator));
        System.out.println("[mostCalorieDish] : " + mostCalorieDish);

        // [ 요약 연산 ]
        // * Collectors.summingInt : 객체를 int 로 매핑한 컬렉터를 바탕으로 합계 반환
        // summingLong, summingDouble > 동작 방식 동일
        int totalCalories = Dish.menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println("[totalCalories] : " + totalCalories);

        int totalCalories2 = Dish.menu.stream().mapToInt(Dish::getCalories).sum(); // 가독성이 좋고, 자동 언박싱 연산 수행을 하므로 성능 측면에서도 좋음
        System.out.println("[totalCalories2] : " + totalCalories2);

        int totalCalories3 = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        System.out.println("[totalCalories3] : " + totalCalories3);

        // * Collectors.averagingInt : 객체를 int 로 매핑한 컬렉터를 바탕으로 평균값 반환
        double avgCalories = Dish.menu.stream().collect(Collectors.averagingInt(Dish::getCalories));

        // * Collectors.summarizingInt : count, sum, min, max, average 통계 추출
        // summarizingLong, summarizingDouble > 동작 방식 동일
        IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("[menuStatistics] : " + menuStatistics + " / [count] : " + menuStatistics.getCount() + " / [sum] : " + menuStatistics.getSum() + " / [min] : " + menuStatistics.getMin() + " / [max] : " + menuStatistics.getMax() + " / [average] : " + menuStatistics.getAverage());

        // [ 문자열 연결 ]
        // * Collectors.joining : 스트림의 각 객체에 toString 메서드를 호출해서 추출한 모든 문자열을 하나의 문자열로 연결해서 반환
        String shortMenu = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println("[shortMenu] : " + shortMenu);
        // * joining > 문자열 구분 (마지막 문자열 delimiter 생략!)
        String shortMenu2 = Dish.menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("[shortMenu2] : " + shortMenu2);

        // * 스트림 인터페이스에서 제공하는 메서드를 이용하는 것에 비해 컬렉터를 이용하는 코드가 더 복잡하다.
        // 하지만, 코드가 복잡한 대신 재사용성과 커스터마이징 가능성을 제공하는 높은 수준의 추상화와 일반화를 얻을 수 있다.
        // 그렇기 때문에 문제를 해결할 수 있는 다양한 해결 방법을 확인한 다음 가장 일반적으로 문제에 특화된 해결책을 고르는 것이 바람직하다 !
    }

}
