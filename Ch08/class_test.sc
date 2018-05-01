// P.158 - 1
//class User
//val u = new User
//val isAnyRef = u.isInstanceOf[AnyRef]

// P.158 - 2
//class User {
//  val name: String = "Yubaba"
//  def greet: String = s"Hello from $name"
//  override def toString = s"User($name)"
//}
//
//val u = new User
//println( u.greet )

// P.159 - 1
//class User(n: String) {
//  val name: String = n
//  def greet: String = s"Hello from $name"
//  override def toString = s"User($name)"
//}
//
//val u = new User("Park");
//println(u.greet);

// P.159 - 2
//class User(val name: String) {
//  def greet: String = s"Hello from $name"
//  override def toString = s"User($name)"
//}
//
//val users = List(
//  new User("Shoto"),
//  new User("Art3mis"),
//  new User("Aesch")
//)
//
//val sizes = users map (_.name.size)
//
//val sorted = users sortBy (_.name)
//
//val third = users find (_.name contains "3")
//
//val greet = third map (_.greet) getOrElse "hi"

// P.161
class A {
  def hi = "Hello from A"
  override def toString = getClass.getName
}

class B extends A

class C extends B {
  override def hi = "hi C -> " + super.hi
}

//class D {
//  def hi = "hi D"
//}

val hiA = new A().hi;

val hiB = new B().hi

val hiC = new C().hi

// P.162
val a: A = new A

val a1: A = new B

//val b: B = new A

val b2: B = new B

val misc = List(new C, new A, new B)
//val misc = List(new C, new A, new B, new D)
//val misc: List[A] = List(new C, new A, new B)

val message = misc.map(_.hi).distinct.sorted