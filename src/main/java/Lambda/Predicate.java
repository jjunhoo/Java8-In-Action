package lambda;

import java.util.ArrayList;
import java.util.List;

/*
 java.util.function.Predicate<T> 인터페이스는 test라는 추상메서드를 정의하며
 test는 제네릭 형식 T의 객체를 인수로 받아 Boolean을 반환
 */
public class Predicate {
    public static void main(String[] args) {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("abc");
        listOfStrings.add("");
        listOfStrings.add("123");
        System.out.println(listOfStrings);

        // 람다식을 이용하여 문자열이 비어있지 않은 아이템 반환하는 Predicate의 test 메소드 작성
        testPredicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        // 문자열 List와 nonEmptyStringPredicate에 작성한 Predicate 파라미터 전달
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }
    @FunctionalInterface
    public interface testPredicate<T> {
        boolean test(T t);
    }
    public static <T> List<T> filter(List<T> list, testPredicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T s : list) {
            if(p.test(s)) {
                result.add(s);
            }
        }
        return result;
    }
}
