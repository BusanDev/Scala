# CHAPTER 5. 일급 함수

- 함수형 프로그래밍의 핵심 가치 중 하나 : 함수는 일급(first-class) 객체이어야 함
  - 함수가 데이터 타입처럼 사용될 수 있다는 의미
  - 리터럴 형태로 생성 가능
  - 값/변수/데이터 구조처럼 컨테이너에 저장 가능
  - 다른 함수에 매개변수로 사용되거나 다른 함수의 반환 값으로 사용될 수 있음
    - 고차 함수(higher-order function)
    - Ex. map(), reduce() : 계산 작업을 일련의 분산 노드에 매핑, 그 결과를 의미있는 사이즈로 다시 줄임으로써 대규모 연산 처리 문제를 다룸
    - 데이터 처리 시 고차 함수를 사용하는 이점 중 하나 : 
      - 데이터를 실제 처리하는 **방법(how)**은 고차 함수를 가지고 있는 프레임워크의 세부 구현사항으로 남겨둔다는 점임
      - 호출자는 **무엇(what)**이 되어야 하는지를 지정하고, 실제 논리 흐름을 처리하는 것은 고차 함수에 맡길 수 있음 > 선언형 프로그래밍
- 선언형 프로그래밍(declarative programming)
  - 고차 함수 또는 다른 메커니즘은 작업을 선언하는 데 사용되며 그 작업을 직접 수행하지 않음
- 명령형 프로그래밍(imperative programming)
  - 연산의 논리 흐름이 명시적으로 기술됨
- 스칼라는 일급 함수, 고차 함수, 선언형 프로그래밍의 사용을 전적으로 지원함




### 함수 타입과 값

------

- 함수의 **타입(type)**

  - 함수의 입력 타입과 반환값 타입의 단순 그룹

  - 입력 타입으로부터 출력 타입으로의 방향을 나타내는 화살표로 배열함

  - 구문: 함수 타입

    ```
    ([<타입>, …]) => <타입>
    ```

  - 함수 시그니처(function signature)는 이름, 입력, 출력이므로 함수의 타입은 입력과 출력이어야 함

  - 함수 타입 이용 예제

    ```scala
    scala> def double(x: Int): Int = x * 2
    double: (x: Int)Int

    scala> double(5)
    res0: Int = 10

    scala> val myDouble: (Int) => Int = double ---------------------------- 1
    										   
    myDouble: Int => Int = $$Lambda$1120/1495001258@61808ecd

    scala> myDouble(5) ---------------------------------------------------- 2
    res1: Int = 10

    scala> val myDoubleCopy = myDouble
    myDoubleCopy: Int => Int = $$Lambda$1120/1495001258@61808ecd

    scala> myDoubleCopy(5) ------------------------------------------------ 3
    res2: Int = 10
    ```

    > 1. myDouble은 값이지만, 다른 값들과 달리 호출 가능
    > 2. myDouble을 함수로 호출하는 것은 double을 호출하는 것과 같은 결과
    > 3. 함숫값을 새로운 값에 할당하는 것은 다른 값들과 마찬가지로 가능
    >
    > - 'myDouble' 값의 명시적 타입은 함수 호출이 아닌 함숫값으로 식별하는 데 필요함
    > - 함수로 할당된 함숫값을 정의하는 다른 방법은 와일드카드 연산자 '_'를 사용하는 것임


- 단일 매개변수를 갖는 함수 타입은 괄호 생략 가능
  - Ex. 단일 정수를 취하고 정수를 반환하는 함수의 타입 : Int => Int

- 구문: 와일드카드 연산자로 함수 할당하기

  ```
  val <식별자> = <함수명> _
  ```

  - 예제

    ```Scala
    scala> def double(x: Int): Int = x * 2
    double: (x: Int)Int

    scala> val myDouble = double _
    myDouble: Int => Int = $$Lambda$1102/1923626523@36478bce

    scala> val amount = myDouble(20)
    amount: Int = 40
    ```

    > - myDouble의 명시적 함수 타입은 필요하지 않음
    > - 언더스코어(_)는 미래의 함수 호출에 대한 자리표시자 역할을 하며, myDouble에 저장할 수 있는 함숫값을 반환함


- 다중 입력값을 가지는 함수 타입

  - 입력 타입을 괄호로 명시적으로 묶음
  - 매개변수 이름이 없는 함수 정의(function definition)의 형태를 띔

  ```scala
  scala> def max(a: Int, b: Int) = if (a > b) a else b
  max: (a: Int, b: Int)Int

  scala> val maximize: (Int, Int) => Int = max
  maximize: (Int, Int) => Int = $$Lambda$1217/1836406440@48860139

  scala> maximize(50, 30)
  res0: Int = 50
  ```

- 입력값이 없는 함수 타입

  - 빈 괄호는 값이 없음을 나타내는 Unit 타입의 리터럴 표현이기도 함

  ```scala
  scala> def logStart() = "=" * 50 + "\nStarting NOW\n" + "=" * 50
  logStart: ()String

  scala> val start: () => String = logStart
  <console>:12: warning: Eta-expansion of zero-argument method values is deprecated. Did you intend to write logStart()?
         val start: () => String = logStart
                                   ^
  start: () => String = $$Lambda$1255/300648514@4ed7db72

  scala> println( start() )
  ==================================================
  Starting NOW
  ==================================================
  ```




### 고차 함수

------

- 고차(higher-order) 함수 : 입력 매개변수나 반환값으로 함수 타입의 값을 가지는 함수
- 고차 함수의 예제
  - String에서 동작하지만 입력 String이 널(null)이 아닌 경우에만 동작하는 다른 함수를 호출
  - JVM의 NullPointerException 방지
  - 기존 함수를 고차 함수에 매개변수로 전달하는 방법 보여줌 

```scala
scala> def safeStringOp(s: String, f: String => String) = {
     |   if (s != null) f(s) else s
     | }
safeStringOp: (s: String, f: String => String)String

scala> def reverser(s: String) = s.reverse
reverser: (s: String)String

scala> safeStringOp(null, reverser)
res0: String = null

scala> safeStringOp("Ready", reverser)
res1: String = ydaeR
```

> - 'null'로 호출하면 안전하게 같은 값을 반환
> - 유효한 String으로 호출하면 입력 값을 거꾸로 한 값을 반환함



### 함수 리터럴

------

- 함수 리터럴(function literal)

  - 실제 동작하지만 이름이 없는 함수

  ```scala
  scala> val doubler = (x: Int) => x * 2

  doubler: Int => Int = $$Lambda$1076/1769827821@5d05f453

  scala> val doubled = doubler(22)
  doubled: Int = 44
  ```

  > - 위 예제에서 함수 리터럴은 구문 (x: Int) => x * 2
  >   - 이 구문은 타입을 가진 입력 인수(x)와 함수 본문(x * 2)을 정의함

  - 함숫값과 변수에 저장되거나 고차 함수 호출의 부분으로 정의될 수 있음

  - 함수 타입을 받는 모든 곳에 함수 리터럴을 표현할 수 있음

  - 함수 리터럴 이름

    - 익명 함수(Anonymous function)

      : 함수 리터럴에 대한 스칼라 언어의 공식적인 이름

    - 람다 표현식(Lambda expression)

      : 수학에서의 람다 계산(lambda calculus) 구문(ex. x -> x * 2)에서 유래된 용어로 C#과 자바 8에서 사용함

    - 람다(Lambda)

      : 람다 표현식의 축약형

    - function0, function1, function2, ...

      : 함수 리터럴에 대한 스칼라 컴파일러의 용어로 입력 인수의 개수를 기반으로 함

  > 왜 그냥 익명 함수라 부르지 않을까?
  >
  > **익명 함수**는 이름이 없는 데에만 초점, 필자는 함수 본문의 모든 로직이 인라인으로 기술된다는 점을 명확히 보여주는 **함수 리터럴**이라는 용어를 선호함

  - 구문: 함수 리터럴 작성하기

  ```
  ([<식별자>: <타입>, ... ]) => <표현식>
  ```

  - 함숫값 정의, 이를 새로운 함수 리터럴에 할당하는 예제

  ```Scala
  scala> val greeter = (name: String) => s"Hello, $name"
  greeter: String => String = $$Lambda$1110/962058379@1152900

  scala> val hi = greeter("World")
  hi: String = Hello, World
  ```

  > 함수 리터럴은 근본적으로 매개변수화된 표현식

  - 함수 리터럴과 함수 할당 비교 예제

  ```scala
  scala> def max(a: Int, b: Int) = if (a > b) a else b ------------------------ 1
  max: (a: Int, b: Int)Int

  scala> val maximize: (Int, Int) => Int = max -------------------------------- 2
  maximize: (Int, Int) => Int = $$Lambda$1116/872877010@1e7d3d87

  scala> val maximize = (a: Int, b: Int) => if (a > b) a else b --------------- 3
  maximize: (Int, Int) => Int = $$Lambda$1117/1328382325@44618fe6

  scala> maximize(84, 96)
  res0: Int = 96
  ```

  > 1. 원본 함수 max()
  > 2. 함숫값에 할당됨
  > 3. 함수 리터럴로 재정의됨

  	- 어떤 인수도 취하지 않는 함수 리터럴 정의 예제

  ```scala
  scala> def logStart() = "=" * 50 + "\nStarting NOW\n" + "=" * 50
  logStart: ()String

  scala> val start = () => "=" * 50 + "\nStarting NOW\n" + "=" * 50
  start: () => String = $$Lambda$1143/1386265672@28941a68

  scala> println( start() )
  ==================================================
  Starting NOW
  ==================================================
  ```

  - 고차 함수 호출 내부에 정의되는 함수 리터럴 예제

  ```scala
  scala> def safeStringOp(s: String, f: String => String) = {
       |   if (s != null) f(s) else s
       | }
  safeStringOp: (s: String, f: String => String)String

  scala> safeStringOp(null, (s: String) => s.reverse)
  res2: String = null

  scala> safeStringOp("Ready", (s: String) => s.reverse)
  res3: String = ydaeR
  ```

  > - 함수 safeStringOp는 f라는 이름의 함숫값을 매개변수로 받고 조건에 따라 이 함숫값을 호출 함

  - 위 예제에서 함수 매개변수 'f'의 타입은 String => String임
  - 이미 정의한 이 타입으로 함수 리터럴에서 명시적 타입을 제거할 수 있음
  - 명시적 타입을 제거한다는 것은 우리가 함수 리터럴에서 괄호를 제거할 수 있다는 것을 의미


  - 좀 더 단순한 구문을 사용하는 함수 리터럴로 'safeStringOp' 함수 다시 호출하는 예제

  ```scala
  scala> safeStringOp(null, s => s.reverse)
  res4: String = null

  scala> safeStringOp("Ready", s => s.reverse)
  res5: String = ydaeR
  ```

  > - 명시적 타입과 괄호를 제거한 함수 리터럴에는 함수의 기본적인 본질만 남게 됨
  > - 함수 리터럴은 입력 매개변수를 받아 그 매개변수로 연산을 수행한 결괏값을 반환함

- 함수 리터럴은 매우 간단한 함수의 표현식이지만, 스칼라는 **자리표시자 구문(placeholder syntax)**으로 더 간단한 표현식을 지원함




### 자리표시자 구문

------

- 자리표시자 구문(placeholder syntax) : 함수 리터럴의 축약형

- 지정된 매개변수를 와일드카드 연산자(_)로 대체한 형태를 가짐

- 이 구문은 함수의 명시적 타입이 리터럴 외부에 지정되어 있고, 매개변수가 한 번 이상 사용되지 않는 경우에 사용

- 지정된 매개변수 자리에 와일드카드 연산자를 사용하여 두 배 함수 리터럴을 만드는 예제

  ```scala
  scala> val doubler: Int => Int = _ * 2
  doubler: Int => Int = $$Lambda$1253/1695501686@ee9b7ac
  ```

  > 자리표시자 구문은 입력 매개변수가 한 번만 사용되고, 리터럴의 타입이 외부에(값(value)에) 명시적으로 정의되어 있기 때문에 유효함

- 자리표시자 구문 이용하여 'safeStringOp' 예제 호출

  ```scala
  scala> def safeStringOp(s: String, f: String => String) = {
       |   if (s != null) f(s) else s
       | }
  safeStringOp: (s: String, f: String => String)String

  scala> safeStringOp(null, _.reverse)
  res6: String = null

  scala> safeStringOp("Ready", _.reverse)
  res7: String = ydaeR
  ```

  > 함수 리터럴 본문은 기능적으로 s => s.reverse와 같지만, 자리표시자 구문으로 단순화 함
  >
  > 입력 매개변수 s에 대한 참조는 함수에의 첫 번째 입력 매개변수를 나타내는 와일드카드(_)로 대체됨
  >
  > 근본적으로 와일드카드는 단일 String 입력 매개변수임

- 두 개의 자리표시자를 가진 예제

  ```scala
  scala> def combination(x: Int, y: Int, f: (Int, Int) => Int) = f(x, y)
  combination: (x: Int, y: Int, f: (Int, Int) => Int)Int

  scala> combination(23, 12, _ * _)
  res8: Int = 276
  ```

  > 두 개의 자리표시자를 사용하는 것은 구문을 더 추상적으로 만듦
  >
  > 자리표시자가 입력 매개변수(x와 y 각각)를 위치적으로 대체함
  >
  > 자리표시자의 개수는 입력 인수의 개수와 일치해야 함

- 자리표시자 개수 세 개의 예제

  ```scala
  scala> def tripleOp(a: Int, b: Int, c: Int, f: (Int, Int, Int) => Int) = f(a, b, c)
  tripleOp: (a: Int, b: Int, c: Int, f: (Int, Int, Int) => Int)Int

  scala> tripleOp(2, 3, 4, _ * _ + _)
  res1: Int = 10
  ```

  > tripleOp 함수는 네 개의 매개변수 취함
  >
  > 실제 함수 본문은 매개변수 리스트보다 훨씬 짧으며, 함수를 입력값에 적용함(?)

- 위의 tripleOp 함수를 두 개의 타입 매개변수(하나는 공통 입력 타입으로, 다른 하나는 반환값 타입으로)를 이용하여 재정의하는 예제

  - tripleOp 함수를 우리가 선택한 어떤 입력 값이나 익명 함수(그 익명 함수가 세 개의 입력값을 취하는 한에는)로 호출할 수 있도록 유연성을 제공함

  ```scala
  scala> def tripleOp[A,B](a: A, b: A, c: A, f: (A, A, A) => B) = f(a, b, c)
  tripleOp: [A, B](a: A, b: A, c: A, f: (A, A, A) => B)B

  scala> tripleOp[Int,Int](23, 92, 14, _ * _ + _)
  res2: Int = 2130

  scala> tripleOp[Int,Double](23, 92, 14, 1.0 * _ / _ / _)
  res3: Double = 0.017857142857142856

  scala> tripleOp[Int,Boolean](93, 92, 14, _ > _ + _)
  res4: Boolean = false
  ```

- 자리표시자 구문은 특히 데이터 구조와 컬렉션으로 작업할 때 유용함

- 수많은 정렬, 필터링, 그 외 다른 데이터 구조 메소드는 일급 함수를 사용하는 경향이 있으며, 자리표시자 구문은 이 메소드들을 호출하는 데 필요한 부가적인 코드의 양을 줄여줌




### 부분 적용 함수와 커링

------

- 함수(일반 함수와 고차 함수 모두)를 호출하려면 전형적으로 호출문 내에 함수의 매개변수가 모두 지정되어 있어야 함(기본 매개변수 값이 있는 함수 제외)

- 값이 다른 숫자의 인수(factor)인지를 검사하는 두 개의 매개변수를 가지는 하나의 함수 예제

  ```scala
  scala> def factorOf(x: Int, y: Int) = y % x == 0
  factorOf: (x: Int, y: Int)Boolean
  ```

  - 여기서 어떤 매개변수도 유지하지 않고 함수를 호출하는 손쉬운 방법을 원한다면 와일드카드 연산자(_)를 사용 가능함

  ```scala
  scala> val f = factorOf _
  f: (Int, Int) => Boolean = $$Lambda$1189/348026124@58882a93

  scala> val x = f(7, 20)
  x: Boolean = false
  ```

  - 매개변수 중 일부를 유지하기를 원하는 경우, 매개변수 중 하나의 자리를 대신하는 와일드 카드 연산자를 사용하여 그 함수를 부분적으로 적용(partially apply)할 수 있음

    - 이 경우 와일드카드 연산자는 명시적 타입을 필요로 하는데, 이 명시적 타입이 선언된 입력 타입으로 함숫값을 생성하는 데 사용되기 때문임

    ```scala
    scala> val multipleOf3 = factorOf(3, _: Int)
    multipleOf3: Int => Boolean = $$Lambda$1203/1660124157@1818d00b

    scala> val y = multipleOf3(78)
    y: Boolean = true
    ```

    > factorOf() 함수의 매개변수 중 일부를 포함하므로, 새로운 함숫값 multipleOf3은 부분 적용 함수임

  - 다중 매개변수 목록을 가진 함수

    - 하나의 매개변수 목록을 적용 매개변수와 미적용 매개변수로 나누고, 한 목록의 매개변수를 적용하고 다른 목록은 적용하지 않는 것 : 함수를 **커링(currying)**한다고 함

    ```scala
    scala> def factorOf(x: Int)(y: Int) = y % x == 0
    factorOf: (x: Int)(y: Int)Boolean

    scala> val isEven = factorOf(2) _
    isEven: Int => Boolean = $$Lambda$1215/1766705563@6d71f296

    scala> val z = isEven(32)
    z: Boolean = true
    ```

    > 함수 타입 관점에서 다중 매개변수 목록을 가지는 함수는 다중 함수의 체인(chain)으로 간주함
    >
    > 각 분리된 매개변수 목록은 별도의 함수 호출로 간주함

  - 함수 def factorOf(x: Int, y: Int)의 함수 타입은 (Int, Int) => Boolean 

  - 함수 def factorOf(x: Int)(y: Int)의 함수 타입은 Int => Int => Boolean

    - 이 함수를 커링하면, 함수 타입은 함수 체인 중 두 번째 함수인 Int => Boolean이 됨
    - 위 예제의 함숫값 isEven은 체인으로 연결된 함수 중 첫 번째 부분을 정숫값 2로 커링함





### 이름에 의한 호출 매개변수

------

- **이름에 의한(by-name)** 호출 매개변수

  - 값을 반환하는 함수를 취할 수 있음
  - 값, 함수 중 어떤 것으로도 호출 가능함
  - 구문: 이름에 의한 호출 매개변수 지정하기

  ```
  <식별자>: => <타입>
  ```

  - 함수 내에서 이름에 의한 매개변수 사용 시, 함수가 전달되는 경우 그 함수를 사용할 때마다 해당 함수 호출하게 됨

- 이름에 의한 매개변수의 이점

  - 유연성이 높아짐 : 값을 사용할 수 있을 때, 함수를 사용해야 할 때 이용될 수 있음
  - 이름에 의한 매개변수에 전달된 함수는 그 매개변수에 접근하지 않으면 호출되지 않으므로 필요한 경우에 비용이 따르는 함수 호출을 피할 수 있음

- 이름에 의한 매개변수를 가지는 함수 호출 예제

```scala
scala> def doubles(x: => Int) = {
     |   println("Now doubling " + x) ---------------------------------- 1
     |   x * 2
     | }
doubles: (x: => Int)Int

scala> doubles(5) ------------------------------------------------------ 2
Now doubling 5
res1: Int = 10

scala> def f(i: Int) = { println(s"Hello from f($i)"); i }
f: (i: Int)Int

scala> doubles( f(8) ) ------------------------------------------------- 3
Hello from f(8)
Now doubling 8
Hello from f(8) -------------------------------------------------------- 4
res2: Int = 16
```

> 1. 이름에 의한 매개변수 x는 일반적인 값에 의한 매개변수(by-value parameter)와 같이 접근
> 2. Doubles 메소드를 일반값으로 호출
> 3. 함숫값으로 호출하면 함숫값이 doubles 메소드 내부에서 호출됨
> 4. Doubles 메소드가 x 매개변수를 두 번 참조하므로 'Hello' 메시지가 두 번 호출됨



### 부분 함수

------

- 완전 함수(total function)

  - 입력 매개변수의 타입을 만족하는 모든 가능한 값을 적절하게 지원하는 함수

  - 예제

    ```scala
    def double(x: Int) = x * 2
    ```

    - double() 함수가 처리하지 못하는 입력값 x가 존재하지 않으므로 완전 함수임

- 부분 함수(partial function)

  - 자신의 입력 데이터 중 일부에만 적용할 수 있는 함수
  - 예
    - 입력 숫자의 제곱근을 반환하는 함수는 입력 숫자가 음수인 경우 동작 불가능
    - 주어진 수로 나누는 함수는 그 수가 0인 경우 적용 불가능

- 스칼라의 부분함수

  - 일련의 case 패턴을 자신의 입력값에 적용하는 함수 리터럴
  - 입력값이 주어진 패턴 중 최소 하나는 일치할 것을 요구함
  - Case 패턴 중 하나도 만족시키지 못하는 데이터로 부분 함수 중 하나를 호출하면 스칼라 에러 발생

- 부분 함수와 부분 적용 함수의 차이

  - 부분 함수
    - 완전 함수와 반대되는 개념
    - 모든 가능한 입력값 중 일부만 받아들임
  - 부분 적용 함수
    - 부분적으로 호출
    - 나머지 부분도 미래에 완전히 호출되는 일반 함수


```scala
scala> val statusHandler: Int => String = {
     | case 200 => "Okay"
     | case 400 => "Your Error"
     | case 500 => "Our error"
     | }
statusHandler: Int => String = $$Lambda$1132/709931821@36c7cbe1

scala> statusHandler(200)
res0: String = Okay

scala> statusHandler(400)
res1: String = Your Error

scala> statusHandler(401) // case 패턴에 맞지 않는 정수로 호출 시 MatchError 발생함
scala.MatchError: 401 (of class java.lang.Integer)
  at .$anonfun$statusHandler$1(<console>:11)
  at .$anonfun$statusHandler$1$adapted(<console>:11)
  ... 28 elided
```

> - MatchError를 방지하는 한 가지 방법으로 모든 다른 에러를 잡는 와일드카드 패턴을 마지막에 추가하는 것이지만, 이 경우라면 '부분 함수'라는 용어를 사용할 수 없을 것임
> - 부분 함수가 컬렉션과 패턴 매칭으로 작업할 때 더 유용하다는 것을 알게 될 것임
> - 예를 들어, 주어진 부분 함수가 허용하는 컬렉션의 모든 아이템을 '수집(collect)'할 수 있음




### 함수 리터럴 블록으로 고차 함수 호출하기

------

- 57쪽의 '표현식 블록을 이용한 함수 호출'을 고차 함수와 함께 재사용하여 함수 리터럴 블록을 괄호 대신 또는 괄호에 추가하여 고차 함수 호출 가능
- 이름과 대규모 표현식 블록으로 호출된 함수는 함수 리터럴로 취하게 되며, 이 함수 리터럴은 한 번도 호출되지 않거나 혹은 여러 번 호출 가능함, 이 구문의 보편적인 용도는 표현식 블록으로 유틸리티 함수를 호출하는 것임
- 일반 함수 리터럴과 함께 사용되는 'safeStringOp' 함수 예제


```scala
scala> def safeStringOp(s: String, f: String => String) = {
     |   if (s != null) f(s) else s
     | }
safeStringOp: (s: String, f: String => String)String

scala> val uuid = java.util.UUID.randomUUID.toString --------------------------- 1
uuid: String = 329e76ec-9696-4364-b318-62eb3703bd01

scala> val timedUUID = safeStringOp(uuid, { s =>
     |   val now = System.currentTimeMillis ------------------------------------ 2
     |   val timed = s.take(24) + now ------------------------------------------ 3
     |   timed.toUpperCase
     | })
timedUUID: String = 329E76EC-9696-4364-B318-1519906275263
```

> 1. 스칼라에서 접근할 수 있는 자바의 java.util 패키지의 UUID 유틸리티
> 2. System.currentTimeMillis는 1/1000초(1ms) 단위로 에포크 시간(epoch time, GMT 기준 1970년 1월 1일을 기점으로 경과 시간)을 제공하며, 타임스탬프를 생성하는 데 유용함
> 3. take(x) 메소드는 String으로부터 처음 x개의 항목을 반환하며, 이 경우 UUID의 처음 네 부분을 반환함
>
> - 이 예제에서 여러 줄의 함수 리터럴이 값 매개변수와 함께 함수에 전달됨
> - 제대로 동작하지만, 같은 괄호 안에 포함시키는 것은 다루기 불편함

- 'safeStringOp'의 매개변수를 별도 그룹으로 구분(64쪽 '매개변수 그룹' 참조)하여 위의 문제를 개선한 예제
  - 함수 타입을 포함하는 두 번째 매개변수 그룹은 표현식 블록 구문으로 호출 가능함

```scala
scala> def safeStringOp(s: String)(f: String => String) = {
     |   if (s != null) f(s) else s
     | }
safeStringOp: (s: String)(f: String => String)String

scala> val timedUUID = safeStringOp(uuid) { s =>
     |   val now = System.currentTimeMillis
     |   val timed = s.take(24) + now
     |   timed.toUpperCase
     | }
timedUUID: String = 329E76EC-9696-4364-B318-1519906894378
```

> 괄호로 값 매개변수를, 그리고 독립된 함수 리터럴 블록으로 함수 배개변수를 전달함으로써 safeStringOp 호출이 깔끔해짐

- 이름에 의한 매개변수 하나를 취하는 함수 예제

```scala
scala> def timer[A](f: => A): A = { ------------------------------------------- 1
     |   def now = System.currentTimeMillis ----------------------------------- 2
     |   val start = now; val a = f; val end = now
     |   println(s"Executed in ${end - start} ms")
     |   a
     | }
timer: [A](f: => A)A

scala> val veryRandomAmount = timer { ----------------------------------------- 3
     |   util.Random.setSeed(System.currentTimeMillis)
     |   for (i <- 1 to 100000) util.Random.nextDouble ------------------------ 4
     |   util.Random.nextDouble
     | }
Executed in 28 ms
veryRandomAmount: Double = 0.38106401650672084
```

> 1. 타입 매개변수 'A'는 이름에 의한 매개변수 'f'의 반환 타입이 'timer' 함수의 반환 타입이 되도록 함으로써 'timer' 함수로 감싼 코드의 영향도를 줄임
> 2. 내부의 중첩된 함수는 미학적인 이유로 우리가 1/1000초 단위의 현재 시간을 간결하게 추출할 수 있도록 해줌
> 3. 고차 함수의 표현식 블록 구문을 가장 간단한 형태인 함수 이름과 블록으로 줄임, 괄호 안의 코드를 표현식 블록으로, 또는 함수 리터럴 블록으로, 또는 'timer' 함수로 싸인(wrapped) 일반 코드로 볼 수 있음
> 4. 100,000개의 무작위 부동 소수점 숫자를 생성하고 폐기함, 이것은 시간 측정 데모를 보여주기 위해 시간을 써야 할 때 유용하지만, 실제 상품화될 코드에 사용하는 것은 권하지 않음
>
> - timer 함수는 개별 단위의 코드를 감싸기 위해 사용되지만, 기존 코드 베이스에 통합될 수도 있음
> - 이 timer 함수로 함수의 마지막 부분을 감싸서 반드시 함수의 반환 값이 코드 블록으로부터 timer를 통해 전달되고, 그 함수에 의해 반환되도록 하면 그 함수 성능을 측정할 수 있음

- 이 방법으로 어떠한 코드 블록도 유틸리티로 감쌀 수 있는 함수는 고차 함수를 '표현식-블록' 형태로 호출하는 주요 이점임
- 이 호출 형태를 사용하는 다른 예
  - 데이터베이스 트랜잭션 관리에서 고차 함수는 세션을 열고, 함수 매개변수를 호출하고, 커밋(commit) 또는 롤백(rollback)하여 트랜잭션을 종료함
  - 오류를 내지 않을 때까지 횟수만큼 함수 매개변수를 호출함으로써 재시도로 예상한 오류 처리하기
  - 지역, 전역 또는 외부 값(예 : 데이터베이스 설정 또는 환경변수)에 기반하여 조건부로 함수 매개변수 호출하기




### 요약

------

- 스칼라는 함수를 고차 함수, 함수 리터럴, 함수 타입의 개념에 의해 지원받는 일급 데이터 타입으로 다룸

  ​



### 연습문제

------

1. 두 개의 정수를 취하여 더 높은 값을 반환하는 함수 리터럴을 작성하라. 그리고 세 개의 항목을 가진 정수 튜플과 이 함수를 취하여 튜플 내의 최댓값을 반환하는 고차 함수를 작성하라.

2. 라이브러리 함수 util.Random.nextInt는 무작위로 정수를 반환한다. 이를 사용하여 세 개의 정수 난수와 두 개의 주어진 정수 중 큰 수를 반환하는 함수로 'max' 함수를 호출하라. 주어진 두 개의 정수 중 작은 수를 반환하는 함수로 동일한 작업을 해보고, 매번 두 번째 정수를 반환하는 함수로도 같은 작업을 반복해보자.

3. 하나의 정수를 취하고 함수를 반환하는 고차 함수를 작성하라. 반환된 함수는 단일 정수 인수(말하자면, 'x')를 취해야 하며, x와 고차 함수에 전달된 정수의 곱을 반환한다.

4. 여러분이 다른 개발자의 코드를 검토하다가 이 함수를 보게 되었다고 가정하자.

   ```Scala
   def fzero[A](x: A)(f: A => Unit): A = { f(x); x }
   ```

   이 함수는 무엇을 수행하는가? 어떻게 이 함수를 호출할 것인지 예를 들 수 있는가?

5. 'square'라는 이름의 함수가 있는데, 이를 함숫값에 저장하고자 한다. 다음과 같이 하는 것이 맞는 방법인가? 그 밖에 어떤 방법으로 함수를 값에 저장할 수 있는가?

   ```scala
   def square(m: Double) = m * m
   val sq = square
   ```

6. 'conditional'이라는 이름의 함수를 작성하라. 이 함수는 값 x와 두 함수 p와 f를 취하고, x와 동일한 타입의 값을 반환한다. p 함수는 조건자로 값 x를 취하여 Boolean b를 반환한다. 함수 f 역시 값 x를 취하고 동일한 타입의 새로운 값을 반환한다. 여러분의 'conditional' 함수는 p(x)가 참일 때에만 f(x)를 호출해야 하며, 그 외의 경우에는 x를 반환해야 한다. 'conditional'함수는 몇 개의 타입 매개변수를 필요로 할까?

7. 3장 '연습문제'에서 'typesafe' 문제를 기억하는가? 유명한 코딩 관련 인터뷰 문제로, 1부터 100까지의 숫자를 한 줄에 하나씩 출력해야 하고, 3의 배수에서는 숫자를 단어 'type'으로, 5의 배수에서는 숫자를 'safe'로 교체해야 한다. 물론, 15의 배수는 'typesafe'를 출력해야 한다.

   6번 문제의 'conditional' 함수를 이용하여 이 문제를 해결하라.

   'conditional'의 반환 타입이 매개변수 x의 타입과 일치하지 않았다면, 여러분의 해답이 더 짧아졌을까? 이 도전에 더 잘 작동하도록 'conditional' 함수를 변경하고 실험해보자.