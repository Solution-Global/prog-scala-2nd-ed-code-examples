///////////////////////////////////////
// 8.6 클래스의 필드
///////////////////////////////////////

//class Name(var value: String)

// 아래는 위의 코드를 명시적으로 표현한 코드이다
class Name(s: String) {
  private var _value: String = s

  // getter
  def value: String = _value

  // setter
  def value_=(newValue: String): Unit = _value = newValue
}

val name = new Name("Buck")

name.value // Buck

name.value_=("Bubba")

name.value // Bubba

name.value = "Hank" // 접근자 메소드를 사용하지 않고도 setter 를 호출하고 있음

name.value // Hank

// 클래스의 필드를 val로 선언하면 value_= 와 같은 메소드가 만들어 지지 않고
// 재할당을 할 시에도 에러가 발생한다

// getter와 setter를 직접 구현하고 싶다면 위와 같은 방식으로 구현 하면 된다.

// class의 생성자에는 필드가 아닌 인자를 생성할 수 있다.



object Pipeline {
  implicit class toPiped[V](value: V) {
    def |>[R] (f: V => R) = f(value) // value: class 생성자의 인자
    // 다른 OO 언어에서의 생성자는 method이기 때문에 생성자 내부에서만 사용할 수 있지만
    // 스칼라에서는 클래스 내부 어디서든지 생성자의 인자를 사용할 수 있다.
  }
}

///////////////////////////////////////
// 8.6.1 단일 접근 원칙
///////////////////////////////////////

// 단순 필드에 대한 읽기와 쓰기가 접근자 메소드(getter, setter)의 역할을 대신함
// 속성에 대한 직관성과 메소드에 의한 캡슐화라는 장점을 살릴 수 있는 단일 접근 원칙을 지원

class Name2(s: String) {
  var value: String = s
}

val name2 = new Name2("Buck")

name2.value // Buck

name2.value_=("Bubba")

name2.value // Bubba

name2.value = "Hanseung"

name2.value // Hanseung


///////////////////////////////////////
// 8.6.2 단항 메서드
///////////////////////////////////////

// src/main/scala/progscala2/basicoop/Complex.sc

case class Complex(real: Double, imag: Double) {
  // unary 연산을 지원하기 위해 unary_X(X에 사용하고자 하는 연산자를 넣는다)라는 메소드를 사용한다.
  def unary_- : Complex = Complex(-real, imag) // colon의 위치에 유의
  def -(other: Complex) = Complex(real - other.real, imag - other.imag)
}

val c1 = Complex(1.1, 2.2)
val c2 = -c1                           // Complex(-1.1, 2.2)
val c3 = c1.unary_-                    // Complex(-1.1, 2.2)
val c4 = c1 - Complex(0.5, 1.0)        // Complex(0.6, 1.2)

