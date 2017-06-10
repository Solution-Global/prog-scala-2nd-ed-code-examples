import scala.language.postfixOps

// 3.1 연산자 오버로딩
// 일반적인 방식
1.toString

// 후위 표기 호출
1 toString

/* 식별자 */
// 문자는 알파벳, 숫자, 밑줄, 달러 기호 사용 가능
// 괄호 문자, 예약어는 사용 불가

// 일반 식별자
val xyz_++ = 1 // 컴파일 가능(xyx_+ 식별자에 1을 대입)
// val xyz++= = 1 // 컴파일 에러(xyz 식별자 뒤에 무언가를 덧붙이는 xyz ++=로 해석될 수 있음)

// 역작은따옴표 리터럴
def `test taht addtion works` = assert(1 + 1 ==2) // 이름을 길게 붙일 때 사용 가능

// 패턴 매칭 식별자(소문자로 시작하는 토큰은 변수 식별자, 대문자로 시작하는 토큰은 상수 식별자로 취급)
object patternMatching {
  def receive(input:Any): Unit = input match {
    case s: String => println("String")
    case i: Integer => println("Integer")
  }
}

// 3.1.1 편의 구문
// !(tell) 구문을 이용해서 actor간 비동기 메시지 전달