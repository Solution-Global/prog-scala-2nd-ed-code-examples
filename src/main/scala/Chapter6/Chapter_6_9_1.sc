// 6.9.1 꼬리 재귀와 무한 컬렉션에 대한 순회
// 꼬리재귀는 어떤 함수가 자기 자신을 호출하되 그 호출이 함수의 마지막 연산인 경우를 말한다.
// 꼬리재귀는 아주 쉽게 루프(반복문)으로 변경할 수 있다.
/// JVM 자체에서는 꼬리 재귀에 대한 최적화를 지원하지 않지만 스칼라 컴파일러의 @tailrec 어노테이션을 통해 꼬리재귀를 최적화할 수 있다.

((((1 + 2) + 3) + 4) + 5) // reduceLeft
(1 + (2 + (3 + (4 + 5)))) // reduceRight
// foldLeft, reduceLeft 같은 왼쪽 순회가 꼬리 재귀
// reduceRight 는 가장 바깥쪽의 덧셈인 (1 + ...) 는 내부의 모든 연산을 마칠 때까지 끝낼 수 없다.

// 예제
def reduceLeft[A,B](s: Seq[A])(f: A => B) : Seq[B] = {
    @annotation.tailrec
    def rl(accum: Seq[B], s2: Seq[A]): Seq[B] = s2 match {
        case head +: tail => rl(f(head) +: accum, tail)
        case _ => accum
    }
    rl(Seq.empty[B], s)
}

def reduceRight[A,B](s: Seq[A])(f: A => B): Seq[B] = s match {
    case head +: tail => f(head) +: reduceRight(tail)(f)
    case _ => Seq.empty[B]
}

val list = List(1,2,3,4,5,6)
reduceLeft(list)(i => 2*i)
reduceRight(list)(i => 2*i)

// 무한 컬렉션에 대한 순회
// 왼쪽 재귀는 입력원소와 순서가 반대, 오른쪽 재귀는 순서가 동일
// reduceLeft의 결과에 reverse 를 호출하면 순서는 동일해지나 컬렉션을 2번 순회해서 비용이 크다.
reduceLeft(list)(i => 2*i) reverse

// 오른쪽 재귀 : 최초 N개의 원소만 처리하고 나머지는 버려도 될 경우에 사용
// Stream은 List와는 달리 실제 호출하기 전까지는 tail을 평가하지 않는다는 특징을 가지고 있다.

// 예제 피보나치 수열
import scala.math.BigInt

val fibs: Stream[BigInt] =
    BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map (n => n._1 + n._2)

fibs take 10 foreach (i => println(s"$i"))
