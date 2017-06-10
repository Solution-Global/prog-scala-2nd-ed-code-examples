///////////////////////////////////////
// 2.1 세미 콜론
///////////////////////////////////////

// 줄이 등호로 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
def equalsign(s: String) =
println("equalsign: " + s);


// 줄이 중괄호를 열고 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
def equalsign2(s: String) = {
  println("equalsign2: " + s)
}

// 줄이 쉼표, 마침표, 연산자로 끝나면 다음 줄에 이어지는 코드가 더 있다는 뜻이다.
// 쉼표와, 마침표로 끝나 구분하는걸 추천
def commas(s1: String,
           s2: String) = Console.
  println("comma: " + s1 +
    ", " + s2)

// 한줄에 여러식을 표현 할 때는 세미콜론으로 구
val test="1"; val test2="2"; val test3="3"
