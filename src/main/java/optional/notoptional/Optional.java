package optional.notoptional;

public class Optional {
    // NullPointerException 발생
    public static String getCarInsuranceName(Person person) {
        return person.getCar().getInsurance().getName();
    }
    // NullPointerException 발생 예방 방법 1.
    public static String getCarInsuranceName2(Person person) {
        if (person != null) {
            Car car = person.getCar();
            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return "UnKnown";
    }
    // NullPointerException 발생 예방 방법 2.
    public static String getCarInsuranceName3(Person person) {
        // null 일 경우 즉시 return 하지만, return 출구가 4개로 늘었으며 "unknown" 중복 문자열 때문에 좋지 않은 코드
        if (person == null) {
            return "UnKnown";
        }
        Car car = person.getCar();
        if (car == null) {
            return "UnKnown";
        }
        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "UnKnown";
        }
        return insurance.getName();
    }

    /*
    [ null 때문에 발생하는 문제 ]
    1. 에러의 근원
    - NullPointerException 은 자바에서 가장 흔히 발생하는 에러이다.
    2. 코드를 어지럽힌다.
    - 때로는 중첩된 null 확인 코드를 추가해야 하므로 null 때문에 코드 가독성이 떨어진다.
    3. 아무 의미가 없다.
    - null 은 아무 의미도 표현하지 않는다. 특히 정적 형식 언어에서 값이 없음을 표현하는 방법으로는 적절하지 않다.
    4. 자바 철학에 위배된다.
    - 자바는 개발자로부터 모든 포인터를 숨겼다. 하지만 예외가 있는데 그것이 바로 null 포인터다.
    5. 형식 시스템에 구멍을 만든다.
    - null 은 무형식이며 정보를 포함하고 있지 않으므로 모든 레퍼런스 형식에 null 을 할당할 수 있다. 이런 식으로 null 이 할당되기
      시작하면서 시스템의 다른 부분으로 null 이 퍼졌을 때 애초에 null 이 어떤 의미로 사용되었는지 알 수 없다.

    [ OptionalClass ]
    - java.util.OptionalClass<T>
    - 값이 있으면 OptionalClass 클래스는 값을 감싼다.
    - 값이 없으면 OptionalClass.empty() 메서드로 OptionalClass 반환 (OptionalClass 싱글턴 인스턴스를 반환하는 정적 팩토리 메서드)
    - null 을 참조하려 하면 NullPointerException 이 발생하지만, OptionalClass.empty() 는 OptionalClass 객체

     */

    public static void main(String[] args) {
        // NullPointerException 발생 (car, insurance, name 모두 null)
        // System.out.println(OptionalClass.getCarInsuranceName(new Person()));
        
        System.out.println(Optional.getCarInsuranceName2(new Person()));
    }
}
