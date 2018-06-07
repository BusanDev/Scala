# Chaper 9  객체 , 케이스클래스, 트레이트



##### 지난 번 배운 내용 : 클래스

- 클래스 : 한번 정의되면 횟수 제한 없이 인스턴스 생성이 가능한 구성요소(붕어빵 틀 같은 것)
  

##### 이번에 배울 내용 : 1.객체  2.케이스클래스 3.트레이트

- 클래스를 보완하고 / 변화를 주거나  / 일부 클래스를 완전히 대체하는 데 사용 가능한 요소
  (클래스의 확장 버전)



##### 설명 방식 : 선코딩(REPL 환경), 후설명

- 먼저 직접 코드를 쳐보고 나중에 설명을 할 것임. (책에 나오는 순서와는 조금 다름)





## 1. 객체

##### 구문 : 객체 정의

```scala
object <식별자> [extends <식별자>] [{ 필드, 메소드, 클래스 }]
```



### 코드 작성

일단 코드를 쳐봅니다. (193페이지)

```scala
scala> object Hello { println("in Hello"); def hi = "hi" }                                            
defined object Hello                                     
                                                         
scala> println(Hello.hi)                                 
in Hello                                                 
hi                                                       
                                                         
scala> println(Hello.hi)                                 
hi                                                       
```



##### 코드 설명

- object로 객체를 정의하고, 명령문과, 함수 정의를 했다. 
- . 쩜으로 객체의 함수에 접근을 했다. 
- hi를 실행했는데, "in Hello"가 출력됐다. 
- 두번째 hi 에서는 "in Hello"가 출력되지 않았다. 



##### 해설

- 이게 무슨 말이냐, 최상위레벨인 'println' 명령은 객체에 최초로 접근하는 시점, 
  인스턴스화 되는 시점에 딱 한번만 호출되고, 그 뒤에 추가적인 초기화는 일어나지 않는다는 말이다. 
- 아래 설명을 보자. 



### 객체의 특징

- object로 정의한다. 
- 자동으로 인스턴스 화, 인스턴스를 단 하나만 가지도록, 강제하는 형태의 클래스(192P 참조)
- 객체지향에서는 싱글톤 singleton 이라고 부름.
- new 키워드로 인스턴스 생성 ( X ), 이름으로 직접 해당객체에 접근 ( O ) - 하나밖에 없으니깐.
- 실행중인 JVM에서 최초로 접근할 때 자동으로 인스턴스화 됨. 
  (객체에 접근하기 전까지는 인스턴스화 되지 않음.)



그러니까 객체란, 

> "생애에 단 한번!  자동으로! 인스턴스화 되는 구성요소"





### 객체의 활용  1. 전역 클래스로 사용 가능



##### 순수 함수

- "주어진 입력값으로만" 계산하여 결과값을 반환하는 함수
- 부작용이 없음.
- 참조에 투명함(함수의 결과값으로 대체하면 구분이 되지 않는다)



##### 194페이지 예제 :  html을 벗겨내고 텍스트만 남기는 코드

- 전역범위에서 다른 어떤 코드도 접근할 수 있으며, 명시적으로 클래스를 초기화 하지 않고도 사용할 수 있다. 







### 객체의 활용 2. apply 메소드와 동반 객체



##### apply 복습 ( 책 173 페이지)

```scala
scala> class Multiplier(factor: Int) {
     | def apply(input: Int) = input * factor
     | }
defined class Multiplier

scala> val tripleMe = new Multiplier(3)
tripleMe: Multiplier = Multiplier@65e7f52a    

scala> val tripled = tripleMe.apply(10)
tripled: Int = 30

scala> val tripled2 = tripleMe(10)
tripled2: Int = 30
```



##### 코드 설명

- Multiplier 클래스를 만들고 클래스 안에 apply 를 정의했다. 
- Multiplier 클래스를 사용해서 tripleMe라는 인스턴스를 만들었고, factor로는 3을 설정했다.
- tripleMe 안에 있는 apply에 10을 넣어서 호출하면 3배에 해당하는 30이 리턴된다
- apply를 안써주고 인스턴스 이름뒤에 바로 괄호를 넣어도 결과는 같다.



##### apply 설명

- apply 메소드는 때로는 기본 메소드 또는 인젝터메소드 라고 불리며, 메소드 이름 없이 호출될 수 있다. 
- apply 메소드는 근본적으로 이름 없이 괄호를 사용하여 적용할 수 있는 기능을 제공하는 간단한 방법이다. 
- List 객체 자체에 apply 메소드가 있다. 그래서 객체 이름 옆에 괄호를 넣어도 동작한다. 이말 같음.



##### 모나딕 컬렉션

- 기억안난다. 다시 복습(133페이지)
- Option 컬렉션 : 대충 보면 (134페이지 아래에) Some인지 None인지확인이 가능하다는 말 같다.
- Future 컬렉션 : 함수 매개변수를 취하여 그 함수를 백그라운드 스레드에서 호출하는 apply()를 사용.(팩토리패턴)



##### 팩토리 패턴

- 클래스의 새로운 인스턴스를 그 동반객체로부터 생성하는 일반적인 방식



#### 동반 객체 

코드를 쳐봅니다.  (196페이지 상단)

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

class Multiplier(val x: Int){ def product(y: Int) = x * y}

object Multiplier { def apply(x: Int) = new Multiplier(x) }


// Exiting paste mode, now interpreting.

defined class Multiplier
defined object Multiplier

scala> var tripler = Multiplier(3)
tripler: Multiplier = Multiplier@616ac46a

scala> val result = tripler.product(13)
result: Int = 39
```

##### 코드 설명

- Multiplier라는 이름을 가진, 동일한 이름의 클래스와 오브젝트를 생성했다. 
- Multipler를  apply 형식으로 인스턴스를 생성했다. 
- tripler에 product를 호출했는데 3배가 되는 숫자가 호출되었다. 
- product는 class 안에 선언된 것이다. 



##### 동반 객체  

- 클래스와 동일한 이름을 공유하며, 동일한 파일 내에서 그 클래스로 함께 정의되는 객체
- 왜 이름이 같아야 하는가?(의문)



#### 동반객체에 접근하는 예제

```scala
scala> :paste
// Entering paste mode (ctrl-D to finish)

object DBConnection {
  private val db_url = "jdbc://localhost"
  private val db_user = "franken"
  private val db_pass = "berry"

 def apply() = new DBConnection
}

 class DBConnection {
  private val props = Map(
   "url" -> DBConnection.db_url,
   "user" -> DBConnection.db_user,
   "pass" -> DBConnection.db_pass
  )
  println(s"Created new connection for " + props("url"))
}

// Exiting paste mode, now interpreting.

defined object DBConnection
defined class DBConnection

scala> val conn = DBConnection()
Created new connection for jdbc://localhost
conn: DBConnection = DBConnection@7ec58feb
```

- 새로운 DBConnection 객체는 데이터베이스 연결 데이터를 프라이빗 상수에 저장하지만, 동일한 이름의 클래스는 연결을 생성할 때 이 상수들을 읽을 수 있다. 



### 객체의 활용 3.객체를 가지는 명령줄 애플리케이션

```scala
$ cat > Date.scala                    
object Date {                                    
  def main(args: Array[String]){                 
      println(new java.util.Date)
  }                                              
}                                                
                                                 
$ scalac Date.scala                   
                                                 
$ scala Date                          
Mon May 28 23:40:53 KST 2018                     
```

- 스칼라는 객체에 'main' 메소드를 사용하여 애플리케이션의 진입점으로  이 특징을 지원한다. 
- 명령줄 애플리케이션을 스칼라에서 생성하려면 입력인수로 문재열 배열을 취하는  'main' 메소드를 추가하면 된다. 코드를 컴파일 했다면 이제 객체 이름을 scala 명령어와 함께 실행하면 된다.



### 결론 정리

그래서 객체가 뭐가 좋다는 건지, 어떻게 쓰라는 건가?

- 객체는 인스턴스 기반의 클래스에 대한 전역범위의 대안
- 명령줄 애플리케이션 만들수 있음.
- 동반객체로 클래스와 짝을 이루면 깔끔하고 분리된, 읽기 쉬운 애플리케이션을 만들 수 있음.





## 2. 케이스 클래스

##### 구문 : 객체 정의

```scala
case class <식별자>([var] <식별자> : <타입> [,...])
						[extends <식별자>(<입력매개변수>)]
						[{필드와 메소드}]
```



### 코드 작성

코드를 쳐 봅니다. (201 페이지) 

```scala
scala> case class Character(name: String, isThief : Boolean)
defined class Character                                                                                              
scala> val h = Character("Hadrian", true)
h: Character = Character(Hadrian,true)

scala> val r = h.copy(name="Royce")
r: Character = Character(Royce,true)

scala> h == r
res0: Boolean = false

scala> h match {                                                                                 
     | case Character(x, true) => s"$x is a thief"
     | case Character(x, false) => s"$x is not a thief"
     | }                                                              
res1: String = Hadrian is a thief
```



##### 코드 설명

- "case 가 붙은", 클래스 Character를 만들었다. (인자는 name과 isThief이다.)
- 값 h를 선언하고 "Hadrian"과  true 값을 가지는 Character를 h에다 넣었다. 
- h에서 카피를 떠서 r에 집어넣었다. 
  (카피라는 메소드는 우리가 선언해준 적이 없다. 케이스클래스로 만들면 자동으로 지원해준다.)
- h랑 r이 같은지 확인해봤다. 안같단다.
- h에 match를 써서 하드리안이 도둑놈인지 확인해봤다. 
  (match 역시 우리가 써준 적이 없다.)
- 도둑놈이라고 결과가 나왔다. 우리가 true를 넣어줬기 때문이다.



##### 주석

- 주석을 하나씩 읽어봅니다. (201페이지 코드 밑에 있음)



### 케이스 클래스는...

- 자동으로 생성된 메소드 몇가지를 포함하는 인스턴스 생성이 가능한 클래스(200페이지 표 9-1 참조)
- 자동으로 생성되는 동반 객체를 포함. (동반 객체도 자신만의 자동으로 생성된 메소드를 가지고 있다. )
- 생성된 데이터 기반 메소드가 주어졌을 때 주로 데이터를 저장하는 데 사용되는 클래스인 데이터 전송 객체를 잘 지원한다. 
- 계층적인 클래스 구조에서는 잘 동작하지 않는데, 상속 받은 필드는 그 유틸리티 메소드를 만드는 데 사용되지 않기 때문이다. 



### 결론 정리

- 함수 작성할 때는 객체와 트레이트가 용이함??
- 케이스 클래스는 데이터 관리하기 용이하고, 함수 재사용하기가 좋음



## 3. 트레이트

##### 구문 : trait 정의하기

```scala
trait <식별자> [extens <식별자>][{필드, 메소드, 클래스}]
```



### 코드 작성

코드를 쳐봅시다 (203페이지 상단)

```scala
scala> trait HtmlUtils{
     | def removeMarkup(input: String) = {
     |	input
     | 		.replaceAll("""</?\w[^>]*>""","")
     | 		.replaceAll("<.*>","")
     | 		}
     | }
defined trait HtmlUtils

scala> class Page(val s: String) extends HtmlUtils{
     | def asPlainText = removeMarkup(s)
     | }
defined class Page

scala> new Page("<html><body><h1>Introduction</h1></body></html>").asPlainText
res2: String = Introduction
```



##### 코드 설명

- trait를 정의했다. 안에는 removeMarkup 함수가 들어있다.
- removeMarkup 함수는 마크업을 지우는 함수다. 
- Page라는 클래스를 만들어냈는데 HtmlUtils라는, 위에서 만들어놓은 trait를 확장(상속)해서 만든다. 
- Page 안에 asPlainText라는 함수를 정의하고 이 안에 removeMarkup을 집어넣었다. 
- 이제 Page에서 asPlainText를 실행하면, 마크업을 지우는 함수를 바로 써먹을 수 있다. 



코드를 하나 더 쳐봅시다. (203페이지 하단)

```scala
scala> trait SafeStringUtils {
     | def trimToNone(s: String): Option[String] = {
     | Option(s) map(_.trim) filterNot(_.isEmpty)
     | }
     | }
defined trait SafeStringUtils

scala> class Page(val s : String) extends SafeStringUtils with HtmlUtils {
     | def asPlainText: String = {
     | trimToNone(s) map removeMarkup getOrElse "n/a"
     | }
     | }
defined class Page

scala> new Page("<html><body><h1>Introduction</h1></body></html>").asPlainText
res3: String = Introduction

scala> new Page(" ").asPlainText
res4: String = n/a

scala> new Page(null).asPlainText
res5: String = n/a
```



##### 코드 설명

- SafeStringUtils라는 트레이트를 정의했음.
  (안에 들어있는 option은 문자열이 정리된걸 반환하든가 None을 반환함.)
- Page라는 클래스를 만들었는데 SafeStringUtils를 확장(상속)하고, HtmlUtils도 확장(상속)함
  - 다중상속임. (이때 with 키워드를 사용)
  - 안에 asPlainText라고 똑같은 이름을 또 넣어놓고 값이 없으면 "n/a"를 뱉어내라고 설정해둠.
- 이제 아까처럼 asPlainText를 써먹으면서 마크업을 제거할 수 있고,
- 또 값이 없으면 "n/a"를 뱉어낸다. 





### 트레이트란?

- 클래스 키워드 대신 trait 키워드를 사용하여 생성함.
- 다중상속을 가능하게 하는 클래스 유형중의 하나임. 
- 클래스, 케이스 클래스, 객체, 트레이트 : 하나 이상의 클래스 확장 불가, 동시에 여러 트레이트 확장가능
- 트레이트 : 인스턴스화 불가능



#### 선형화 linearization

어째서 스칼라에서는 다중상속이 가능한가? 

> - JVM 클래스는 기본적으로 하나의 부모 클래스만 확장 가능
> - 스칼라 언어는 이론적으로 다중상속을 지원하지만, 실제로 컴파일러는 클래스와 트레이트의 긴 한줄짜리 계층구조를 만들기 위해 각 트레이트의 사본을 생성함.
> - 클래스가 클래스를 확장하고, 또 그 클래스가 클래스를 확장하는, 꼬리에 꼬리를 무는 클래스의 확장을 스칼라에서 지원해주고 이를 수직적인 체인으로 구성하는데,  이를 선형화 라고 부름.
> - 이것이 다중상속을 지원하는 스칼라의  대처방안임.



##### 트레이트는 부모 클래스 다음에 온다.

> 만일 클래스와 하나 또는 그 이상의 트레이트를 확장한다면, with 키워드를 이용하여 트레이트를 추가하기 전에 클래스를 먼저 확장해야 한다. 만약 부모클래스가 지정되었다면, 그 부모 클래스는 어떤 부모 트레이트 보다 앞에 나와야 한다. 



#### 선형화 순서

다중상속순서, 가장 낮은 서브클래스에서 가장 높은 기반 클래스까지, 오른쪽에서 왼쪽 순으로 배치한다. 

- class D extends A with B with C는
- class D extends C extends B extends A로 재구현된다. 

(205페이지 예제 참고)



### 셀프 타입 self_type

- 트레이트의 애너테이션
- 그 트레이트가 클래스에 추가될 때 특정타입 또는 그 서브타입과 함께 사용되어야 함을 명시
- 트레이트가 클래스와 함께 사용될때 언제나 지정된 타입의 서브타입이 될 것임을 보장
- 책 208페이지 참조



### 트레이트의 활용 - 트레이트를 이용하여 인스턴스화

```scala
scala> class A
defined class A

scala> trait B{ self: A => }
defined trait B

scala> val a = new A with B
a: A with B = $anon$1@2eeb0f9b
```

- 클래스가 인스턴스화 될 때 클래스에 트레이트를 추가
- 클래스 인스턴스화 시점에 추가된 트레이트는, 그 클래스를 확장한다. 
- 다시말해, 클래스가 트레이트를 확장하는 것이 아니라, 트레이트가 클래스를 확장한다. 
- with 키워드 사용.
- extends 키워드 사용불가(트레이트를 확장하는 것이 아니라 트레이트에 의해 확장 되는 것이기 때문임)
- 211페이지 코드 - 종속성 주입 예제



##### 트레이트 vs 추상클래스 vs 인터페이스

|                  | 트레이트 | 추상클래스 | 인터페이스 |
| ---------------- | :------: | :--------: | :--------: |
| 메소드 구현 가능 |    O     |     O      |     X      |
| 다중 상속 가능   |    O     |     X      |     O      |
| 변수 선언 가능   |    O     |     O      |     X      |
| 인스턴스 생성    |    X     |     X      |     X      |



## 인스턴스 구성원 임포트 하기

##### 코드 

```scala
scala> case class Receipt(id: Int, amount: Double, who: String, title: String)
defined class Receipt

scala> {
     | val latteReceipt = Receipt(123, 4.12, "fred", "Medim Latte")
     | import latteReceipt._
     | println(s"Sold a $title for $amount to $who")
     | }
Sold a Medim Latte for 4.12 to fred
```



##### 코드 설명

- 176페이지 복습 - 패키징 된 클래스에 접근하기
- import 키워드를 이용하여 외부 패키지로부터 클래스를 추가하여, 패키지명을 앞에 붙이지 않고 접근가능



##### 코드

```scala
scala> import util.Random._
import util.Random._

scala> val letters = alphanumeric.take(20).toList.mkString
letters: String = kD4ZSZaJv8e56t4p3Mcl

scala> val numbers = shuffle(1 to 20)
numbers: scala.collection.immutable.IndexedSeq[Int] = Vector(12, 9, 3, 18, 7, 2, 4, 16, 8, 15, 20, 11, 14, 6, 19, 13, 5, 17, 1, 10)
```



##### 코드 설명

- util.Random에 있는 모든 메소드를 통째로 불러와서 사용했음.
- 이름충돌 및 가독성저하에 주의해야 함. 
- 임포트 시에는 써먹을 곳과 가까운 곳에서 부르는 게 좋음.



## 단원 전체 요약

- 클래스 : 스칼라 애플리케이션의 핵심 구성요소
- 트레이트 : 다중상속 지원, 클래스는 트레이트에 의해 강화가능
- 객체 : 트레이트보다는 덜 유연하지만, 내장된 싱글턴 메커니즘을 자바의 싱글턴 도구나 정적 구성원과 클래스에 비해 훨씬 적은 표준으로 제공
- 케이스 클래스 또한 스칼라 애플리케이션의 핵심 구성요소로 포함되어야 함. 
  (케이스 클래스는 동반객체를 생성하며, 클래스 그 이상임.)
