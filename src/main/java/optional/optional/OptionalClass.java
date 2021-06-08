package optional.optional;

import java.util.Optional;

public class OptionalClass {

    public static void main(String[] args) {
        // 1. 빈 Optional 만들기
        Optional<Car> optCar = Optional.empty();
        System.out.println("" + optCar);

        // 2. null 이 아닌 값으로 Optional 만들기
        Car car = new Car();
        Optional<Car> optCar2 = Optional.of(car);
        System.out.println("" + optCar2);

        // 즉시 NullPointerException 발생
        // Optional<Car> optCar3 = Optional.of(null);
        // System.out.println("" + optCar3);

        // 3. null 값으로 Optional 만들기
        // 값이 null 인 경우, 빈 Optional 객체 반환
        Optional<Car> optCar4 = Optional.ofNullable(null);
        System.out.println("" + optCar4);

    }
}
