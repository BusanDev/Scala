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
