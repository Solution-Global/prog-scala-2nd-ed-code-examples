///////////////////////////////////////
// 3.3 우선순위 규칙
///////////////////////////////////////

// Double에 대한 메서드 호출의 연속
2.0 * 4.0 / 3.0 * 5.0

//연산자 우선 순위 (낮은 우선순위부터)
// 1. 모든 글자
// 2. |
// 3. ^
// 4. &
// 5. < >
// 6. = !  (= 을 대입에 사용하는 경우 가장 낮은 우선순위)
// 7. :
// 8. + -
// 9. * / %
// 10. 다른 모든 특수 문자

// * / 는 같은 우선순위 이므로 두 줄의 값은 같다
2.0 * 4.0 / 3.0 * 5.0
(((2.0 * 4.0) / 3.0) * 5.0)

// scala는 일반 적으로 왼쪽 결합이다.
// 이름이 콜론(:) 으로 끝나는 메서드는 항상 오른쪽으로 묶인다.

// 오른쪽으로 묶이는 연산자 예제
// List의 cons(::) 연산자는 오른쪽부터 계산하여 두번째 리스트에 첫번째 리스트를 prepend하라는 의미
// List의 triple colon(:::) 연산자는 List의 concat과 동일
val list = List('b', 'c', 'd')
'a' :: list
list.::('a') // 이와 같은 순서로 동작한다.

list :+ 'e'
list :: List('e', 'f')
list ::: List('e', 'f')
List('e', 'f').::: (list) // 이와 같은 순서로 동작한다.

// 앞에 붙이기는 :: 또는 +: 연산자
// 뒤에 붙이기는 :+ 연산자 (immutable list 에서 효율적인 방법이 아님)
// 리스트 두개를 붙이기는 ++ 또는 ::: 연산자
