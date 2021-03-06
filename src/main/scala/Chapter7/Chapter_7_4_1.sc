
// 7.4 Option과 다른 컨테이너 타입
// 컬렉션 타입이 아니라도 foreach, map, flatMap, withFilter를 구현한 타입에 대해서 for 내장에 사용할 수 있다.

// 7.4.1 컨테이너로서의 Option
/* Option에서 정의한 4가지 method
sealed abstract class Option[+A] { self => // 1
    def isEmpty: Boolean // Some이나 None이 구현한다.

    final def foreach[U](f: A => U): Unit =
        if (!isEmpty) f(this)

    final def map[B](f: A => B): Option[B] =
        if (isEmpty) None else Some(f(this.get))

    final def flatMap[B](f: A => Option[B]): Option[B] =
        if (isEmpty) None else f(this.get)

    final def filter(p: A => Boolean): Option[A] =
        if (isEmpty || p(this.get)) this else None

    final def withFilter(p: A => Boolean): WithFilter = new WithFilter(p)

    class WithFilter(p: A => Boolean) {
        def map[B](f: A => B) : Option[B] = self filter p map f // 2
        def flatMap[B](f: A => Option[B]): Option[B] = self filter p flatMap f
        def foreach[U](f: A => U): Unit = self filter p foreach f
        def withFilter(q: A => Boolean): WithFilter = new WithFilter(x => p(x) && q(x))
    }
}
*/
// 1: self => 식은 Option의 인스턴스가 사용할 this에 대한 별명을 지정한다. WithFilter 안에서 활용
// 2: 위에서 정의한 self 참조를 사용해서 WithFilter가 아니라 그 클래스를 둘러싼 Option 인스턴스에 대해 연산을 수행한다.
//    여기서의 this는 WithFilter 인스턴스 이다.

// Option을 사용하여 분산 작업(하둡의 맵리듀스 처럼 작업을 분산해서 처리해서 결과를 한데 모아 처리)의 결과를 처리할 수 있다.

// 비어 있지 않은 결과만 처리하고 싶을 경우
val results: Seq[Option[Int]] = Vector(Some(10), None, Some(20))

val results2 = for {
    Some(i) <- results
} yield (2 * i)

// 변환 단계 #1 (withFilter)
val results2b = for {
    Some(i) <- results withFilter {
        case Some(i) => true
        case None => false
    }
} yield (2 * i)

// 변환 단계 #2
val results2c = results withFilter {
    case Some(i) => true
    case None => false
} map {
    case Some(i) => (2 * i)
}

// None이 발생하면 과정을 중단
def positive(i: Int): Option[Int] = if (i > 0) Some(i) else None

for {
    i1 <- positive(5)
    i2 <- positive(10 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(2 * i3)
} yield (i1 + i2 + i3 + i4)

for {
    i1 <- positive(5)
    i2 <- positive(-1 * i1)
    i3 <- positive(25 * i2)
    i4 <- positive(-2 * i3)
} yield (i1 + i2 + i3 + i4)


// 제너레이터가 하나 이상 있는 for 내장의 경우 flatMap 으로 변환된다.
for {
    i1 <- positive(5)
    i2 <- positive(10 * i1)
} yield (i1 + i2)

positive(5).map(i1 => i1)
positive(5).flatMap(i1 => positive(10* i1).map(i2 => (i1 + i2)))
positive(5).flatMap(i1 => positive(10* i1).flatMap(i2 => positive(25 * i2).flatMap(i3 => positive(2 * i3).map(i4 => (i1 + i2+i3+ i4)))))


// 제너레이터가 하나만 있고 맨 끝에 yield가 있는 for 내장은 다음과 같이 map으로 변환된다.
val x = Some(99)
for (i <- x ) yield i + 1
x map (_ + 1)
x map (i => i + 1)