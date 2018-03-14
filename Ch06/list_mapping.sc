// collect
val `collect 1` = List(0, 1, 0, 2, 1, 0) collect {
  case 0 => "zero"
  case 1 => "one"
  case _ => "???"
}

// flatMap
val `flatMap 1` = List("milk,tea") flatMap {
  _.split(',')
}

val `flatMap 2` = List(List(1, 2), List(1, 4)) flatMap {
  (_.filter(_ > 1))
}

// map
val `map 1` = `flatMap 1` map (_.toUpperCase)
val `map 2` = `collect 1` map (_.toUpperCase())
val `map 3` = `flatMap 2` map (_ * 2)
