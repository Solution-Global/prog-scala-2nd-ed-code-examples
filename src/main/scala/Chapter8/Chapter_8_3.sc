// implicit class 사용한 타입 클래스는 class에 새로운 함수를 추가하는 확장을 가능하게 한다.
// 그러나 이렇게 wrapper 타입을 추가하면 기본 타입을 사용하는 것에 비해 성능성 약점

// 스칼라 2.10부터는 value class와 universal trait 개념이 추가
class Dollar(val value: Float) extends AnyVal {
  override def toString = "$%.2f".format(value)
}

val benjamin = new Dollar(100)

// Dollar 가 value class로서 float를 확장하는 dollar 타입을 만들어냄
// 컴파일시에는 Dollar라고 생각하는데 runtime시에는 Float임
// value class는
// 1. 공개된 val 인자가 하나만 있어야 한다. -> 위에선 value
// 2. 인자의 타입이 value class 자체가 될 수 없다 -> 위에선 float
// 3. 매개변수화된 경우라면 @specialized 사용 못함
// @specialized란?
// jvm runtime시 generic이 생략되어 wrapper를 사용하게 되어 boxing, unboxing 으로 잃게 되는 성능문제를 해결하기 위해
// @specialized로 지정된 primitive type을 위한 subclass를 생성. 즉, 클래스를 두개 생성.
// 원하는 타입이 오면 서브클래스를 사용하여 boxing, unboxing을 피함.
class Spec[@specialized(Int) T] {
  private var value: T = _
  def get: T = value
  def put(x: T) = value = x
}

val x = new Spec[Int]
val y = new Spec[Double]
x.put(7)
y.put(7.0)
x.get
y.get

// 4. 주 생성자 외의 다른 생성자가 있으면 안됨
// 5. value class 안에는 메서드만 있어야 함. 다른 val, var 필드는 있으면 안됨.
// 6. equals, hashCode를 override 할 수 없음
// 7. value class 안에 trait, class, 객체 생성 불가
// 8. value class를 상속할 수 없음
// 9. value class는 universal trait만 상속 가능
// 10. value class는 최상위 타입이거나 참조 가능한 객체의 멤버여야 한다.
