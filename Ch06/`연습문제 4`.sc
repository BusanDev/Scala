/*
  문자열 리스트를 받아서 그 리스트의 가장 긴 문자열을 반환하는 함수를 작성해보자.
  fold, reduce
 */

// 테스트용 데이터
val test1 = List("apple", "banana", "cherry", "strawberry", "pear")
val test2 = List("1", "12", "123", "1234", "12345")
val test3 = List(1, 12, 123, 1234, 12345)

// fold 사용
def `가장_긴_문자열을_반환하는_함수`(inputList: List[String]): String = {
  inputList.fold(inputList(0))((a: String, b: String) => if (a.length >= b.length) a else b )
}

val fold1 = `가장_긴_문자열을_반환하는_함수`(test1)
val fold2 = `가장_긴_문자열을_반환하는_함수`(test2)

// reduce 사용
def `가장_짧은_문자열을_반환하는_함수`(inputList: List[String]): String = {
  inputList.reduce((a: String, b: String) => if (a.length >= b.length) b else a)
}

val reduce1 = `가장_짧은_문자열을_반환하는_함수`(test1)
val reduce2 = `가장_짧은_문자열을_반환하는_함수`(test2)

// 어떤 타입의 리스트에도 적용 가능하도록
def `가장_긴_문자열을_반환하는_함수2`[A](inputList: List[A]): A = {
  inputList.foldLeft(inputList(0))((a: A, b: A) => if (a.toString.length >= b.toString.length) a else b )
}

val result1 = `가장_긴_문자열을_반환하는_함수2`(test2)
val result2 = `가장_긴_문자열을_반환하는_함수2`(test3)