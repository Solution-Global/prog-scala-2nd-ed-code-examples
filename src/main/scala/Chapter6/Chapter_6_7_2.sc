///////////////////////////////////////
// 6.7.2 맵
///////////////////////////////////////

// 맵은 키와 값을 저장하기 위해 사용
// 일반적인 데이터 구조들이 제공하는 map method와는 다른 개념의 데이터 구조

// src/main/scala/progscala2/fp/datastructs/map.sc

// map은 tuple을 인자로 받음

// PreDef에 immutable.Map이 Map으로 정의되어 있음
val stateCapitals: Map[String, String] = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

val stateCapitalss: Map[String, String] = Map(
  ("Alabama", "Montgomery"),
  ("Alaska" , "Juneau"),
  ("Wyoming", "Cheyenne"))

// map method에 함수를 정의하는 두가지 방식

// key => value : k, v를 가진 튜플 하나로 받음
val lengths = stateCapitals map {
  kv => (kv._1, kv._2.length)
}
println(lengths)
// case (key, value) : k와 v를 분리하여 받음
val caps = stateCapitals map {
  case (k, v) => (k, v.toUpperCase)
}
println(caps)

val stateCapitals2: Map[String, String] = stateCapitals + ("Virginia" -> "Richmond")
println(stateCapitals2)

val stateCapitals3 = stateCapitals2 + (
  "New York" -> "Albany", "Illinois" -> "Springfield")
println(stateCapitals3)

// 위의 stateCApitals2에서 괄호를 없애면?
// Map을 String으로 변환해버려서 (String, String)인 tuple이 반환됨
val stateCapitals4: (String, String) = stateCapitals + "Virginia" -> "Richmond"

import scala.collection.mutable

val mutableMap: mutable.Map[String, String] = mutable.Map.empty
val immutableMap: Map[String, String] = Map.empty

mutableMap + (("key" -> "value"), ("keyy" -> "valuee"))
immutableMap + (("key" -> "value"), ("keyy" -> "valuee"))
