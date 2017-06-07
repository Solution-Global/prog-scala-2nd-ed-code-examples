///////////////////////////////////////
// 6.8.1 순회
///////////////////////////////////////

// src/main/scala/progscala2/fp/datastructs/foreach.sc

// foreach는 단순히 콜렉션의 원소들을 순회하기 위한 용도
// Unit 타입을 리턴. 순전히 side effect만을 위한 method

//def foreach[U](f: A => U): Unit =
//  iterator.foreach(f)

List(1, 2, 3, 4, 5) foreach { i => println("Int: " + i) }

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

// Map의 경우, 위 정의의 A는 (key, value) 의 tuple을 의미
//stateCapitals foreach { kv => println(kv._1 + ": " + kv._2) }
stateCapitals foreach { case (k, v) => println(k + ": " + v) }


///////////////////////////////////////
// 6.8.2 연관시키기 (map)
///////////////////////////////////////

// map method는 원래 콜렉션과 같은 크기, 같은 데이터 구조의 새로운 콜렉션을 반환한다.
// 원래 콜렉션의 원소들에 특정 함수를 적용하여 얻어진 결과들을 리턴

val nums: Seq[Int] = List(1, 2, 3, 4, 5, 6, 7)

val strs: Seq[String] = nums.map(n => n.toString)


// src/main/scala/progscala2/fp/combinators/combinators.sc

// List의 map의 정의는 아래와 같다
// final def map[B](f: (A) ⇒ B): List[B]

// 하지만 map의 실제 동작은 List[A] => List[B]이다(같은 크기, 같은 데이터 구조의 컬렉션 반환)
// 이런 동작은 list.map(f) 와 같은 표현에 의해 가려진다

object Combinators1 {
  def map[A,B](list: List[A])(f: (A) ⇒ B): List[B] = list map f
}

// 부분 적용을 위해 인자 목록 바꿈
object Combinators {
  def map[A,B](f: (A) ⇒ B)(list: List[A]): List[B] = list map f
}

val intToString: (Int) => String = (i:Int) => s"N=$i"
// Result: intToString: Int => String = <function1>

// flist : Comninators.map을 부분 적용하여 만든 새로운 함수
// Int => String 인 intToString을 List[Int] => List[String]인 flist로 lift
val flist: (List[Int]) => List[String] = Combinators.map(intToString)(_)
// Result: flist: List[Int] => List[String] = <function1>

val list: Seq[String] = flist(List(1, 2, 3, 4))
// Result: list: List[String] = List(N=1, N=2, N=3, N=4)

///////////////////////////////////////
// 6.8.3 펼치면서 연관시키기 (flatMap)
///////////////////////////////////////

// src/main/scala/progscala2/fp/datastructs/flatmap.sc

val listf = List("now", "is", "", "the", "time")

listf flatMap (s => s.toList)

// Like flatMap, but flatMap eliminates the intermediate step!
// flatMap은 map을 수행한 후 flatten 수행한 것과 같은 결과를 나타낸다
val list2 = List("now", "is", "", "the", "time") map (s => s.toList)
list2.flatten

// flatMap에서 flatten 과정은 원소를 한 단계만 펼친다.

val l1 = List(List(List(1, 2), List(3, 4)), List(List(5, 6), List(7, 8)), List(List(9, 10), List(11, 12)))

l1.flatMap(s => s.toList)

///////////////////////////////////////
// 6.8.4 걸러내기 (filter)
///////////////////////////////////////

// src/main/scala/progscala2/fp/datastructs/filter.sc

val st = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  "Wyoming" -> "Cheyenne")

// 해당 술어를 만족하는 모든 원소들의 컬렉션을 반환
st.filter { kv => kv._1 startsWith "A" }

// filter의 반대
st.filterNot { kv => kv._1 startsWith "A" }

// 해당 술어를 만족하는 첫번째 원소를 Option으로 반환 반환.
st.find { kv => kv._1 startsWith "A" }

// 해당 술어를 만족하는 원소가 단 하나라도 있으면 true, 아니면 false 반환
st.exists { kv => kv._1 startsWith "A" }

// 컬렉션 안의 모든 원소가 술어를 만족하면 true, 아니면 false
st.forall { kv => kv._1 startsWith "A" }

// 맨 앞의 n개의 인덱스를 선택
st.take(1)

// 맨 앞의 n개의 인덱스를 제외한 나머지를 선택
st.drop(1)


///////////////////////////////////////
// 6.8.5 접기와 축약시키기 (fold, reduce)
///////////////////////////////////////

// src/main/scala/progscala2/fp/datastructs/foldreduce.sc

List(1,2,3,4,5,6) reduce (_ + _)
List(1,2,3,4,5,6) reduce (_ * _)

// 기준(씨앗값)은 10
// 씨앗값에 1부터 6까지 곱한 수를 반환
List(1).fold (10) (_ * _)           // 10 * 1
List(1,2).fold (10) (_ * _)         // 10 * 1 * 2
List(1,2,3).fold (10) (_ * _)       // 10 * 1 * 2 * 3
List(1,2,3,4).fold (10) (_ * _)     // 10 * 1 * 2 * 3 * 4
List(1,2,3,4,5).fold (10) (_ * _)   // 10 * 1 * 2 * 3 * 4 * 5
List(1,2,3,4,5,6).fold (10) (_ * _) // 10 * 1 * 2 * 3 * 4 * 5 * 6

(List(1,2,3,4,5,6) fold 10) (_ * _)

val fold1 = (List(1,2,3,4,5,6) fold 10) _ // 부분 적용 함수 생성
fold1(_ * _)

// fold에 빈 콜렉션을 사용할 경우 기준값이 그대로 반환
(List.empty[Int] fold 10) (_ + _)

// reduce에 빈 콜렉션을 사용할 경우 exception 던짐(fold와 달리 기준값이 없음)
try {
  List.empty[Int] reduce (_ + _)
} catch {
  case e: java.lang.UnsupportedOperationException => e
}

// reduceOption을 사용하면 Option으로 값을 반환받을 수 있으므로 Empty collection을 위한 추가적인 에러 처리를 하지 않아도 된다
List.empty[Int] reduceOption (_ + _)
List(1,2,3,4,5,6) reduceOption (_ * _)

val numbers = List(1, 2, 3, 4, 5)

// foldLeft는 콜렉션의 첫번째 원소부터, foldRight는 마지막 원소부터 동작
numbers.foldLeft(0){ (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
numbers.foldRight(0){ (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }

// 연속적으로 연산을 적용한 결과를 갖는 콜렉션을 반환
numbers.scan(0)(_ + _)
numbers.sum // = numbers.reduce(_ + _)
numbers.product // = numbers.reduce(_ * _)

numbers.mkString
numbers.mkString(" @ ")
numbers.mkString("Start", " @ ", "End")