///////////////////////////////////////
// 2.1 변수의 정의
///////////////////////////////////////

/*
  Val 특징
  1. 불가능한 변수
  2. 선언시 반드시 초기화
 */

val array : Array[String] = new Array(5)

// array라는 참조는 다른 Array를 가르키도록 할 수 없다.
// array = new Array(2) // Error

// 하지만 각 원소의 변경이 가능하다.
array(0) = "Hana"

array

/*
  Var 특징
  1. 변경 가능한 변수
  2. 선언시 반드시 초기화
 */


var stockPrice : Double = 100.0
stockPrice.hashCode()
stockPrice.hashCode()
stockPrice = 200.0
stockPrice.hashCode()


/*
자바의 기본타입(char, byte, shorte 등) 근본적으로 객체는 아니고 단지 원래의 값만 존재 한다.
 하지만 스칼라의 모든 기본타입, 메소드 등 모두가 객체이므로 위에 예제는 stocPrice를 가르 키는객체를
 바꾼게 아니라 stocPrice의 자체를 변경 한것이다.
val, var는 선언시 반드시 초기화 하라고 하지만 다음과 같이 생성자 매개변수에서는 예외가 될 수 있다
 */

class Person(val name: String, var age: Int)

val p = new Person("Dean Wampler", 29)

p.name // 이름을 보여준다
p.age  // age 값을 보여준다

// 변경할 수 없다!
p.name = "Buck Trends" // Error
// 변경할 수 있다!
p.age = 30

p
