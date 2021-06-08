package optional.optional;

import optional.notoptional.Insurance;

import java.util.Optional;

public class Car {
    // 자동차가 보험에 가입되어 있을 수도 가입되어 있지 않았을 수도 있으므로 OptionalClass 로 정의
    private Optional<Insurance> insurance;
    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}
