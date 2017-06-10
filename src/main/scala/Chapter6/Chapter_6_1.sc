// 6.1 함수형 프로그래밍

// 객체 지향 프로그래밍에서의 함수 -> 재사용 가능한 로직
// 함수형 프로그래밍에서의 함수 -> 객체로 취급되며 순수 함수(pure function)로 구현한다.
// 이는 부수 효과(side-effect)가 없는 함수를 의미한다. -> 함수 내부에서 다른 전역 상태를 일체 변경하지 않는다.
// 순수 함수의 장점: 함수 간 상호 영향이 없기 때문에 분석, 테스트, 디버깅이 쉬워진다.

// 6.1.1 수학 함수
// A. 참조 투명성
// 함수를 아무 곳에서나 호출할 수 있고 주변 환경에 상관없이 항상 같은 방식으로 동작
// 어떤 식이 두 번 이상 반복될 때 맨 처음에 얻은 결과로 반복된 식을 안전하게 치환할 수 있다
// B. 일급 계층(first class)
// 함수를 다른 함수로 부터 합성 가능(tan(x) = sin(x) / cos(x))
// C. 고차 함수(higher-order function)
// 다른 함수를 인자로 받는 함(colㅣection의 map)

// 6.1.2 값이 바뀌지 않는 변수(immutable)
// 순서 중심의 프로그래밍 방식에서는 변수 값이 변할 수 있지만 함수형 프로그래밍 방식에서는 변수 값이 변하지 않는다.
// 리스트를 생성한 이 후 추가/삭제를 하는 경우 추가 리스트를 새로 만들고 삭제된 리스트를 새로 만드는 방식(persistent data structure)
// 처리할 데이터가 불변이기 때문에 modify(state)가 아닌 newState = modify(state)와 같은 개념

// Factorial 함수 예
def factorial(i: Int): Long = {
  def fact(i: Int, accumulator: Long): Long = {
    if (i <= 1) accumulator
    else fact(i - 1, i * accumulator)
  }

  fact(i, 1L)
}

(0 to 5) foreach ( i => println(factorial(i)) )

// 재귀 호출을 이용해서 계승 값을 계산 -> accmulator에 대한 변경은 stack에 쌓이나 계산하는 과정에서 메모리의 한 위치에 있는 값을 변경하지 않음
// 마지막에 결과를 출력하면서 상태를 변경하나 가능한 한 순수성을 유지하는 것이 좋다
// 불변성은 동시성을 사용해서 규모 확장이 가능한 코드를 작성할 때 유용
// - Multi-thread 환경에서는 공유된 변경 가능한 상태에 대한 접근을 동기화하는 것이 어려움

// 복잡성을 다루기 쉬워짐
// 고차 순수 함수 == combinator
// 함수들이 레고 블록처럼 잘 조합될 수 있음

// 순수 함수와 불변성은 버그가 발생하는 빈도를 극적으로 감소시켜준다.
// 순수성
// 객체지행 코드에 필요한 여러 방어적 준비 코드를 제거할 수 있음 -> 설계의 단순화
// 변경 불가능한 데이터 구조
//  상태 변화로 인한 대부분의 문제가 사라짐
//  상태 변경을 원할 때는 복사를 해야함
// 함수형 데이터 구조를 사용하면 두 복사본 사이에 변화가 없는 부분을 공유하는 방식을 사용해서 오버헤드를 줄임

// 스칼라의 스트림 타입과 같은 지연 계산을 통해 성능 향상을 꾀할 수 있다.
// 스칼라는 기본적으로는 미리 계산(eager evaluation) 방식을 사용
// 지연 계산을 기본으로 사용하지 않는 이유? 지연 계산이 효율적이지 못한 경우가 많이 있고 지연 계산의 성능을 예측하는 것이 어렵기 때문
