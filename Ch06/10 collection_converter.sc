// mkString
val mkString = List(24, 99, 104).mkString(", ")

// toBuffer : 불변의 컬렉션을 가변적인 컬렉션으로 만듬
val toBuffer = List('f', 't').toBuffer

// toList
val toList = Map("a" -> 1, "b" -> 2).toList

// toMap
val toMap = Set(1 -> true, 3 -> true).toMap

// toSet
val toSet = List(2, 5, 5, 3, 2).toSet

// toString
val `toString 1` = List(2, 5, 5, 3, 2).toString

// asJava
import java.util
import scala.collection.JavaConverters._
val `asJava 1` = List(12, 29).asJava

// asScala
val `asScala 1` = new util.ArrayList[String](5).asScala