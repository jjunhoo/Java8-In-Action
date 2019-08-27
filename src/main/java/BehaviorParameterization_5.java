import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BehaviorParameterization_5 {
    public static void main(String[] args) {
        /*
        List<Apple> inventory = new ArrayList<>();
        Apple greenApple = new Apple("Apple", "Green", 200);
        Apple redApple = new Apple("Apple", "Red", 250);
        Apple blueApple = new Apple("Apple", "Blue", 300);
        inventory.add(greenApple);
        inventory.add(redApple);
        inventory.add(blueApple);
        */
        List<Apple> inventory = Arrays.asList(new Apple("Apple", "Green", 200),
                                              new Apple("Apple", "Blue", 250),
                                              new Apple("Apple", "Red", 300));

        /* Green 색상 사과 필터링 */
        AppleGreenColorPredicate greenColorApple = new AppleGreenColorPredicate();
        List<Apple> greenApples = filterApples(inventory, greenColorApple);
        System.out.println("Green Apple Filtering : " + greenApples.toString());

        /* 150 이상의 무게인 사과 필터링 */
        //List<Apple> HeavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        //System.out.println(HeavyApples.toString());

        /* Red 색상 사과 필터링(익명클래스 버전) */
        // 익명클래스로 인터페이스를 구현하는 여러 클래스를 선언하는 과정을 줄일 수는 있음
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "Red".equals(apple.getColor());
            }
        });
        System.out.println("Red Apple Filtering : " + redApples.toString());

        /* Blue 색상 사과 필터링(람다식 버전) */
        // 람다식 사용으로 보다 간결한 메소드 구현(코드 작성) 가능
        List<Apple> blueApples = filterApples(inventory, apple -> "Blue".equals(apple.getColor()));
        System.out.println("Blue Apple Filtering : " + blueApples.toString());
    }
    // 파라미터로 전달한 ApplePredicate 객체에 의해 filterApples 메소드의 동작 결정
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            // Predicate 클래스의 test 메소드를 사용하여 필터링
            if(p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
}