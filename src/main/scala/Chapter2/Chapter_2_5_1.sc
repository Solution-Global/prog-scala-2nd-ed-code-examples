///////////////////////////////////////
// 2.5.1 메서드의 기본 인자와 이름 붙은 인자
///////////////////////////////////////

case class Point(x: Double = 0.0, y: Double = 0.0) {

  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy (x + deltax, y + deltay)
}

val p1 = new Point(y=6.6) // 기본 값 사용

val p2 = new Point(x=3.3, y=4.4) // named argument 사용

val p3 = p2.copy(y=6.6) // case 클래스의 기본 copy method 사용



