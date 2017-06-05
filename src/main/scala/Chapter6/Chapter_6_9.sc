// 6.9 왼쪽 순회와 오른쪽 순회

// * + 는 결합 법칙과 교환 법칙이 성립한다.
// 결합법칙 : 앞쪽의 연산을 먼저 계산한 값과 뒤쪽의 연산을 먼저 계산한 결과가 항상 같을 경우
// 교환법칙 : 두 대상의 이항연산의 값이 두 원소의 순서에 관계없다는 성질이다.
// fold
(List(1,2,3,4,5) fold 10) (_ * _)
(List(1,2,3,4,5) foldLeft 10) (_ * _) // 첫번째 원소가 누적값
(List(1,2,3,4,5) foldRight 10) (_ * _) // 두번째 원소가 누적값

List(1,2,3,4,5).foldLeft(10) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m * n }
List(1,2,3,4,5).foldRight(10) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m * n }


// reduce
List(1,2,3,4,5) reduce (_ + _)
List(1,2,3,4,5) reduceLeft (_ + _)
List(1,2,3,4,5) reduceRight (_ + _)

List(1,2,3,4,5).reduceLeft { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
List(1,2,3,4,5).reduceRight { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }


// foldLeft VS foldRight
val facLeft = (accum: Int, x: Int) => accum + x
val facRight = (x: Int, accum: Int) => accum + x
val list1 = List(1,2,3,4,5)
list1 reduceLeft facLeft
list1 reduceRight facRight

//facLeft : 1이 씨앗값이고 왼쪽에서 오른쪽으로 처리
((((1 + 2) + 3) + 4) + 5) //= 15
//facRight : 5가 씨앗값이고 오른쪽에서 왼쪽으로 처리
((((5 + 4) + 3) + 2) + 1) // = 15
(1 + (2 + (3 + (4 + 5)))) // = 15 (원소 순서로 재배열)

// 결합법칙은 성립하지만 교환 법칙이 성립하지 않는 함수
val fncLeft = (accum: Int, x:Int) => accum - x
val fncRight = (x: Int, accum: Int) => accum - x
list1 reduceLeft fncLeft // ((((1 - 2) - 3) - 4) - 5) = -13
list1 reduceRight fncRight // ((((5 - 4) - 3) - 2) - 1) = -5

// 결합법칙 확인
((((1 - 2) - 3) - 4) - 5) // = -13 (fncLeft)
((((1 + -2) + -3) + -4) + -5) // = -13 (fncRight)
(1 + (-2 + (-3 + (-4 + -5)))) // = -13 (fncRight, 재배열한 결과)

// 결합법칙과 교환법칙이 모두 성립하지 않는 함수
val fnacLeft = (x: String, y: String) => s"($x) - ($y)"
val fnacRight = (x: String, y: String) => s"($y) - ($x)"
val list2 = list1 map (_.toString) // String의 리스트를 만듦
list2 reduceLeft fnacLeft // ((((1 - 2) - 3) - 4) - 5) = -13
list2 reduceRight fnacRight // ((((5 - 4) - 3) - 2) - 1) = -5
list2 reduceRight fnacLeft // (1) - ((2) - ((3) - ((4) - (5)))) = 3
