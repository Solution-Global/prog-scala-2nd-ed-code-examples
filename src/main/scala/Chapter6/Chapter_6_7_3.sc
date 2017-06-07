///////////////////////////////////////
// 6.7.3 집합
///////////////////////////////////////

// 집합은 순서가 없으며 집합의 각 원소는 집합 내에서 유일하다

// src/main/scala/progscala2/fp/datastructs/set.sc

val states = Set("Alabama", "Alaska", "Wyoming")

val lengths = states map (st => st.length)
println(lengths)

// + 연산자를 통해 항목을 추가할 수 있다.

val states2 = states + "Virginia"
println(states2)

val states3 = states2 + ("New York", "Illinois")
println(states3)
