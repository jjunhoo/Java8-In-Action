package BehaviorParameterization;

import java.util.ArrayList;
import java.util.List;

public class BehaviorParameterization_2 {
    public static void main(String[] args) {
        List<Apple> appleInventory = new ArrayList<>();
        Apple greenApple = new Apple("Apple", "Green", 100);
        Apple redApple = new Apple("Apple", "Red", 120);
        Apple blueApple = new Apple("Apple", "Blue", 150);

        appleInventory.add(greenApple);
        appleInventory.add(redApple);
        appleInventory.add(blueApple);

        // Blue Apple - Filter
        appleInventory = filterGreenApples(appleInventory, "Blue");
        System.out.println("result : " + appleInventory.toString());
    }
    // 색(color) 파라미터화
    private static List<Apple> filterGreenApples(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(color.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }
}

