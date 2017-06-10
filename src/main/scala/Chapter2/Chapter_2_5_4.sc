///////////////////////////////////////
// 2.5.4 내포된 메서드 정의와 재귀
///////////////////////////////////////


/*
  내포된 메소드(nest method)
  메서드 본문안에 여러 작은 메서드로 사용 함
  재귀에서 함수에 유용하게 사용
 */

import scala.annotation.tailrec

def factorial(i: Int): Long = {

  // 재귀 함수의 마지막을 제대로 작성했는지 컴파일러가 그 코드를 최적화 할 수 있는지 알기 위해 @tailrec 어노테이션 추가 함
  // 만약 꼬리 재귀가 아닌 경우 에러 발생 함
  @tailrec
  def fact(i: Int, accumulator: Int): Long = {
    if (i <= 1) accumulator
    else fact(i - 1, i * accumulator)
  }

  fact(i, 1)
}

(0 to 5) foreach ( i => println(factorial(i)) )
