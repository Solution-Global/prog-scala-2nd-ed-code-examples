/*
자신만의 문자열 인터폴레이션 만들기
s"ddd
문자열 알에 s, f라는 식을 보면 컴파일러는 scala.StringContext를에서 s라는 함수를 찾음
 */

import scala.util.parsing.json._

object Interpolators {
  implicit class jsonForStringContext(val sc: StringContext) {       // <1>
    def json(values: Any*): JSONObject = {                           // <2>
      val keyRE = """^[\s{,]*(\S+):\s*""".r                          // <3>
    val keys = sc.parts map {                                      // <4>
      case keyRE(key) => key
      case str => str
    }
      val kvs = keys zip values                                      // <5>
      JSONObject(kvs.toMap)                                          // <6>
    }
  }
}

import Interpolators._

val name = "Dean Wampler"
val book = "Programming Scala, Second Edition"

val jsonobj = json"{name: $name, book: $book}"                       // <7>
println(jsonobj)
