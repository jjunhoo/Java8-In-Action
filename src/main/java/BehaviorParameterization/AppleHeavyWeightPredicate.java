package behaviorparameterization;

// ApplePredicate 인터페이스의 test 메소드를 오버라이딩하여 사과의 무게가
// 150 이상인 것만 추출하는 메소드 구현
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
