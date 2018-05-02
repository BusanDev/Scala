# 클래스

스칼라에서 클래스를 정의하는 코드를 간단히 확인해 보자.

```scala
def greet: String = s"Hello from $name"
```

위 코드에서 이상한점 없나요?

> 스칼라는 괄호를 이용하여 정의한 함수는 괄호가 있으나 없으니 상관이 없다. 괄호가 없으면 함수를 호출할때 괄호를 넣으면 안된다.



### 클래스 정의하기

**구문: 간단한 클래스 정의하기**

````scala
class <식별자> [extends <식별자>][{필드, 메소드, 및 클래스}]
````

- 식별자: 클래스/타입 이름
- extends 식별자: 상속



클래스는 서로 안에 **중첩** 될 수 있다.

```scala
class 중첩 {
  val a = 10;
  val b = 20;

  class 중첩2 {
    def print = println(s"a: ${a}, b: ${b}")
  }
  
  val 중첩2인스턴스 = new 중첩2
}

val 중첩인스턴스 = new 중첩
중첩인스턴스.중첩2인스턴스.print
```



**구문: 입력 매개변수로 클래스 정의하기**

```scala
class <식별자> ([val|var] <식별자>: <타입>[, ...])
        [extends <식별자>(<입력 매개변수>)]
        [{ 필드와 메소드 }]
```

책 내용

> 입력 매개변수가 있는 클래스는 프로그래머가 여러 인스턴스를 생성해야 하는 이유를 제공하는데, 각 인스턴스가 자신만의 고유 내용을 가질 수 있기 때문이다.

무슨 말인지 모르겠다. *구문* 을 보면 클래스를 정의할 때, 매개변수를 전달할 수 있다는 것이다.

```Scala
class Car(val make: String, var reserved: Boolean) {
  def reserve(r: Boolean): Unit = { reserved = r; }
  //def reserve(r: Boolean) = reserved = r
}

new Car("Toyota", false)
new Car(reserved = false, make = "Tesla") // 매개변수를 지정할 수 있다.
```



**구문: 입력 매개변수와 기본값으로 클래스 정의하기**

```scala
class <식별자> ([val|var] <식별자>: <타입> = <표현식>[,...])
        ....
```

함수에 디폴트 매개변수를 사용하는 형태로 사용가능하다.

```scala
class Car(
        val make: String,
        var reserved: Boolean = true,
        val year: Int = 2015
) {
  override def toString = s"$year $make, reserved = $reserved"
}

val a = new Car("Acura")
val l = new Car("Lexus", year = 2010)
val p = new Car(reserved = false, make = "Porsche")
```



**구문: 클래스를 타입 매개변수로 정의하기**

```scala
class <식별자> [타입 매개변수]….
```

```scala
class Singular[A](element: A) extends Traversable[A] {
  def foreach[B](f: A => B) = f(element)
}

val p = new Singular(43)
p foreach println
```

코드 설명 가능하신 분?



### 그 외의 클래스 유형

#### 추상 클래스

구문

```scala
abstract class <식별자>
```

> **추상 클래스** 는 다른 클래스들에 의해 확장되도록 설계되었으며 자신은 인스턴스를 생성할 수 없다.

```scala
abstract class Car {
  val year: Int
  val automatic: Boolean = true
  def color: String
}

//new Car()

class RedMini(val year: Int) extends Car {
  def color = "Red"
}
// year years로 변경해보자
val m: Car = new RedMini(2005)

class Mini(val year: Int, val color: String) extends Car
val redMini: Car = new Mini(2005, "Red")

println(s"Got a ${redMini.color} Mini")
```

위 코드중에 Mini 정의가 이상하지 않나요?

괄호와 매개변수가 없는 매소드와 맴버변수 사이에 어떤 차이가 있나요?



#### 익명 클래스

익명 클래스를 이용해 추상 클래스를 정의해서 사용할 수 있다. javascript와 비슷하다.

```scala
val myListener = new Listener {
  def trigger: Unit = {
    println(s"Trigger at ${new java.util.Date}")
  }
}
```



### 그 외의 필드와 메소드 유형

#### 중복 정의된 메소드

우리가 흔히 알고 있는 메소드 overload 이다.

```scala
class Printer(msg: String) {
  def print(s: String): Unit = println(s"$msg: $s")
  def print(l: Seq[String]): Unit = print(l.mkString(", "))
}
```



#### apply 메소드

**기본 메소드** 또는 **인젝터 메소드** 로 불리며, 메소드 이름 없이 호출될 수 있는 메소드이다.

```scala
val l = List('a', 'b', 'c')
l(1)
```



#### 지연값

**지연값(lazy value)** 는 자신이 처음 인스턴스화될때 생성된다.

```Scala
class RandomPoint{
  val x = { println("creating x"); util.Random.nextInt }
  lazy val y = { println("now y"); util.Random.nextInt }
}
```



### 패키징

패키지는 스칼라(자바)의 코드 체계를 위한 시스템이다. 점으로 구분된 경로를 사용하며, 코드 가장 위에 **package** 키워드와 함께 해당 파일의 모든 클래스가 포함되는 패키지를 선언한다.

**구문: 스칼라 파일에 대해 패키지 정의하기**

```scala
package <식별자>
```



#### 패키징된 클래스에 접근하기

패키징된 클래스는 점으로 구분된 전체 패키지 경로와 클래스 이름으로 접근할 수 있다.

```scala
val d = new java.util.Date
```



**구문: 패키징된 클래스 임포트하기**

```scala
import <패키지>.<클래스>
```



**구문: 전체 패키지 임포트 하기**

```scala
import <패키지>.<패키지>._
```

java 의 `*` 과 동일하다.



**구문: 임포트 그룹 사용하기**

```scala
import <패키지>.{<클래스 1>[, <클래스 2>...]}
```



#### 패키징 구문

중괄호를 이용하여 패키지내의 클래스를 정의할 수 있다.

동일한 파일에서 서로 다른 패키지 클래스를 정의할 수 있다.

```scala
package com {
    package oreilly {
        class Config(val baseUrl: String = "http://localhost")
    }
}

new com.oreilly.Config().baseurl
```
