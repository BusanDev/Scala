import scala.collection.mutable

/*
  연습문제 2
  - 숫자를 취하고 1과 자기 자신을 제외한 그 숫자의 인수 리스트를 반환하는 'factors'라는 제목의 함수를 작성하라.
  - 'factors'를 숫자 리스트에 반영하는 새로운 함수를 작성하라.
 */

var result = mutable.Buffer[Long]()

def factors(inputNumber: Int): Unit = {

  if (inputNumber > 1) {
    for (i <- 2 to inputNumber) {
      if (inputNumber % i == 0) {
        result += i
        factors(inputNumber / i)
      }
    }
  }

}

factors(15)
result