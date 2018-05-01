class Printer(msg: String) {
  def print(s: String): Unit = println(s"$msg: $s")
  def print(l: Seq[String]): Unit = print(l.mkString(", "))
}

new Printer("Today's Report")
  .print("Foggy" :: "Rainy" :: "Hot" :: Nil)
