///////////////////////////////////////
// 4.3 시퀀스에 일치시키기
///////////////////////////////////////
// Seq 는 정해진 순서대로 원소를 순회할 수 있는 List나 Vector 등의 모든 구체적인 컬렉션 타입의 타입이다.
// Map은 순회 시 특별한 순서를 보장하지 않기 때문에 Seq의 서브타입이 아니다.
// 패턴 매칭과 재귀를 사용해서 Seq를 순회하는 전통적인 관용구를 살펴보자.
val nonEmptySeq     = Seq(1, 2, 3, 4, 5) // 비어있지 않은 Seq 생성. Seq는 trait으로 실제로는 List가 반환됨.
val emptySeq        = Seq.empty[Int] // 비어있는 Seq 생성.
val nonEmptyList    = List(1, 2, 3, 4, 5)
val emptyList       = Nil
val nonEmptyVector  = Vector(1, 2, 3, 4, 5)
val emptyVector     = Vector.empty[Int]
val nonEmptyMap     = Map("one" -> 1, "two" -> 2, "three" -> 3)
val emptyMap        = Map.empty[String, Int]

// T 라는 타입에 대해 Seq[T]로부터 String을 생성하는 재귀적 메서드
def seqToString[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"$head +: " + seqToString(tail) // 비어있지 않은 Seq와 일치
    case Nil => "Nil" // 빈 Seq와 일치
}

// Seq를 seq에 넣어 순회하면서 seqToString을 호출한다.
// Map은 Seq의 서브타입이 아니지만, Map에 toSeq를 호출하면 키-값 쌍의 시퀀스로 맵을 바꿀 수 있다.
for (seq <- Seq(
    nonEmptySeq, emptySeq, nonEmptyList, emptyList,
    nonEmptyVector, emptyVector, nonEmptyMap.toSeq, emptyMap.toSeq)) {
    println(seqToString(seq))
}

// 각각의 '하위리스트'를 괄호로 둘러쌓 계층구조를 더 명확히 보여줌.
def seqToString2[T](seq: Seq[T]): String = seq match {
    case head +: tail => s"($head +: ${seqToString2(tail)})" // 괄호 추가
    case Nil => "(Nil)"
}

for (seq <- Seq(
    nonEmptySeq, emptySeq, nonEmptyMap.toSeq)) {
    println(seqToString2(seq))
}

// 모든 시퀀스에 대해 단지 두 case절과 재귀 메서드를 사용하여 처리할 수 있다.
// 즉, 시퀀스는 비어 있거나, 그렇지 않거나 둘 중 하나다.
// 스칼라 2.10 이전에는 Seq 대신 List를 사용하는 것이 더 일반적이었으나
// 이제는 List나 Vector 등 모든 서브클래스에 적용할 수 있도록 Seq를 활용해서 코드를 작성하는 것이 더 일반덕이다.

def listToString[T](list: List[T]): String = list match {
    case head :: tail => s"($head :: ${listToString(tail)})" // +: 를 ::로 변경.
    case Nil => "(Nil)"
}

for (l <- Seq(
    nonEmptyList, emptyList)) {
    println(listToString(l))
}


// 원래 객체를 재구성
// +:(cons) 연산자가 오른쪽으로 결합되는 메서드이기 때문에 4를 Nil 앞에 붙이고,
// 그 결과 List 앞에 다시 3을 분이는 방식으로 계산한다.
val s1= (1 +: (2 +: (3 +: (4 +: (5 +: (Nil))))))
val l = (1 :: (2 :: (3 :: (4 :: (5 :: (Nil))))))
val s2 = (("one",1) +: (("two",2) +: (("three",3) +: (Nil))))
val m = Map(s2 : _*) // 가변인자 목록으로 전달
