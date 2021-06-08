package optional.optional;

import optional.notoptional.Car;
import java.util.Optional;

public class Person {
    // 사람이 차를 소유했을 수도 소유하지 않았을 수도 있으므로 OptionalClass 로 정의
    private Optional<Car> car;
    public Optional<Car> getCar() {
        return car;
    }
}
