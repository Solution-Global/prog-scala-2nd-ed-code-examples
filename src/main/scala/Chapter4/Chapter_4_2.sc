// 더 구체적인 case가 먼저 나와야 한다!

for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case 1 => "int 1"
    case i: Int => "other int: " + i    // case 다음에 소문자면 값을 담는 변, 대문자면 타입이름으로 간
    case d: Double => "a double: " + x
    case "one" => "string one"
    case s: String => "other string: " + s
    case unexpected => "unexpected value: " + unexpected    // 이게 default 처리
  }
  println(str)
}

// 위치지정자(_) 사용하면 그냥 x 사용 가능
// 'four 는 심볼
for {
  x <- Seq(1, 2, 2.7, "one", "two", 'four)
} {
  val str = x match {
    case 1 => "int 1"
    case _:Int => "other int: " + x
    case _: Double => "a double: " + x
    case "one" => "string one"
    case _: String => "other string: " + x
    case _ => "unexpected value: " + x
  }
  println(str)
}

// case y 가 모든 경우에 대해 처리해버리므로 다음줄은 unreachable!
//def checkY(y: Int): Unit = {
//  for {
//    x <- Seq(99, 100, 101)
//  } {
//    val str = x match {
//      case y => "found y!"
//      case i: Int => "int: " + i
//    }
//    println(str)
//  }
//}
//checkY(100)

// y를 파라미터로 받은건데...이걸 어떻게 쓰나?
// case 다음에 그냥 소문자 쓰면 변수 이름으로 생각하니까 앞에서 정의한 값을 참조하고 싶으면 ` 사용
def checkY(y: Int): Unit = {
  for {
    x <- Seq(99, 100, 101)
  } {
    val str = x match {
      case `y` => "found y!"
      case i: Int => "int: " + i
    }
    println(str)
  }
}
checkY(100)