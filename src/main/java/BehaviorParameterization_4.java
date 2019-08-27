import java.util.ArrayList;
import java.util.List;

/* 가능한 모든 속성으로 필터링 */
public class BehaviorParameterization_4 {
    public static void main(String[] args) {

    }
    public static List<Apple> filterApples(List<Apple> inventory, String color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            /*
            flag : true 이면서 apple 인스턴스의 color가 메소드의 파라미터인 color와 동일할 경우
            Color 필터링
            flag : false 이면서 apple 인스턴스의 weight가 메소드의 파라미터인 weight보다 큰 경우
            Weight 필터링

            flag의 true, false 값의 의미를 유추할 수 없을뿐더러
            추후 요구사항이 변경될 경우, 유연하게 대응할 수 없음
            예를 들어, 사과의 크기, 모양, 출하지 등으로 사과를 필터링하려면
            여러 중복된 필터 메소드를 만들거나, 모든 것을 처리하는 거대한
            하나의 필터 메서드를 구현해야 함.
            */
            if((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }
}