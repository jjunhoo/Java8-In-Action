## 1. 인터페이스 Default Method 

### Default Method란 
Java 8부터 추가된 기능으로 default 키워드를 사용하여 인터페이스에 비추상적인 메소드를 구현하는 것으로 Extension Methods라고도 함

#### JAVA 8 - 인터페이스 클래스 
````JAVA 
interface Formula {
    double calculate(int a);

    // default 키워드를 통해 메소드를 인터페이스 클래스 내부에서 구현 가능
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
````

#### JAVA 8 - 인터페이스 구현 클래스
````JAVA 
// 익명 객체를 통해 calculate 메소드는 오버라이딩 하여 구현하여 생성
Formula formula = new Formula() {
    @Override
    public double calculate(int a) {
        return sqrt(a * 100);
    }
};
// 위 코드에서 오버라이딩 하였기 때문에 사용 가능
formula.calculate(100);
// 인터페이스 클래스에서 sqrt 메소드를 default 키워드를 통해 선언했기 때문에 
// 인터페이스 구현 클래스에서 sqrt 메소드를 오버라이딩 하여 구현하지 않아도 즉시 사용 가능
formula.sqrt(16);
````
````
[실행 결과]
100.0
4.0
````


## 2. 람다식

### 람다식이란
람다식(Lambda expression)은 메소드를 하나의 '식(expression)'으로 표현한 것으로 메서드를 람다식으로 표현하면 메소드의 이름과 반환값이 없어지므로, 람다식을 '익명 함수(anonymous function)'이라고도 함


#### 일반 메소드 사용법
```` JAVA
[반환 타입] 메소드명 (매개변수 선언) {
    함수 몸체 
}

int min(int x, int y) {
    return x < y ? x : y;
}
````

#### 람다식 사용법
```` JAVA
(매개변수 선언) -> {
    함수 몸체 
}

(x, y) -> {
    x < y ? x : y;
}
````

### 람다식 사용 시 유의사항
1. 매개변수의 타입을 추론할 수 있는 경우, 타입을 생략 가능
2. 매개변수가 하나인 경우, 괄호() 생략 가능
3. 함수의 몸체가 하나의 명령문만으로 이루어진 경우, 중괄호{} 생략 가능 (이때 세미콜론(;)은 붙이지 않음)
4. 함수의 몸체가 하나의 return 문으로만 이루어진 경우, 중괄호{} 생략 불가
5. return문 대신 표현식을 사용할 수 있으며, 이때 반환값은 표현식의 결괏값 (이때 세미콜론(;)은 붙이지 않음)

#### 함수형 인터페이스(functional interface)
```` JAVA
// 람다 표현식을 하나의 변수에 대입할 때, 사용하는 참조 변수의 타입을 함수형 인터페이스라고 함
참조변수의타입  참조변수의이름 = 람다 표현식
````

### 함수형 인터페이스 명시 
```` JAVA
// 1. 함수형 인터페이스는 추상 클래스와는 달리, 단 하나의 추상 메소드만을 가질 수 있음
// 2. 아래와 같은 어노테이션을 사용하여 함수형 인터페이스 명시
// 3. 함수형 인터페이스에 두 개 이상의 메소드가 선언되면 오류 발생
@FunctionalInterface
````

### 함수형 인터페이스 예제
```` JAVA
@FunctionalInterface
interface Calc { // 함수형 인터페이스의 선언
    public int min(int x, int y);
}
public class Lambda {
public static void main(String[] args){
        Calc minNum = (x, y) -> x < y ? x : y; // 추상 메소드의 구현
        System.out.println(minNum.min(3, 4));  // 함수형 인터페이스의 사용
    }
}
````


## 3. 스트림

### 스트림이란
스트림은 반복자로써 컬렉션(배열 포함)의 요소를 하나씩 참조해서 람다식으로 처리할 수 있는 반복자

#### 자바 8 이전 코드 
````JAVA 
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()) {
   String name = iterator.next();
   System.out.println(name);
}
````
````
[실행 결과]
김두한 구마적 신마적 
````

#### 자바 8 이후 코드
````JAVA
List<String> list = Arrays.asList("김두한", "구마적", "신마적");
Stream<String> stream = list.stream();
stream.forEach(name -> System.out.println(name));
````
````
[실행 결과]
김두한 구마적 신마적 
````

### 스트림 특징
1. 람다식으로 요소 처리 코드를 제공
- 스트림이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가짐
- 매개변수로 람다식 또는 메소드 참조를 대입할 수 있음

````JAVA
public static void main(String[] args) {
  List<Student> list = Array.asList(
    new Student("김두한",92),
    new Student("구마적",90),
    new Student("신마적",95)
  );
  
  Stream<Student> stream = list.stream();
  stream.forEach(s -> {
    String name = s.getName();
    int score = s.getScore();
    System.out.println(name + "-" + score);
  });
}
````
````
[실행 결과]
김두한-92
구마적-90
신마적-95
````

2. 내부 반복자를 사용하므로 병렬 처리가 쉽다.
- 외부 반복자(external iterator)
  > 개발자가 코드로 직접 컬렉션 요소를 반복해서 요청하고 가져오는 코드 패턴
- 내부 반복자(internal iterator)
  > 컬렉션 내부에서 요소들을 반복시키고 개발자는 요소당 처리해야할 코드만 제공하는 코드 패턴
- 내부 반복자의 이점
  > 개발자는 요소 처리 코드에만 집중 
  > 멀티 코어 CPU를 최대한 활용하기 위해 요소들을 분배시켜 병렬 처리 작업을 할 수 있음
- 병렬(parallel)처리
  > 한 가지 작업을 서브 작업으로 나누고, 서브 작업들을 분리된 스레드에서 병렬적으로 처리한 후, 서브 작업들의 결과들을 최종 결합하는 방법
  > **자바는 ForkJoinPool 프레임워크를 이용해서 병렬 처리를 함** 
- 병렬 처리 코드 예

````JAVA
public class ParallelEx {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("김두한","구마적","신마적","람다식","병렬처리");

    Stream<String> stream = list.stream();
    stream.forEach(ParallelEx::print);

    Stream<String> parallelStream = list.parallelStream();
    parallelStream.forEach(ParallelEx::print);
  }

  public static void print(String string) {
    System.out.println(string + " : " + Thread.currentThread().getName());
  }
}
````
````
[실행 결과]
김두한 : main
구마적 : main
신마적 : main
람다식 : main
병렬처리 : main
신마적 : main
람다식 : ForkJoinPool.commonPool-worker-2
병렬처리 : main
구마적 : ForkJoinPool.commonPool-worker-1
김두한 : ForkJoinPool.commonPool-worker-2
````

### 병렬 처리 (Parallel Operation)
1. 멀티 코어 CPU 환경에서 하나의 작업을 분할해서 각각의 코어가 병렬적으로 처리
- 병렬 처리의 목적 : 작업 처리 시간을 줄임
- 자바8부터 병렬 스트림을 제공하므로 컬렉션(배열)의 전체 요소 처리 시간을 줄여줌

2. 동시성(Concurrency)과 병렬성(Parallelism)
- 동시성 : 멀티 스레드 환경에서 스레드가 번갈아 가며 실행하는 성징(싱글 코어 CPU)
- 병렬성 : 멀티 스레드 환경에서 코어들이 스레드를 병렬적으로 실행하는 성질(멀티 코어 CPU)

3. 병렬성(Parallelism) 구분
- 데이터 병렬성
  > 데이터 병렬성은 한 작업 내에 있는 전체 데이터를 쪼개어 서브 데이터들로 만들고, 이 서브 데이터들을 
    병렬 처리해서 작업을 빨리 끝내는 것
- 작업 병렬성
  > 서로 다른 작업을 병렬 처리하는 것
  > 작업 병렬성의 대표적인 예는 웹서버(Web Server)로 웹서버는 각각의 브라우저에서 요청한 내용을 
    개별 스레드에서 병렬로 처리

4. 병렬 스트림은 데이터 병렬성을 구현한 것
- 멀티 코어의 수만큼 대용량 요소를 서브 요소들로 나누고, 각각의 서브 요소들을 분리된 스레드에서 
  병렬 처리 시킴
- 예를 들어 쿼드 코어 CPU일 경우, 4개의 서브 요소들로 나누어 4개의 스레드가 각각의 서브 요소들을 병렬 처리함
- 병렬 스트림은 내부적으로 포크 조인(ForkJoin) 프레임워크를 이용


### 포크 조인(ForkJoin) 프레임워크
* **포크 조인 프레임워크 동작 방식**

- 포크 단계
  > 데이터를 서브 데이터로 반복적으로 분리
  > 서브 데이터를 멀티 코어에서 병렬로 처리
  
- 조인 단계
  > 서브 결과를 결합해서 최종 결과 도출
  
- 병렬 처리 스트림은 내부적으로 서브 요소를 나누는 알고리즘을 통해 요소를 분할함


 <img src="https://i.imgur.com/TEzxTDp.png" width="90%"></img>
 

### 병렬 처리에 영향을 미치는 3가지 요인
1. 요소의 수와 요소당 처리 시간
- 컬렉션에 요소의 수가 적고 요소당 처리 시간이 짧으면, 병렬 처리는 스레드풀, 스레드 생성이라는 추가적인 비용이 발생하기 때문에 
  순차 처리가 오히려 병렬 처리보다 빠를 수 있음
2. 스트림 소스의 종류
- ArrayList, 배열은 랜덤 엑세스를 지원(인덱스로 접근)하기 때문에 포크 단계에서 쉽게 요소를 분리할 수 있어 병렬 처리 시간이 절약되지만,
  HashSet, TreeSet은 요소를 분리하기 쉽지 않고, LinkedList는 랜덤 엑세스를 지원하지 않아 링크를 따라가야 하므로 역시 요소를 분리하기 
  쉽지 않으며 BufferedReader.lines()은 전체 요소의 수를 알기 어렵기 때문에 포크 단계에서 서브 요소로 나누기 어려움. 따라서 HashSet,
  TreeSet, LinkedList, BufferedReader.lines는 ArrayList, 배열 보다는 상대적으로 병렬 처리가 느림
3. 코어의 수 
- 싱글 코어 CPU일 경우에는 순차 처리가 빠르지만, 병렬 처리를 할 경우 스레드의 수만 증가하고 번갈아 가면서 스케줄링을 해야하므로 좋지 못한
  결과를 줌. 코어의 수가 많으면 많을수록 병렬 작업 처리 속도는 빨라짐 
  

 
