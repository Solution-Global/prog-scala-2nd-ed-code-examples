import scala.util.{ Try, Success, Failure }

// Try는 sealed abstract class로 서브클래스 Success, Failure를 가
// Either와 달리 타입파라미터가 한개 Try[+T]
// 즉, Success인 경우만 T에 대해 동작하고 Failure인 경우는 Throwable

def positive(i: Int): Try[Int] = Try {
  assert (i > 0, s"nonpositive number $i")
  i
}

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2 * i3)
} yield(i1+i2+i3+i4)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)
} yield(i1+i2+i3+i4)

//def positive(i: Int): Try[Int] =
//  if (i > 0) Success(i)
//  else Failure(new AssertionError("assertion failed"))

// Try를 쓰게 되면 left, right 둘 다 신경쓸 필요 없고 success 케이스만 신경써도 됨
