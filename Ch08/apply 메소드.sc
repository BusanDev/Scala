class Multiplier(factor: Int) {
  def apply(input: Int) = input * factor
}

val tripleMe = new Multiplier(3)
val tripled = tripleMe.apply(10)
val tripled2 = tripleMe(10)

val l = List('a', 'b', 'c')

val character = l(1)
