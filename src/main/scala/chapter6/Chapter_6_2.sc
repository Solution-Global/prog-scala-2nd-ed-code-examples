// filter 연산 (2, 4, 6, 8, 10) map 연산 (4, 8, 12, 16, 20) reduce 연산 4 x 8 x 12 x 16 x 20 = 12880
// var 변수를 사용하지 않고도 모든 리스트에 원소에 대해 여러가지 연산을 수행할 수 있다.
(1 to 10) filter (_ % 2 == 0) map (_ * 2) reduce (_ * _)

// factor 값에 따라 함수의 결과가 바뀜(함수 자체는 immutable 하지만 참조하는 값이 다르기 때문)
// i 값은 형식 인자이지만 factor는 자유 변수(주위를 둘러싼 영역에 있는 변수에 대한 참조)
var factor = 2
val multiplier = (i: Int) => i * factor

(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

factor = 3
(1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)

// m2를 호출해서 Int => Int 타입의 함수 값을 반환받은 다음 m1 함수 실행
// factor 값은 m1 외부에 선언되어 있으나 m2 내부적으로 참조하면서 4 x 8 x 12 x 16 x 20에 대한 연산이 가능
// 결국 m2가 반환하는 함수는 factor에 대한 참조까지 포함하는 클로저
def m1 (multiplier: Int => Int) = {
  (1 to 10) filter (_ % 2 == 0) map multiplier reduce (_ * _)
}

def m2: Int => Int = {
  val factor = 2
  val multiplier = (i: Int) => i * factor
  multiplier
}

// result = (2 x 2) x (4 x 2) x (6 x 2) x (8 x 2) x (10 x 2)
m1(m2)

// - 함수: 이름이 붙어 있거나 익명인 연산, 자유 변수가 들어있을 수 있음
// - 람다: 이름이 붙어 있지 않은 함수, 자유 변수가 들어있을 수 있음
// - 클로저: 이름이 붙어있거나 익명인 함수, 자유 변수가 참조하는 모든 영역의 닫힌 환경을 포함
// 클로저는 런타임에 함수 리터럴에 있는 모든 자유 변수에 대한 바인딩을 실행해서 자유 변수가 없는 닫힌 상태를 만들기 때문에 클로저라고 부름
// * 자유 변수: 함수 내에서 선언하거나 인자로 사용하지 않는 변수(ex: 외부 scope 에서 선언한 변수)