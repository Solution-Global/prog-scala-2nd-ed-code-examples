import scala.collection.mutable
///////////////////////////////////////
// 2.7 타입 정보 추론하기
///////////////////////////////////////


// 자바
// HashMap<Integer, String> intToStringMap = new HashMap<Integer, String>();
//  추론을 위해 명시적인 타입이 없어도 문맥상 많은 타입 정보를 얻을 수 있다.
val intToStringMap = new mutable.HashMap[Integer, String]

// 상속으로 인해 모든 타입을 추론 할 수 없다. 명시적으로 타입을 표기 해야하는 항목 책 참조

// 항목에서 첫번째, 두번째, 세번째
abstract class Test() {
    // 1
    val test : String // type 지정 해야함
    var test2  : Int  // type 지정 해야함

   // 2
    def m1(str : String) // 매개변수 type 지정 해야함

   // 3
    def m2()  : String = {
      return "hana" // return으로 명시적으로 호출 할경우
    }
    def m3()  = {
    "hana" // return 값에 대해 type 명시 필요 없음
   }

}

// 4 : 이전 장의 내포된 메서드의 재귀에서 내포 메서드에 return type을 정의하지 않으면 안됨

// 5 : 오버로딩하는 메서드들 중에서 한 메서드가 다른 메서드를 호출 할 경우 명

object StringUtilV1 {
  // String*는 가변 인자 메서드 임
  def joiner(strings: String*): String = strings.mkString("-")

  // def joiner(strings: List[String]) = joiner(strings :_*)   // Error : 컴파일 오류

  /*
 _* 의 의미
 strings 라는 리스트를 가변인자 목록(*)으로 다루되 타입을 알 수 없지만 추론한 타입(String)을 사용 하라임
 String*U
 */
  def joiner(strings: List[String]) : String = joiner(strings :_*)  // return type 명시
}

/*
  가변인자 메서드
  가변인자 오직 하나만 받음
 */
StringUtilV1.joiner("TEST", "Test2")
StringUtilV1.joiner("TEST", "Test2", "Test3")


StringUtilV1.joiner(List("Programming", "Scala"))

// 6. 컴파일러가 추론한 타입이 여러분의 의도보다 어 일반적일 경우

def makeList(strings: String*) = {
  if (strings.length == 0)
    List(0)  // #1
  else
    strings.toList
}

// val list: List[String] = makeList()  // 컴파일 오류(Error)
// List(0)의 의미는 empty가 아닌 List[Int[ 이므로 makeList는 우리의 기대인 List[String]이 아닌 List[Any]이다

// 위 코드를 개선한 코드는 아래임

def makeList1(strings: String*) = {
  if (strings.length == 0)
    List.empty[String] // or Nill // String Empty List를 반환
  else
    strings.toList
}

val list: List[String] = makeList1()


/////// Tip!!!!!!

def dobule1(i: Int) { 2 * i}  // return 값이 Unit이다 예상과 다르다.
def dobule2(i: Int) = { 2 * i}  // return 값이 Int이다. 예상 결과다

// 위의 예제의 경우 메서드를 프로시저(?)로 정의 했다는 의도로 Unit을 반환 2.11부터 사용 중단


