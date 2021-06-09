package behaviorparameterization;

import java.util.ArrayList;
import java.util.List;

public class BehaviorParameterization_6 {
    public static void main(String[] args) {
        List<Apple> appleBox = new ArrayList<>();
        Apple greenApple = new Apple("apple", "Green", 100);
        Apple yellowApple = new Apple("apple", "Yellow", 200);

        appleBox.add(greenApple);
        appleBox.add(yellowApple);

        prettyPrintApple(appleBox, new AppleFancyFormatter());
        prettyPrintApple(appleBox, new AppleSimpleFormatter());
    }

    private static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for(Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
