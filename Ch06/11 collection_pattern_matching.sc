val httpStatus = List(200, 404, 500)
val message = httpStatus(1) match {
  case x if (x < 400) => "OK"
  case x if (x == 404) => "Not Found"
  case _ => "Internal Server Error"
}

val message2 = httpStatus match {
  case x if (x contains 500) => "이렇게도 가능하다"
  case _ => " "
}

val httpStatus3 = List(200, 406, 502)
val message3 = httpStatus3 match {
  case List(200, _*) => "200"
  case List(_, 404, _) => "404"
  case List(_, _, 500) => "500"
  case _ => " zzz"
}