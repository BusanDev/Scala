class RandomPoint{
  val x = { println("creating x"); util.Random.nextInt }
  lazy val y = { println("now y"); util.Random.nextInt }
}

val p = new RandomPoint()

println(s"Location is ${p.x}, ${p.y}")

println(s"Location is ${p.x}, ${p.y}")