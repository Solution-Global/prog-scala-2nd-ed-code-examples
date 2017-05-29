///////////////////////////////////////
// 5.7 스칼라가 기본 제공하는 암시
///////////////////////////////////////

///////////////////////////////////////
// 암시 메서드, 값, 타입
// 대부분 암시 메서드이며, 이 메서드들 중 대부분이 타입변환을 위한 것
val a1: Byte = 1
val a2: Char = 'A'
val a3: Short = 1
val a4: Int = 1
val a5: Long = 1
val a6: Float = 1
val a7: BigDecimal = BigDecimal(1)
val a8: BigInt = BigInt(1)
// Option은 원소가 없거나 하나인 리스트로 변환할 수 있다.
val a9: Iterable[Int] = Some(1)
val a10: Iterable[Int] = None

//
// 스칼라의 String 타입은 자바의 String 클래스를 그대로 사용하는데
// contains, split 메서드 자바 String 클래스에서 제공하는 메서드이지만
// reverse, toInt, toBoolean과 같은 메서드들은 제공하지 않는다.
// 대신 스칼라의 StringOps라는 클래스에서 이를 제공하고 있고 이를 사용하는 과정에서 스칼라의 암시적 타입변환이 적용된다.

// 1. StringOps
val str: String = "Test String"
str.split(" ")(0)
str.contains("Test")
"2".toInt
val str2 = str.reverse
// 위와 같은 스칼라의 암시적인 변환 과정을 요약하자면,
// 특정 객체의 메서드를 호출할 때, 컴파일러가 그 객체의 클래스 선언에서 해당 메서드의 정의를 찾지 못하면,
// 컴파일러는 그 객체를 해당 메서드를 정의한 클래스 타입으로 변환하려 시도한 후 메서드를 호출 한다는 것이다.

// 2. ArrayOps[T]
val arr1 = Array(1, 2, 3)
val arr2 = arr1.reverse
// 이런 암시적 변환들이 대부분 Predef.scala에 정의되어 있따.

// 3. tuple2ToZippedOps, tuple3ToZippedOps
val zipped = List(1, 2, 3) zip List(4, 5, 6)
// List((1,4), (2,5), (3,6))
val products = zipped map { case (x, y) => x * y}

// 2 개의 리스트 원소를 가진 튜플
// 원소가 튜플 형태인 리스트
val pair2 = (List(1, 2, 3), List(4, 5, 6))
//pair2 map { case (int1, int2) => int1 + int2}
pair2.invert map { case (int1, int2) => int1 + int2}

val pair = (List(1, 2, 3), List("one", "two", "three"))
val zipped2 = tuple2ToZippedOps(pair)
//tuple2ToZippedOps(pair) map { case (i, j) => (i*2, j.toUpperCase)}
zipped2.invert map { case (i, j) => (i*2, j.toUpperCase)}
///////////////////////////////////////

///////////////////////////////////////
// 스칼라 컬렉션 -> 자바 컬렉션
// DecorateAsJava.scala, WrapAsJava.scala
// 자바 컬렉션 -> 스칼라 컬렉션
// DecorateAsScala.scala, WrapAsScala.scala

import scala.collection.JavaConverters._
val sl_1 = new scala.collection.mutable.ListBuffer[Int]
val jl_1 : java.util.List[Int] = sl_1.asJava
val sl2_1 : scala.collection.mutable.Buffer[Int] = jl_1.asScala

import scala.collection.JavaConversions._
val sl_2 = new scala.collection.mutable.ListBuffer[Int]
val jl_2 : java.util.List[Int] = sl_2
val sl2_2 : scala.collection.mutable.Buffer[Int] = jl_2
///////////////////////////////////////

///////////////////////////////////////
// Ordering[T]
///////////////////////////////////////
// List(1, 2) < List(1, 2, 3)
// error: value < is not a member of List[Int]
import Ordering.Implicits._
List(1, 2) < List(1, 3)
// implicit def seqDerivedOrdering
// Ordering[List[Int]] 타입으로 암시적 변환
// implicit def infixOrderingOps
// Ordering[List[Int]]의 타입일 때 List[Int]에 대해 <, > 와 같은 크기 비교 연산자를 구현한 객체로 암시적 변환을 해준다.