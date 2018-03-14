// 맵 생성
val colorMap = Map("red" -> 0xFF0000, "green" -> 0xFF00, "blue" -> 0xFF)

// 키 검색
val redRGB = colorMap("red")

// Bit 논리합 연산
val cyanRGB = colorMap("green") | colorMap("blue")

// contains
val hasWhite = colorMap.contains("white")

// for
for (pairs <- colorMap) {println(pairs)}

// 키 - 맵 짬뽕
val map = Map(
  "number1" -> "aa",
  "number2" -> "bb",
  "number3" -> 3,
  5 -> "cc"
)

println(map.isEmpty)
println("whole map : " + map)
println("keys : " + map.keys)
println("values : " + map.values)

println(map("number1"))

// 1반이 있었습니다. 그 명단은 다음과 같습니다.
var student1Ban = Map(
  1 -> "고지용",
  2 -> "김말자",
  3 -> "김말이",
  4 -> "김이불",
  5 -> "이재현",
  6 -> "정미쳐",
  7 -> "차수홍",
  8 -> "최예림",
  9 -> "최한잔"
)

// 전학생이 왔습니다.
student1Ban += (10 -> "전학생")

// 최한잔이 술먹고 퇴학 당했습니다.
student1Ban -= 9

// 사람들이 많이 전학을 와서 아예 2반을 만들었습니다.
var student2Ban = Map(
  11 -> "고세융",
  12 -> "권육삼",
  13 -> "김융찬",
  14 -> "김참치"
)

// 관리가 힘들어서 반을 합쳤습니다.
var students = student1Ban ++ student2Ban

/*
 *  for 형태의 루프도 가능하고,
 *  foreach를 지원하는 컬렉션이라면 이렇게도 표현 가능
 */
// 현재 반의 학생명단은 다음과 같습니다.
students.foreach(i => println(i))

println()

// 선생님이 갑자기 5번을 부릅니다.
val callee = 5
println("여기 " + callee + "번 없어?")

// 5번이 반에 있는지 확인합니다.
println(students.contains(5))