

/*
  연습문제 2
  - 숫자를 취하고 1과 자기 자신을 제외한 그 숫자의 인수 리스트를 반환하는 'factors'라는 제목의 함수를 작성하라.
  - 'factors'를 숫자 리스트에 반영하는 새로운 함수를 작성하라.
 */
def factors(number: Int, list: List[Int] = List()): List[Int] = {
  for(n <- 2 to number if (number % n == 0)) {
    return factors(number / n, list :+ n)
  }
  list
}

factors(15)
