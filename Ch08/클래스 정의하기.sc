////P.164
//class Car(val make: String, var reserved: Boolean) {
//  def reserve(r: Boolean): Unit = { reserved = r; }
//  //def reserve(r: Boolean) = reserved = r
//}
//
//val t = new Car("Toyota", false)
//t.reserve(true)
//
//val t2 = new Car(reserved = false, make = "Tesla")
//println(t2.make)
//
////P.165
//class Lotus(val color: String, reserved: Boolean) extends
//  Car("Lotus", reserved)
//
//val l = new Lotus("Silver", false)
//println(s"Requested a ${l.color} ${l.make}")


// P.166
//class Car(
//        val make: String,
//        var reserved: Boolean = true,
//        val year: Int = 2015
//) {
//  override def toString = s"$year $make, reserved = $reserved"
//}
//
//val a = new Car("Acura")
//
//val l = new Car("Lexus", year = 2010)
//
//val p = new Car(reserved = false, make = "Porsche")

// P.167
class Singular[A](element: A) extends Traversable[A] {
  def foreach[B](f: A => B) = f(element)
}

val p = new Singular("Planes")

p foreach println

val name: String = p.head

//def testConvert(input: String) = input.size
//p foreach testConvert
