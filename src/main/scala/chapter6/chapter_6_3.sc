import scala.annotation.tailrec

// @tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")

// 반복된 함수 호출로 인항 성능상의 부가 비용
// stack overflow 위험성