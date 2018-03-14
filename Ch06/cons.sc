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
