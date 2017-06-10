///////////////////////////////////////
// 2.8 리터럴 값
///////////////////////////////////////

/*
 정수 리터럴
 Long, Int, Short, Char, Byte 있음
  */

val test: Int = 123
val test2: Long = 123L
val tes3: Long = 123l  // L, l로 Long 구분


/*
 부동소수 리터럴
 Float, Double
  */

val test4 = 3.3 // F, f없은 Double
val test5 = 3.3F  // F, f 가능
val test6 = 3.3d // D, d 가능

/*
 불린 리터럴
 true, false 뿐
 */

/*
 문자 리터럴
 '' 따옴표 사이에 오는 것
 */

/*
 문자열 리터럴
"" 사이에 오는 것
 멀티라인 문자열 리터럴
 """ """ 사이에 오는
 */

// 멀티라인 예제이며 stripMargin 메소드를 사용하면 |를 포함 앞의 공백을 지우고 첫자리로 이동
def hello(name: String) = s"""Welcome!
  Hello, $name!
  * (Gratuitous Star!!)
                             |We're glad you're here.
                             |  Have some extra whitespace.""".stripMargin

hello("Programming Scala")

// 접두사와 접미사를 제거 하고 싶을 때 stripPrefix, stripPrefix 사용
def goodbye(name: String) =
  s"""xxxGoodbye, ${name}yyy
  xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")

goodbye("Programming Scala")

/*
심벌 리터럴
예제 참고
 */

scala.Symbol("id")
'id

/*
함수 리터럴
 */
// 아래 2개 선언 방법은 동일
val f1 : (Int, String) => String = (i, s) => s+i
val f2  :Function2[Int, String, String] = (i, s) => s+i


/*
튜플 리터럴
- 메서드의 둘 이상의 값을 반환하고 싶을 때 튜플이 유용
- 1~22개만 가능 하다
- 변경 불가능 하다
 */


val t = ("Hello", 1, 2.3)
println( "Print the whole tuple: " + t )
println( "Print the first item:  " + t._1 )
println( "Print the second item: " + t._2 )
println( "Print the third item:  " + t._3 )

val (t1, t2, t3) = ("World", '!', 0x22)
println( t1 + ", " + t2 + ", " + t3 )

val (t4, t5, t6) = Tuple3("World", '!', 0x22)
println( t4 + ", " + t5 + ", " + t6 )



(1, "one")
1 -> "one"
Tuple2(1, "one")


