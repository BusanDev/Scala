# Chapter 6 보편적인 컬렉션

------

## 목차

[TOC]



------

## 컬렉션(collection) 이란?

- 배열, 리스트, 맵, 집합, 트리와 같이 주어진 타입을 가지는 하나 또는 그 이상의 값을 수집하는 데이터 구조

  → 빈 컬렉션은 컬렉션인가? 아닌가? 

  → 나중에 Nil 이란 개념이 나옴

- 스칼라는 JVM 언어이므로 스칼라 코드에서 자바 컬렉션 라이브러리를 사용할 수 있다.

- 스칼라 고유의 컬렉션 프레임워크도 제공하고 있다.

- http://www.scala-lang.org/api/2.12.4/scala/collection/index.html <= API 문서

---

## 리스트, 집합, 그리고 맵

### 리스트

```scala
// 리스트 생성 - Int
val numbers = List(32, 95, 24, 21, 17, 46)

// 리스트 생성 - String
val colors = List("red", "orange", "yellow", "green", "blue", "indigo", "purple")

// size
val size = s"I have ${colors.size} colors"

// head
val head = colors.head

// tail
val tail = colors.tail

// index
val index2 = colors(2)

// for
var total1 = 0
for (i <- numbers) {total1 += i}
total1

// sum
val total2 = numbers.sum

// foreach
colors.foreach((c: String) => println(c))
colors.foreach(println(_))

// map
colors.map((c: String) => c.length)
colors.map(_.length)

// reduce
val total3 = numbers.reduce((a: Int, b: Int) => a + b)
val total4 = numbers.reduce(_ + _)
```



### 집합

```scala
// 집합 생성 - Int
val unique = Set(10, 20, 30, 20, 20, 10)

// reduce
val sum = unique.reduce(_ + _)

// 집합 생성 - String
var basket: Set[String] = Set()
basket += "딸기"
basket += "포도"
basket += "포도"
basket += "사과"
basket += "포도"
basket += "바나나"
println(basket)
```

- 순서 없음
- 각 요소들이 유니크함
- Iterable의 서브타입으로, List 와 동일한 연산을 지원함





### 맵

```scala
// 맵 생성
val colorMap = Map("red" -> 0xFF0000, "green" -> 0xFF00, "blue" -> 0xFF)

// 키 검색
val redRGB = colorMap("red")

// Bit 논리합 연산
val cyanRGB = colorMap("green") | colorMap("blue")

// contains
val hasWhite = colorMap.contains("white")

// for
for (pairs <- colorMap) {println(pairs)}

// 키 - 맵 짬뽕
val map = Map(
  "number1" -> "aa",
  "number2" -> "bb",
  "number3" -> 3,
  5 -> "cc"
)

println(map.isEmpty)
println("whole map : " + map)
println("keys : " + map.keys)
println("values : " + map.values)

println(map("number1"))

// 1반이 있었습니다. 그 명단은 다음과 같습니다.
var student1Ban = Map(
  1 -> "고지용",
  2 -> "김말자",
  3 -> "김말이",
  4 -> "김이불",
  5 -> "이재현",
  6 -> "정미쳐",
  7 -> "차수홍",
  8 -> "최예림",
  9 -> "최한잔"
)

// 전학생이 왔습니다.
student1Ban += (10 -> "전학생")

// 최한잔이 술먹고 퇴학 당했습니다.
student1Ban -= 9

// 사람들이 많이 전학을 와서 아예 2반을 만들었습니다.
var student2Ban = Map(
  11 -> "고세융",
  12 -> "권육삼",
  13 -> "김융찬",
  14 -> "김참치"
)

// 관리가 힘들어서 반을 합쳤습니다.
var students = student1Ban ++ student2Ban

/*
 *  for 형태의 루프도 가능하고,
 *  foreach를 지원하는 컬렉션이라면 이렇게도 표현 가능
 */
// 현재 반의 학생명단은 다음과 같습니다.
students.foreach(i => println(i))

println()

// 선생님이 갑자기 5번을 부릅니다.
val callee = 5
println("여기 " + callee + "번 없어?")

// 5번이 반에 있는지 확인합니다.
println(students.contains(5))
```

- 키 - 값 튜플 구조
- Iterable의 서브타입으로, List, Set 과 동일한 연산을 지원함
- 맵은 순서가 없다.

---

## 리스트에는 무엇이 있는가?

```scala
// 컬렉션의 컬렉션
val oddsAndEvents = List(List(1, 3, 5), List(2, 4, 6))

// 튜플의 컬렉션
val keyValues = List(('A', 65), ('B', 66), ('C', 67))

// 인덱스
val primes = List(2, 3, 5, 7, 11, 13)
val first = primes(0)
val fourth = primes(3)

// 헤드 앤 테일
val headOfPrimes = primes.head
val tailOfPrimes = primes.tail

// isEmpty - 가변 변수를 사용하여 반복
var i = primes
while (! i.isEmpty) {
  print(i.head + ", ")
  i = i.tail
}

// isEmpty - 재귀 함수를 사용하여 반복
def visit(i: List[Int]): Unit = {
  if (i.size > 0) {
    print(i.head + ", ")
    visit(i.tail)
  }
}
visit(primes)

// Nil
var j = primes
while (j != Nil) {
  print(j.head + ", ")
  j = j.tail
}

val l: List[Int] = List()
l == Nil
val m: List[String] = List("A")
m.head
m.tail == Nil
```

- Nil은 근본적으로 List[Nothing]의 싱글턴 인스턴스
- Nothing 타입은 스칼라에서 모든 타입의 서브타입
- List[Nothing]은 모든 타입의 리스트와 호환되며, 그들의 종점으로 안전하게 사용될 수 있음
- 새로운 빈 리스트를 생성하면, 실제로는 새 인스턴스 대신에 Nil이 반환이 됨
- Nil 은 불변 데이터 구조이기 때문에 근본적으로는 List[Nothing]과 차이가 없지만 명목상 컬렉션과 구분지은것 같다. ← 내 생각

---

## 생성 연산자 ( :: )

- 스칼라는 리스트를 만들기 위해 생성(cons, construct의 축약형) 연산자의 사용을 지원함
- 생성 연산자 => ( :: )

```scala
// 생성 연산자를 사용해서 리스트 만들기
val list = "a" :: "b" :: "c" :: Nil

for (x <- 0 until list.size)
  println(list(x))

val numbers = 1 :: 2 :: 3 :: Nil

// 왼쪽 결합형 표기법
val first = Nil.::(1)
first.tail == Nil

// 오른쪽 결합형 표기법
val second = 2 :: first
second.tail == first
```

---

## 리스트 산술 연산

- 스칼라에서 지원하는 메소드 들

```scala
val list1 = "a" :: "b" :: "c" :: Nil
val list2 = "d" :: "e" :: Nil
val numbers = 0 :: 1 :: 2 :: 3 :: 4 :: 5 :: Nil

// ::
val list3 = list1 :: list2
for (x <- 0 until list3.size) {
  println(list3(x))
}

// :::
val list4 = list1 ::: list2
for (x <- 0 until list4.size) {
  println(list4(x))
}

// ++
val `++ 1` = List(1, 2) ++ Set(3, 4, 4)
val `++ 2` = Set(3, 4, 4) ++ List(1, 2)

// ==
val `== 1` = List(1, 2) == List(1, 2)
val `== 2` = List(2, 1) == List(1, 2)
val `== 3` = Set(1, 2) == Set(2, 1)
val `== 4` = Map(1 -> "일", 2 -> "이") == Map(2 -> "이", 1 -> "일")

// distinct
val `distinct 1` = List(1, 2, 3, 1, 2, 1) distinct

// drop
val `drop 1` = list4 drop 2

// filter
val `filter 1` = List(23, 8, 14, 17, 0) filter (_ > 15)

// flatten
val `flatten 1` = List(List(1, 2), List(3, 4)) flatten

// partition
val `partition 1` = numbers partition (_ < 3)

// reverse
val `reverse 1` = numbers reverse

// slice(from, until)
val `slice 1` = numbers slice (1, 3)
val `slice 2` = numbers slice (0, 3)

// sortBy
val `sortBy 1` = List("nice", "to", "meet", "you") sortBy (_.size)
val `sortBy 2` = List("hello", "stranger") sortBy (_.length) reverse

// sorted
val `sorted 1` = List(1, 3, 2, 4, 5, 0) sorted
val `sorted 2` = List("a", "c", "b", "A", "D") sorted

// splitAt
val `splitAt` = numbers splitAt(2)

// take
val `take` = numbers take(3)

// zip
val `zip` = numbers zip list1
```

---

## 리스트 매핑

- 함수를 취하여, 그 함수를 리스트의 모든 요소들에 적용하고, 그 결과를 새로운 리스트에 수집한다.

```scala
// collect
val `collect 1` = List(0, 1, 0, 2, 1, 0) collect {
  case 0 => "zero"
  case 1 => "one"
  case _ => "???"
}

// flatMap
val `flatMap 1` = List("milk,tea") flatMap {
  _.split(',')
}

val `flatMap 2` = List(List(1, 2), List(1, 4)) flatMap {
  (_.filter(_ > 1))
}

// map
val `map 1` = `flatMap 1` map (_.toUpperCase)
val `map 2` = `collect 1` map (_.toUpperCase())
val `map 3` = `flatMap 2` map (_ * 2)
```

---

## 리스트 축소하기

- 스칼라의 컬렉션은 수학적 축소 연산과 부울 축소 연산을 지원한다.
- 수학적 축소 연산 : max, min, product, sum, 
- 부울 축소 연산 : contains, endsWith, exists, forall, startsWith

```scala
val numbers1 = List(41, 74, 85, 25, 36)
val numbers2 = List(10.1, 51.4, 23.1, 5.67, 16.7)
val numbers3 = List(1, 2, 3, 4, 5)

// max
val max = numbers1.max

// min
val min = numbers2.min

// product
val product = numbers3.product

// sum
val sum = numbers3.sum

// contains
val contains = numbers1 contains 85

// startsWith
val startsWith1 = numbers3 startsWith List(2)
val startsWith2 = numbers3 startsWith (List(2), 1)

// endsWith
val endsWith1 = numbers3 endsWith List(5)

// exists
val exists = numbers2 exists(_ > 10)

// forall
val forall = numbers3 forall(_ < 5)
```



### 리스트 접기(list-folding)

- 순서, 방향 무관
- 왼쪽에서 오른쪽
- 오른쪽에서 왼쪽

```scala
val list = List(4, 5, 6)

// fold(start index)(reduce function)
val fold = list.fold(0)(_ + _)

// foldLeft
val foldLeft = list.foldLeft(0)(_ + _)

// foldRight
val foldRight = list.foldRight(0)(_ + _)

// reduce(reduce function)
val reduce = list.reduce(_ + _)

// reduceLeft
val reduceLeft = list.reduceLeft(_ + _)

// reduceRight
val reduceRight = list.reduceRight(_ + _)

// scan
val scan = list.scan(0)(_ + _)

// scanLeft
val scanLeft = list.scanLeft(0)(_ + _)

// scanRight
val scanRight = list.scanRight(0)(_ + _)
```

---

##컬렉션 전환하기

```scala
// mkString
val mkString = List(24, 99, 104).mkString(", ")

// toBuffer : 불변의 컬렉션을 가변적인 컬렉션으로 만듬
val toBuffer = List('f', 't').toBuffer

// toList
val toList = Map("a" -> 1, "b" -> 2).toList

// toMap
val toMap = Set(1 -> true, 3 -> true).toMap

// toSet
val toSet = List(2, 5, 5, 3, 2).toSet

// toString
val `toString 1` = List(2, 5, 5, 3, 2).toString
```

---

## 자바와 스칼라 컬렉션 호환성

```scala
// asJava
import java.util
import scala.collection.JavaConverters._
val `asJava 1` = List(12, 29).asJava

// asScala
val `asScala 1` = new util.ArrayList[String](5).asScala
```

---

## 컬렉션으로 패턴 매칭하기

```scala
val httpStatus = List(200, 404, 500)
val message = httpStatus(1) match {
  case x if (x < 400) => "OK"
  case x if (x == 404) => "Not Found"
  case _ => "Internal Server Error"
}

val message2 = httpStatus match {
  case x if (x contains 500) => "이렇게도 가능하다"
  case _ => " "
}

val httpStatus3 = List(202, 406, 502)
val message3 = httpStatus3 match {
  case List(200, _, _) => "200"
  case List(_, 404, _) => "404"
  case List(_, _, 500) => "500"
  case _ => " zzz"
}
```

---

## 연습문제

### 연습문제 1

- 첫 20개의 홀수 Long 숫자의 리스트를 생성하라.
- Hint : for, filter, map

```scala

```



### 연습문제 2

- 숫자를 취하고 1과 자기 자신을 제외한 그 숫자의 인수 리스트를 반환하는 'factors'라는 제목의 함수를 작성하라.
- 'factors'를 숫자 리스트에 반영하는 새로운 함수를 작성하라.

```scala

```



### 연습문제 3

- 주어진 리스트의 첫 x개의 항목을 반환하는 함수 `first[A](items: List[A], count: Int): List[A]`  를 작성하라.
- Hint : for, foldLeft, 재귀함수

```scala

```



### 연습문제 4

- 문자열 리스트를 받아서 그 리스트의 가장 긴 문자열을 반환하는 함수를 작성하라.
- Hint : fold, reduce

```scala

```



### 연습문제 5

- 리스트의 순서를 거꾸로 만드는 함수를 작성하라.
- Hint : 재귀함수, 매치표현식

```scala

```



