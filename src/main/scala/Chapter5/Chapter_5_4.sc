// Implicit class는 이미 존재하는 class에 새로운 함수를 추가하여 확장하기 위해서 사용된다.
// 정확히 말하면 원래 있던 클래스 자체를 바꾸지는 않고,
// class를 implicit conversion해서 호출할 수 있는 함수를 추가할 수 있게 해준다.

case class Address(street: String, city: String)
case class Person(name: String, address: Address)

trait ToJSON {
    def toJSON(level: Int = 0): String

    val INDENTATION = "  "
    def indentation(level: Int = 0): (String,String) =
        (INDENTATION * level, INDENTATION * (level+1))
}

implicit class AddressToJSON(address: Address) extends ToJSON {
    def toJSON(level: Int = 0): String = {
        val (outdent, indent) = indentation(level)
        s"""{
           |${indent}"street": "${address.street}",
           |${indent}"city":   "${address.city}"
           |$outdent}""".stripMargin
    }
}

implicit class PersonToJSON(person: Person) extends ToJSON {
    def toJSON(level: Int = 0): String = {
        val (outdent, indent) = indentation(level)
        s"""{
           |${indent}"name":    "${person.name}",
           |${indent}"address": ${person.address.toJSON(level + 1)}
           |$outdent}""".stripMargin
    }
}

val a = Address("1 Scala Lane", "Anytown")
val p = Person("Buck Trends", a)

println(a.toJSON())
println()
println(p.toJSON())

//val aJson = new AddressToJSON(a)
//println(aJson.toJSON())

//Standard library에 있는 implicit class의 대표적인 예는 Duration class들이다.
import scala.concurrent.duration.DurationInt
3.seconds // scala.concurrent.duration.FiniteDuration = 3 seconds
1.second // scala.concurrent.duration.FiniteDuration = 1 second
16.milliseconds // scala.concurrent.duration.FiniteDuration = 16 milliseconds

import scala.concurrent.duration.DurationDouble
1.2.seconds // scala.concurrent.duration.FiniteDuration = 1200 milliseconds
(1.0/60).seconds // scala.concurrent.duration.FiniteDuration = 16666667 nanoseconds
