import java.util.ArrayList;
import java.util.List;

public class BehaviorParameterization_3 {
    public static void main(String[] args) {
        List<Apple> appleInventory = new ArrayList<>();
        Apple greenApple = new Apple("Apple", "Green", 100);
        Apple redApple = new Apple("Apple", "Red", 120);
        Apple blueApple = new Apple("Apple", "Blue", 150);

        appleInventory.add(greenApple);
        appleInventory.add(redApple);
        appleInventory.add(blueApple);

        appleInventory = filterGreenApples(appleInventory, 100);
        System.out.println("result : " + appleInventory.toString());
    }
    /* 무게(weight) 파라미터화 */
    public static List<Apple> filterGreenApples(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }
        return result;
    }
}

