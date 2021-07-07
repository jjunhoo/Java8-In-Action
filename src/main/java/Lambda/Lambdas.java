package lambda;

import behaviorparameterization.Apple;
import behaviorparameterization.ApplePredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Lambdas {
    public static void main(String[] args) {
        // 람다식 간단 예제
        Runnable r = () -> System.out.println("Hello");
        r.run();

        // 람다식 사용 필터링
        List<Apple> inventory = Arrays.asList(new Apple("Apple","Green", 150), new Apple("Apple","Blue",100), new Apple("Apple","Red",180));
        // 람다식을 사용하여 ApplePredicate의 test 메소드를 직접 동작 파라미터화 하여 전달
        List<Apple> greenApple = filter(inventory, (Apple apple) -> "Green".equals(apple.getColor()));
        System.out.println(greenApple);

        // 람다식을 사용하여 두 객체의 Weight를 비교하는 Comparator 구현
        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(c);
        System.out.println(inventory);
    }
    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}
