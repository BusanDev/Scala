
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
