case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",  "USA"))

for (person <- Seq(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
    case Person("Bob",   29, Address("2 Java Ave.", "Miami", "USA")) => println("Hi Bob!")
    case Person(name, age, _) => println(s"Who are you, $age year-old persona named $name?")
  }
}

// 컴파일러가 내부적으로 아래와 같은 companion object를 생성하기 때문에 case 절에서 case class에 대한 값의 비교가 가능
// apply 메소드는 injection, unapply 메소드는 extraction 의 역할

//object Person {
//  def apply(name: String, age: Int, address: Address) = new Person(name, age, address)
//  def unapply(p: Person): Option[Tuple3[String, Int, Address]] = Some((p.name, p.age, p.address))
//}

// case +:(head, tail) 구문이 +:.unapply(collection) 메서드와 대응
// +: 객체의 단순화된 unapply 메소드는 아래와 같
// def unapply[T, Coll](collection: Coll): Option[(T, Coll)]
// T: head 타입, Coll: 출력 꼬리 컬렉션의 타입, head 와 tail 값의 tuple 값을 option 형태로 리턴
def processSeq2[T](l: Seq[T]): Unit = l match {
  case +:(head, tail) =>
    printf("%s +: " , head)
    processSeq2(tail)
  case Nil => print("Nil")
}

processSeq2(List(1, 2, 3, 4, 5))

// infix
case class With[A, B](a: A, b: B)

// 아래 표기 방식은 같은 결과를 갖는다(중위 표기법이 허용되기 때문)
val with1: With[String, Int] = With("Foo", 1)
val with2: String With Int = With("Bar", 2)

Seq(with1, with2) foreach { w =>
    w match {
      case s With i => println(s"$s with $i")
      case _        => println(s"Unknown: $w")
    }
}

// match-reverse-seq
val nonEmptyList = List(1, 2, 3, 4, 5)
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)

def reverseSeqToString[T](l: Seq[T]): String = l match {
  case prefix :+ end => reverseSeqToString(prefix) + s" : + $end"
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
  println(reverseSeqToString(seq))
}

// match-seq-unapplySeq
// unapplySeq 메소드 정의
// def unapplySeq[A](x: Seq[A]): Some[Seq[A]]
val emptyList = Nil

def windows[T](seq: Seq[T]): String = seq match {
  case Seq(head1, head2, _*) =>
    s"($head1, $head2), " + windows(seq.tail)
  case Seq(head, _*) =>
    s"($head, _), " + windows(seq.tail)
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows(seq))
}

def windows2[T](seq: Seq[T]): String = seq match {
  case head1 +: head2 +: tail => s"($head1, $head2), " + windows2(seq.tail)
  case head1 +: tail => s"($head1, _), " + windows2(tail)
  case Nil => "Nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows2(seq))
}

