package behaviorparameterization;

import java.util.ArrayList;
import java.util.List;

public class BehaviorParameterization_1 {
    public static void main(String[] args) {
        List<Apple> appleInventory = new ArrayList<>();
        Apple greenApple = new Apple("Apple", "Green", 100);
        Apple redApple = new Apple("Apple", "Red", 120);
        Apple blueApple = new Apple("Apple", "Blue", 150);

        appleInventory.add(greenApple);
        appleInventory.add(redApple);
        appleInventory.add(blueApple);

        appleInventory = filterGreenApples(appleInventory);
        System.out.println("result : " + appleInventory.toString());
    }
    // 'Green' Apple 필터링
    private static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if("Green".equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}

