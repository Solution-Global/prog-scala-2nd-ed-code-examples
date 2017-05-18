/*
List 관련 타입 일치
 */

for {
  x <- Seq(List(5.5,5.6,5.7), List("a", "b"))
} yield (x match {
  case seqd: Seq[Double] => ("seq double", seqd)
  case seqs: Seq[String] => ("seq string", seqs)
  case _                 => ("unknown!", x)
})

// 예상
// List((seq double,List(5.5, 5.6, 5.7)), (seq string,List(a, b)))

// 실제
//  List((seq double,List(5.5, 5.6, 5.7)), (seq double,List(a, b)))

// 이유?
/*
JVM의 타입 소거 때문. 이전 코드와 하위 호환상을 위해 JVM의 바이트코드에서는
List의 제너릭 타입이 가지는 타입 매개변수 정보가 없기 확인 불가
 */

/* 해결방안 */

val test = List(5.5,5.6,5.7)
test match {
  case head +: re =>
    print("Haed : " + head)
    print("re : " + re)
}

def doSeqMatch[T](seq: Seq[T]): String = seq match {
  case Nil => "Nothing"
  case head +: _ => head match {
    case _ : Double => "Double"
    case _ : String => "String"
    case _ => "Unmatched seq element"
  }
}

for {
  x <- Seq(List(5.5,5.6,5.7), List("a", "b"), Nil)
} yield {
  x match {
    case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
    case _           => ("unknown!", x)
  }
}