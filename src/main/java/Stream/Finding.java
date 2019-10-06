package Stream;

import java.util.Optional;

public class Finding {
    public static void main(String[] args) {
        if (isVegetarianFriendlyMenu()) {
            System.out.println("Vegetarian Friendly !");
        }
        System.out.println("isHealthyMenu : " + isHealthyMenu());
        System.out.println("isHealthyMenu2 : " + isHealthyMenu2());

        // ifPresent : 값이 있으면 주어진 블록문 실행
        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(d -> System.out.println(d.getName()));
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
