// 4.12 패턴 매칭의 다른 사용법

// 값을 정의할 때 패턴 매칭을 사용할 수 있다.
// ex1
case class Address2(street: String, city: String, country: String)
case class Person2(name: String, age: Int, address: Address2)
val Person2(name, age, Address2(_, state, _)) = Person2("Dean", 22, Address2("1 Scala Way", "CA", "USA"))
println("My name is " + name)
// ex2
val head +: tail = List(1, 2, 3)
val list22 = List(1, 2, 3)
list22.head
list22.tail
// ex3
val head1 +: head2 +: tail1 = Vector(1, 2, 3)
// ex4
val Seq(a1, b1, c1, _*) = Seq(1, 2, 3, 4, 5)
// ex5
//val Seq(a2, b2, c2) = Seq(1, 2, 3,)

// if 문에서도 사용 가능
val p2 = Person2("Dean", 23, Address2("1 Scala Way", "CA", "USA"))
//if (p2 == Person2("Dean", 23, Address2("1 Scala Way", "CA", "KOREA"))) "yes"
//else "no"

//if (p2 == Person2(_, 23, Address2(_, _, "KOREA"))) "yes"
//else "no"
// if 문에서는 위치지정자(_) 사용한 패턴 매칭은 할 수 없다는 것만 인지하고 있으면 될듯..


// case절과 패턴 매칭을 사용하는 좋은 예
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int)

val as = Seq(
  Address("1 Scala Lane", "Anytown", "USA"),
  Address("2 Clojure Lane", "Othertown", "USA"))
val ps = Seq(
  Person("Buck Trends", 29),
  Person("Clo Jure", 28))
val pas = ps zip as

// 보기 좋지 않은 예
// 튜플을 인자로 받아서 패턴 매치로 튜플의 두 값을 뽑아냄
pas map { tup =>
  val Person(name, age) = tup._1
  val Address(street, city, country) = tup._2
  s"$name (age: $age) lives at $street, $city, in $country"
}

// 보기 좋은 예
pas map {
  case (Person(name, age), Address(street, city, country)) =>
    s"$name (age: $age) lives at $street, $city, in $country"
}

// 정규 표현식에 대한 패턴 매칭을 사용한 문자열 분해 할 수 있다.
val cols = """\*|[\w, ]+"""
val table = """\w+"""
val others = """.*"""
val selectRE = s"""SELECT\\s*(DISTINCT)?\\s+($cols)\\s*FROM\\s+($table)\\s*($others)?;""".r

//val selectRE(distinct1, cols1, table1, otherClauses) =
// "SELECT DISTINCT col FROM atable;"
//val selectRE(distinct2, cols2, table2, otherClauses) =
//  "SELECT col1, col2 FROM atable;"
//val selectRE(distinct3, cols3, table3, otherClauses) =
//  "SELECT DISTINCT col1, col2 FROM atable;"
val selectRE(distinct4, cols4, table4, otherClauses) =
  "SELECT DISTINCT col1, col2 FROM atable WHERE col1 = 'foo';"

/**
    ? 있거나 없거나
    + 1번 이상
    * 0번 이상
    \s 공백
    \w 단어
    . 모든 단일 문
*/
// 인터폴레이션을 사용하는 경우 이중 역슬래쉬

// unapply 패턴 매칭
// 노출시키는 상태를 제어하기 위함
// unapply를 사용하면 자세한 구현은 숨기면서 정보를 외부에 노출시킬 수 있다.
val num1 = "010-222-2222"
val num2 = "119"
val num3 = "test number"
val numList = List(num1, num2, num3)

for (num <- numList) {
  num match {
    case Emergency() => println("긴급전화 : " + num)
    case Normal(num) => println("일반 전화 : " + num)
    case _ => println("판단 불가")
  }
}

object Emergency {
  def unapply(number: String): Boolean = {
    if (number.length == 3 && number.forall(_.isDigit)) true
    else false
  }
}
object Normal {
  def unapply(number: String): Option[Int] = {
    try{
      val o = number.replaceAll("-", "")
      Some(o.toInt)
    } catch {
      case _: Throwable => None
    }
  }
}

// unapply의 반환 값은 반드시 다음 중에서 선택해야 한다.
// 1. 단순한 테스트라면 Boolean을 반환한다.
// 2. 타입 T의 단일 하위 값을 반환한다면, Option[T]를 반환한다.
// 3. 여러 하위 값 T1,...,Tn를 반환하고 싶다면, 이를 Option[(T1,...,Tn)]과 같이 튜플로 묶어준다.