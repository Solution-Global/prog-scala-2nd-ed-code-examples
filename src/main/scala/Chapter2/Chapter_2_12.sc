///////////////////////////////////////
// 2.12 타입과 멤버 임포트하기
///////////////////////////////////////

import java.awt._
import java.io.File
import java.io.File._
import java.util.{Map, HashMap}

def stuffWithBigInteger() = {
  import java.math.BigInteger.{
  ONE => _,
  TEN,
  ZERO => JAVAZERO
  }

  // 사용 못함
  // println(ONE) // Error
  println(TEN)
  // println(ZERO) // Error
  // 별명으로만 사용 가
  println(JAVAZERO)
}

// 2.12.1 임포트는 상대적이다.

import scala.collection.mutable._
import collection.immutable._              // 'scala'는 항상 임포트된 상태다.
import _root_.scala.collection.parallel._  // 실제 최상위 패키지로부터의 전체 경로다.

// 2.12.2 패키지 객체
/*
  패키지의 최상위 영역에 존재하는 특별한 object인 package object를 지원한다.
 */
import day1.json._
test("test")

