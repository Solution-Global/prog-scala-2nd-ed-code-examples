// 3.2 빈 인자 목록이 있는 메서드

List(1, 2, 3).size // size 함수는 괄호가 없기 때문에 괄호를 넣을 경우 에러

def isEven(n: Int) = (n % 2) == 0

// 아래 코드는 모두 같은 의미(단, 함수 목록이 한 개 이상일 경우 축약 불가)

List(1, 2, 3, 4).filter((i: Int) => isEven(i)).foreach((i: Int) => println(i))
List(1, 2, 3, 4).filter(i => isEven(i)).foreach(i => println(i))
List(1, 2, 3, 4).filter(isEven).foreach(println)
List(1, 2, 3, 4) filter isEven foreach println