package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Finding {
    public static void main(String[] args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("Vegetarian Friendly !");
        }
        System.out.println("isHealthyMenu : " + isHealthyMenu());
        System.out.println("isHealthyMenu2 : " + isHealthyMenu2());

        // ifPresent : 값이 있으면 주어진 블록문 실행
        // Optional : 값의 존재나 부재 여부를 표현하는 컨테이너 클래스
        // T get() : 값이 존재하면 값을 반환하고, 값이 없으면 NoSuchElementException 반환
        // T orElse(T other) : 값이 있으면 값을 반환하고, 값이 없으면 기본값을 반환
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));

        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                           .map(x -> x * x)
                           .filter(x -> x % 3 == 0)
                           .findFirst();
        System.out.println("[firstSquareDivisibleByThree] : " + firstSquareDivisibleByThree);
    }
    // anyMatch : 주어진 스트림에서 적어도 한 요소와 일치하는지 확인
    private static boolean isVegetarianFriendlyMenu() {
        return Dish.menu.stream().anyMatch(Dish::isVegetarian);
    }
    // allMatch : 모든 요소와 일치하는지 확인
    private static boolean isHealthyMenu() {
        return Dish.menu.stream().allMatch(d -> d.getCalories() < 1000);
    }
    // noneMatch : 주어진 스트림에서 일치하는 요소가 없는지 확인
    private static boolean isHealthyMenu2() {
        return Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }
    // findAny : 현재 스트림에서 임의의 요소 반환
    private static Optional<Dish> findVegetarianDish(){
        return Dish.menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
