// 5.5. 암시와 관련된 기술적인 문제
// 암시를 쓰려면 개발자 입장에서는 코드를 추가적으로 작업해야 하고,
// 컴파일러도 암시 처리를 위해 더 많은 작업을 수행해야 하고, 빌드 시간도 늘어남
// 암시적 변환은 런타임에 추가비용이 필요

trait Stringizer[+T] {
  def stringize: String
}

// a가 Any타입이므로 switch문에 들어오는게 부적절함
// 굳이 implicit로 하면서 모든 타입에 대한 처리를 할수밖에 없으므로 implicit의 이점 사라짐
implicit class AnyStringizer(a: Any) extends Stringizer[Any] {
  def stringize: String = a match {
    case s: String => s
    case i: Int => (i*10).toString
    case f: Float => (f*10.1).toString
    case other =>
      throw new UnsupportedOperationException(s"Can't stringize $other")
  }
}

val list: List[Any] = List(1, 2.2F, "three", 'symbol)

list foreach { (x:Any) =>
  try {
    println(s"$x: ${x.stringize}")
  } catch {
    case e: java.lang.UnsupportedOperationException => println(e)
  }
}

// 잠재적 문제를 피하기위한 방법
// 언제나 암시적 변환 메서드의 반환 타입을 명시하라. (타입추론을 하면 원하지 않는 결과가 나올 수 있음)
// 어떤 타입에 + 메서드를 정의하고 사용할 때 +가 더하기가 아닌 string 이어붙이기로 동작할 수 있음.
// 이게 컴파일러에서 타입추론을 지 맘대로 해버려서 그런거임

def m(pair:Tuple2[Int, String]) = println(pair)
m(1, "two")
// (1, "two") 이거를 컴파일러가 두개의 인자로 보지 않고 하나의 튜플로 봄. 자동튜플화!


// 5.6. 암시 해결 규칙
// 암시라는거 자체가 모호하다보니 문제가 발생할 소지가 많아서
// 컴파일러는 아래의 순서대로 암시를 찾아서 문제를 피하려고 함.
// 1. 현재 범위 안으로 임포트된 값을 먼저 검색
// 2. 경로접두사가 필요하지 않은, 타입이 호환되는 모든 암시적 값을 검색
//    동일한 패키지 안에 들어있는 코드, 또는 같은 코드 블, 같은 타입, 동반 객체, 부모 타입안에 정의된 값을 검색
// 타입이 호환되면 더 구체적인 값을 사용
// 두개 이상의 암시적 값이 모호할 경우 컴파일러 오류 발생
//class Reads[A] {
//  def read(s: String): A = throw new Exception("not implemented")
//}
//implicit val fooReads = new Reads[Foo]
//implicit val barReads = new Reads[Bar]
//def convert[A](s: String)(implicit reads: Reads[A]): A = reads.read(s)
//def f(s: String): Foo = convert(s)
// implicit reads 에서 어떤 Reads를 가져와야 할지 알 수 없음(Reads[Foo]? Reads[Bar]?)