// 3.7 다른 루프 표현

// 스칼라에서 제공하는 for 문의 내장 기능은 강력함, 다른 루프 구문은 자주 사용되지 않는다.

/*
// 3.7.1
import java.util.Calendar

def isFridayThirteen(cal: Calendar) = {
  val dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)
  val dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

  (dayOfWeek == Calendar.FRIDAY) && (dayOfMonth == 13)
}

while (isFridayThirteen(Calendar.getInstance())) {
  println("Today is Friday the 13th. Lame.")

  //println("Today is not Friday the 13th. Lame.")
  //Thread.sleep(5000)
}
*/

/*
// 3.7.2
var count = 0

do {
  count += 1
  println(count)
} while (count < 10)
*/

// 3.8 조건 연산자
/**
  * && 왼쪽 항이 참인 경우에만 오른쪽 항을 평가
  * || 왼쪽 항이 거짓인 경우에만 오른쪽 항을 평가
  * ==, !=
  * 자바 : 객체 참조 비교, 논리적인 동등성 검사 X, equals() 제공
  * 스칼라 : == 연산 시 내부적으로 equals 메서드 호출, 객체 참조만을 비교 하기 위해 eq 제공
  */

/* 자바 코드
String str1 = new String("java test")
String str2 = new String("java test")

str1.equals(str2)  // true
str1 == str2      // false
*/

// 스칼라
val str1 = new String("scala test")
val str2 = new String("scala test")

str1 eq str2
str1 == str2






