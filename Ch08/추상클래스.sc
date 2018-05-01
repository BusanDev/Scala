abstract class Car {
  val year: Int
  val automatic: Boolean = true
  def color: String
}

//new Car()

class RedMini(val year: Int) extends Car {
  def color = "Red"
}
// year years로 변경해보자
val m: Car = new RedMini(2005)

class Mini(val year: Int, val color: String) extends Car
val redMini: Car = new Mini(2005, "Red")

println(s"Got a ${redMini.color} Mini")