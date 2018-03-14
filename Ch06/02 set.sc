val unique = Set(10, 20, 30, 20, 20, 10)

val sum = unique.reduce(_ + _)

var basket: Set[String] = Set()
basket += "딸기"
basket += "포도"
basket += "포도"
basket += "사과"
basket += "포도"
basket += "바나나"
println(basket)