/*
  암시적 변환
  모든 객체..(Int, String, Boolean 등)에 메서드를 추가하는 것은 현명하지 않고 실용적이지 않다.
  ㄹ
 */
(1, "one")
1 -> "one"
(1 → "one")
Tuple2(1, "one")
Pair(1, "one")


// 위 화살표는 아래의 implicitf를 가지는 클래스가
new ArrowAssoc("onee") -> 1

// 위 화살표가 동작 순서
/*
1. String에서 -> 메소드를 찾으려고 시도 한다.
2. 없으면 범위 안의 String을 -> 메서드를 제공하는 어떤 타입으로 변화하는 암시적 변환을 찾는다.
3. Predef에 있는 ArrowAssoc를 찾음
4. "one"을 넘겨서 새객체를 만듬
5. 그 후 -> 메서드를 리xjs

 */

// 암시적 변환 주의 사항
/*
1.  인자가 하나만 받는 생성자 클래스
2. 인자를 단 하나만 받는 메서드
 */

// 암시적 변환 검색 규칙
/*
1. 타입 검사를 통해 모두 통과시 임시적 변환 사용 안함
2. implicit 키워드 사용
3. 현재 범위 안에 있는 암시적 클래스와 메서드만 고려 or 대상 타입의 동반 객체에 전의된 메서드 고려
4. 암시적 메서드를 둘이상 조합 안함
5. 암시적 변환이 둘 이상일 때 변환 수행 안함
 */


// src/main/scala/progscala2/implicits/implicit-conversions-resolution.sc
import scala.language.implicitConversions // 암시적 변환은 선택 사항

// WARNING: You must :paste mode in the REPL for the following.
// Using :load won't compile the two definitions together!
case class Foo(s: String)
object Foo { // Foo의 동반 객체
  implicit def fromString(s: String): Foo = Foo(s) // 동반 객체의 암시적 메서드
}

class test {
  def m1(foo: Foo) = println(foo)
  def m(s: String) = m1(s)
}

val abc = new test
abc.m("test") // Foo(test)

/*
Foo 전달 하는 이유?
범위 안에 implicit가 없음에도 불구하고 m1에서 받는 Foo의 동반객체 안에 impliciti 메서드가 있어 처리됨
 */


case class Foo1(s: String)
object Foo1 {
  implicit def fromString(s: String): Foo1 = Foo1(s)
}

// 동반 객체보다 범위 안이 더 우선 순위 됨
implicit def overridingConversion(s: String): Foo1 = Foo1("Boo: "+s)

class test2 {
  def m1(foo: Foo1) = println(foo)
  def m(s: String) = m1(s)
}

val d = new test2
d.m("test") // Foo1(Boo: test) 출력 됨


// 아래의 String의 메서드가 아니라 암시적 메서드를 선언한 WrappedString 이다.

val s = "test"
s.reverse
s.capitalize
s.foreach(c => println(s + "/"))