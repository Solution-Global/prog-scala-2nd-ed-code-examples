///////////////////////////////////////
// 6.7.1 시퀀스
///////////////////////////////////////

val seq: Seq[Int] = Seq(1, 2, 3, 4, 5)

// src/main/scala/progscala2/fp/datastructs/list.sc

val list1 = List("Programming", "Scala")                             // <1>
val list2 = "People" :: "should" :: "read" :: list1                  // <2>
// :: 는 List의 method
// ::는  인자를 컬렉션의 원소 하나로 취급

// Nil 은 빈 리스트를 의미
val nil: Seq[Nothing] = Nil

val list3 = "Programming" :: "Scala" :: Nil                          // <3>
val list4 = "People" :: "should" :: "read" :: Nil
val list5 = list4 ++ list3     // ++ : 두 리스트를 결합                                      // <4>

//val list6 = "programming" :: "java" // error


// src/main/scala/progscala2/fp/datastructs/seq.sc

val seq1 = Seq("Programming", "Scala")                               // <1>
val seq2 = "People" +: "should" +: "read" +: seq1                    // <2>

val seq3 = "Programming" +: "Scala" +: Seq.empty                     // <3>
val seq4 = "People" +: "should" +: "read" +: Seq.empty
val seq5 = seq4 ++ seq3                                              // <4>

val firstSeq = Seq("A", "base", "trait")
val secondSeq = Seq("for", "sequences")

// :+, +:는 list에 원소를 추가하기 위한 메소드
// :+, +:를 사용할  + 쪽에 있는 인자는 원소 하나로 취급됨
// +의 위치에 따라 추가되는 위치 결정
val fs1 = firstSeq :+ secondSeq
val fs2 = firstSeq +: secondSeq

// list에 원소를 추가할 때, colone 쪽에는 list가 있어야함에 유의
val fs3 = "first" +: firstSeq
val fs4 = firstSeq :+ "first"
//val fs5 = firstSeq :: secondSeq // error


// src/main/scala/progscala2/fp/datastructs/vector.sc

val vect1 = Vector("Programming", "Scala")
val vect2 = "People" +: "should" +: "read" +: vect1
println(vect2)

val vect3 = "Programming" +: "Scala" +: Vector.empty[String]
val vect4 = "People" +: "should" +: "read" +: Vector.empty[String]
val vect5 = vect4 ++ vect3
println(vect5)

println(seq4(0)) // 이런식으로 seq와 vector에 접근 가능
println(seq5(3))

scala.Seq(1, 2, 3, 4, 5)
scala.collection.immutable.Seq


Seq(1, 2, 3, 4, 5)
