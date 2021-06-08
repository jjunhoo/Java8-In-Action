package behaviorparameterization;

// ApplePredicate 인터페이스의 test 메소드를 오버라이딩하여 "Green" 색상만 추출하는 메소드 구현
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "Green".equals(apple.getColor());
    }
}
