///////////////////////////////////////
// 2.5.2 인자 목록이 여럿 있는 메서드
///////////////////////////////////////

/*
  장점
  1. 블록 구조 구문 사용
  2. 타입 추론 가능
  3. 마지막 인자 목록은 암시적 인자로 사용(다음 챕터에 설명)
 */

case class Point(x: Double = 0.0, y: Double = 0.0) {

  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy (x + deltax, y + deltay)
}

abstract class Shape() {
  /**
    * 두개의 인자 목록을 받는다.
    * 한 인자 목록은 그림을 그릴 때, x, y 축 방향으로 이동시킬 오프셋 값이고,
    * 나머지 인자 목록은 함수 인자
    */
  def draw(offset: Point = Point(0.0, 0.0))(f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")

  def draw2(offset: Point = Point(0.0, 0.0), f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, height: Double, width: Double)
  extends Shape


// 장점 1

val s = Circle(Point(), 2.33)

// 사용 방법
s.draw(Point(1.0, 2.0))(str => println(s"ShapesDrawingActor : $str"))

// 함수인자는 {} 사용 가능
s.draw(Point(1.0, 2.0)){str => println(s"ShapesDrawingActor : $str")}

// 줄바꿈 가능
s.draw(Point(1.0, 2.0)){str =>
  println(s"ShapesDrawingActor : $str")
}

// 줄바꿈 가능
s.draw(Point(1.0, 2.0)){
  str => println(s"ShapesDrawingActor : $str")
}

// 기본인자가 있더라도 인자 목록에서는() 생략 안됨
s.draw(){
  str => println(s"ShapesDrawingActor : $str")
}

// 인자목록이 아닌 인자로 선언시 함수는 아래와 같이 사용하지만 블록코드 표현을 위해 인자목록 추천
s.draw2(Point(1.0, 2.0), str => println(s"ShapesDrawingActor : $str"))
// 인자들 함수 인자는 named argument 사용 가능
s.draw2(f= str => println(s"ShapesDrawingActor : $str"))


// 장점 2

def m1[A](a : A, f: A => String) = f(a) // 추론 안됨
def m2[A](a : A)(f: A => String) = f(a)  // 추론 가능

// missing parameter type error 발생
// m1(100, i => s"$i + $i") // Error
m2(100)(i => s"$i + $i")
