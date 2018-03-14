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

