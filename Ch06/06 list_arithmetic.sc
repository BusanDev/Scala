val list1 = "a" :: "b" :: "c" :: Nil
val list2 = "d" :: "e" :: Nil
val numbers = 0 :: 1 :: 2 :: 3 :: 4 :: 5 :: Nil

// ::
val list3 = list1 :: list2
for (x <- 0 until list3.size) {
  println(list3(x))
}

// :::
val list4 = list1 ::: list2
for (x <- 0 until list4.size) {
  println(list4(x))
}

// ++
val `++ 1` = List(1, 2) ++ Set(3, 4, 4)
val `++ 2` = Set(3, 4, 4) ++ List(1, 2)

// ==
val `== 1` = List(1, 2) == List(1, 2)
val `== 2` = List(2, 1) == List(1, 2)
val `== 3` = Set(1, 2) == Set(2, 1)
val `== 4` = Map(1 -> "일", 2 -> "이") == Map(2 -> "이", 1 -> "일")

// distinct
val `distinct 1` = List(1, 2, 3, 1, 2, 1) distinct

// drop
val `drop 1` = list4 drop 2

// filter
val `filter 1` = List(23, 8, 14, 17, 0) filter (_ > 15)

// flatten
val `flatten 1` = List(List(1, 2), List(3, 4)) flatten

// partition
val `partition 1` = numbers partition (_ < 3)

// reverse
val `reverse 1` = numbers reverse

// slice(from, until)
val `slice 1` = numbers slice (1, 3)
val `slice 2` = numbers slice (0, 3)

// sortBy
val `sortBy 1` = List("nice", "to", "meet", "you") sortBy (_.size)
val `sortBy 2` = List("hello", "stranger") sortBy (_.length) reverse

// sorted
val `sorted 1` = List(1, 3, 2, 4, 5, 0) sorted
val `sorted 2` = List("a", "c", "b", "A", "D") sorted

// splitAt
val `splitAt` = numbers splitAt(2)

// take
val `take` = numbers take(3)

// zip
val `zip` = numbers zip list1


