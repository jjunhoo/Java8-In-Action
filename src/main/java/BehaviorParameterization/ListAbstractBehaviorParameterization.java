package BehaviorParameterization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ListAbstractBehaviorParameterization {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple("Apple","Green",100),
                                              new Apple("Apple","Red",150));

        List<Integer> numbers = Arrays.asList(100, 50, 25);

        /* List 형식으로 추상화한 Red 색상 사과 필터링 조건과 람다식 혼용 */
        List<Apple> redApples = filter(inventory, (Apple apple) -> "Red".equals(apple.getColor()));
        System.out.println("Red Apple : " + redApples.toString());

        /* List 형식으로 추상화한 짝수 필터링 조건과 람다식 혼용 */
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println("Even Numbers : " + evenNumbers);
    }
    // List 형식으로 추상화한 다양한 조건 필터링
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
