// 리스트 생성 - Int
val numbers = List(32, 95, 24, 21, 17, 46)

// 리스트 생성 - String
val colors = List("red", "orange", "yellow", "green", "blue", "indigo", "purple")

// size
val size = s"I have ${colors.size} colors"

// head
val head = colors.head

// tail
val tail = colors.tail

// index
val index2 = colors(2)

// for
var total1 = 0
for (i <- numbers) {total1 += i}
total1

// sum
val total2 = numbers.sum

// foreach
colors.foreach((c: String) => println(c))
colors.foreach(println(_))

// map
colors.map((c: String) => c.length)
colors.map(_.length)

// reduce
val total3 = numbers.reduce((a: Int, b: Int) => a + b)
val total4 = numbers.reduce(_ + _)
