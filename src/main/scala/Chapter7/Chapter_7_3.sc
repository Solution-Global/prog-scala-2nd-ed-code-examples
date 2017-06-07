///////////////////////////////////////
// 7.3 for 내장의 변환 규칙
///////////////////////////////////////


// for ( pat <- expr1 ) yield expr2
// expr1 map { case pat => expr2 }

val expr1 = Seq(1, 2, 3, 4, 5)

for ( x <- expr1 ) yield x + 1
expr1.map { case x => x + 1}

// for ( pat <- expr1 ) expr2
// expr1 foreach { case pat => expr2}

for ( x <- expr1 ) println(x + 1)
expr1.foreach { case x => println(x + 1) }

// for ( pat1 <- expr1; pat2 <- expr2; ... ) yield exprN
// expr1 flatMap { case pat1 => for ( pat2 <- expr2 ... ) yield exprN }

val expr2 = Seq("a", "b", "c", "d", "e")

for ( x <- expr1; y <- expr2) yield s"$x and $y"
expr1.flatMap { case x => for ( y <- expr2 ) yield s"$x and $y" }

// for ( pat1 <- expr1; pat2 <- expr2; ... ) yield exprN
// expr1 foreach { case pat1 => for ( pat2 <- expr2 ... ) yield exprN }

for ( x <- expr1; y <- expr2) yield println(s"$x and $y")
expr1.foreach { case x => for ( y <- expr2 ) yield println(s"$x and $y") }

// pat1 <- expr1 if guard
// pat1 <- expr1 withFilter (( arg1, arg2, ... ) => guard )

for ( x <- expr1 if x % 2 == 0) yield x
for ( x <- expr1.withFilter( x => x % 2 == 0 )) yield x

// pat1 <- expr1; pat2 = expr2
// (pat1, pat2) <- for {
//   x1 @ pat1 <- expr1
// } yield {
//   val x2 @ pat2 = expr2
//   (x1, x2)
// }

// src/main/scala/progscala2/forcomps/for-variable-translated.sc

val map = Map("one" -> 1, "two" -> 2)

val list1 = for {
  (key, value) <- map   // How is this line and the next translated?
  i10 = value + 10
} yield (i10)
// Result: list1: scala.collection.immutable.Iterable[Int] = List(11, 12)

// Translation:
val list2 = for {
  (i, i10) <- for {
    x1 @ (key, value) <- map
  } yield {
    val x2 @ i10 = value + 10
    (x1, x2)
  }
} yield {
  println(s"i = $i, i10 = $i10")
  (i10)
}
// Result: list2: scala.collection.immutable.Iterable[Int] = List(11, 12)



// src/main/scala/progscala2/forcomps/for-patterns.sc

val ignoreRegex = """^\s*(#.*|\s*)$""".r                             // <1>
val kvRegex = """^\s*([^=]+)\s*=\s*([^#]+)\s*.*$""".r                // <2>

val properties = """
                   |# Book properties
                   |
                   |book.name = Programming Scala, Second Edition # A comment
                   |book.authors = Dean Wampler and Alex Payne
                   |book.publisher = O'Reilly
                   |book.publication-year = 2014
                   |""".stripMargin                                                   // <3>



val kvPairs = for {
  prop <- properties.split("\n")                                     // <4>
  if ignoreRegex.findFirstIn(prop) == None                           // <5>
  kvRegex(key, value) = prop                                         // <6>
} yield {
  (key.trim, value.trim)
}                                       // <7>
// Returns: kvPairs: Array[(String, String)] = Array(
//   (book.name,Programming Scala, Second Edition),
//   (book.authors,Dean Wampler and Alex Payne),
//   (book.publisher,O'Reilly),
//   (book.publication-year,2014))
