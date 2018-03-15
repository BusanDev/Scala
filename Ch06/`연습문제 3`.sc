import scala.collection.mutable

/*
  연습문제 3
  주어진 리스트의 첫 X 개의 항목을 반환하는 함수를 작성하라
 */

// 방법 1 : take 메소드를 사용
def first1[A](items: List[A], count: Int): List[A] = {

  items take count
}
val `first1 결과` = first1(List(1, 2, 3, 4, 5), 2)

// 방법 2 : for 루프
def first2[A](items: List[A], count: Int): List[A] = {

  val resultList = mutable.Buffer[A]()
  for (i <- 0 until count) {
    resultList += items(i)
  }
  resultList.toList
}
val `first2 결과`= first2(List(1, 2, 3, 4, 5), 3)

// 방법 3 : foldLeft

// 방법 4 : head & tail 재귀함수
def first4[A](items: List[A], count: Int): List[A] = {

  val resultList = List(items.head)

  def tail(list: List[A], items: List[A], count: Int): List[A] = {
    for (i <- 0 until count) {
      val newList = list :+ items.head
      return tail(newList, items.tail, count - 1)
    }
    list
  }
  tail(resultList, items.tail, count - 1)
}
val `first4 결과`= first4(List(1, 2, 3, 4, 5), 4)