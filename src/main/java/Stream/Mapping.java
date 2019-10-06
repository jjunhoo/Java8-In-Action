package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mapping {
    public static void main(String[] args) {
        // Map
        // menu List 이름 모두 추출
        // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
        List<String> dishNames = Dish.menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(dishNames);
        System.out.println("==========================================================");

        // Map
        // words List의 element 문자열 길이 추출
        // [5, 5]
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream().map(String::length).collect(Collectors.toList());
        System.out.println(wordLengths);
        System.out.println("==========================================================");

        // flatMap : 각 배열을 스트림이 아니라 스트림의 콘텐츠로 매핑 (하나의 평면화된 스트림 반환)
        // ["Hello", "World"] 문자열 리스트 안의 각 문자들을 중복없이 추출
        words.stream().flatMap((String line) -> Arrays.stream(line.split(""))).distinct().forEach(System.out::println);
        System.out.println("==========================================================");

        // flatMap
        // 합이 3으로 나누어 떨어지는 것의 합만 출력
        List<Integer> numbers1 = Arrays.asList(1,2,3,4,5);
        List<Integer> numbers2 = Arrays.asList(6,7,8);
        List<int[]> pairs = numbers1.stream().flatMap((Integer i) -> numbers2.stream().map((Integer j) -> new int[]{i, j}))
                            .filter(pair -> (pair[0] + pair[1]) % 3 == 0)
                            .collect(Collectors.toList());
        pairs.forEach(pair -> System.out.println("(" + pair[0] + "," + pair[1] + ")"));
    }
}
