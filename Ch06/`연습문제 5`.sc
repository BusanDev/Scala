/*
  리스트의 순서를 거꾸로 만드는 함수를 작성하라.
  - 재귀적 함수
  - 매치표현
 */

// 테스트 데이터
val test1 = List(1, 2, 3, 4, 5)
val test2 = List("일", "이", "삼", "사", "오")
val test3 = List('a', 'b', 'c', 'd', 'e')

// reverse
val reverse1 = test1.reverse
val reverse2 = test2.reverse
val reverse3 = test3.reverse

// 재귀 함수
def `재귀_함수를_사용한_방법`[A](inputList: List[A], count: Int): List[A] = {

  def `결과물을_조립하기_위한_함수`[A](headList: List[A], tailList:  List[A], count: Int): List[A] = {

    if (count > 1) {
      val newHeadList =  tailList.head :: headList
      return `결과물을_조립하기_위한_함수`(newHeadList, tailList.tail, count - 1)
    }
    headList
  }

  val headList = List(inputList.head)
  val tailList = inputList.tail

  `결과물을_조립하기_위한_함수`(headList, tailList, count)
}

val `재귀 테스트 1` = `재귀_함수를_사용한_방법`(test1, test1.size)
val `재귀 테스트 2` = `재귀_함수를_사용한_방법`(test2, test1.size)
val `재귀 테스트 3` = `재귀_함수를_사용한_방법`(test3, test1.size)

// 매치 표현식 - 못풀겠음
def `매치_표현식을_사용한_방법`[A](inputList: List[A]) = {

  inputList match {
    case a: List[A] => a.reverse
    case Nil => ""
  }
}

val `매치 표현식 테스트 1` = `매치_표현식을_사용한_방법`(test1)
val `매치 표현식 테스트 2` = `매치_표현식을_사용한_방법`(test2)
val `매치 표현식 테스트 3` = `매치_표현식을_사용한_방법`(test3)