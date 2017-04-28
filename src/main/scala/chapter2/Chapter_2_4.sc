///////////////////////////////////////
// 2.4 부분 함수(PartialFunction)
///////////////////////////////////////

/* 특징
1. case 절에만 들어 갈 수 있다.
2. 전체를 중괄호로 묶어야 한다.
3. case 절에 일치하지 않으면 MatchError 발생
4. isDefinedAtt을 사용하여 MatchError를 줄 일 수 있
5. orElse를 사용하여 연쇄 호출 가능
 */
// 단순 예제

val test   = {
  case s : String => "String!!"
  case i : Int => "Int!!"
  case d : Double => "Dobule!!"
}

//println(test(3))

// 복잡 예제

val pf1: PartialFunction[Any,String] = { case s:String => "YES" }    // PartialFuction을 변수로 선언
val pf2: PartialFunction[Any,String] = { case d:Double => "YES" }

val pf = pf1 orElse pf2    // pf1 실패시 pf2 시도

def tryPF(x: Any, f: PartialFunction[Any,String]): String =
  try { f(x).toString } catch { case _: MatchError => "ERROR!" } // MatchError 발생 여부

def d(x: Any, f: PartialFunction[Any,String]) =
  f.isDefinedAt(x).toString // 해당 객체가 case 절에 선언되어 있는지 확인

println("      |   pf1 - String  |   pf2 - Double  |    pf - All")
println("x     | def?  |  pf1(x) | def?  |  pf2(x) | def?  |  pf(x)")
println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
List("str", 3.14, 10) foreach { x =>
  printf("%-5s | %-5s | %-6s  | %-5s | %-6s  | %-5s | %-6s\n", x.toString,
    d(x,pf1), tryPF(x,pf1), d(x,pf2), tryPF(x,pf2), d(x,pf), tryPF(x,pf))
}
