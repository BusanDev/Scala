/**
  * 연습문제 1
  * 첫 20개의 홀수 Long 숫자의 리스트를 생성하라.
  * 힌트 : for, filter or map
  */

// 1. 먼저 리스트를 생성한다.
// 불변 컬렉션
val numberList1: List[Long] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40)

// 가변 컬렉
var numberList2 = collection.mutable.Buffer[Long](1)
for (i <- 2 to 40) {
  numberList2 += i
}
numberList2

// 2. filter 로 홀수만 취한다
val oddNumberList = numberList1.filter(_ % 2 != 0)
