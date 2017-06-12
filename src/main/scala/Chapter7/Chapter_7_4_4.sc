import scalaz._, std.AllInstances._

// List[String]으로 failure 처리, Int로 success 처리
def positive(i: Int): Validation[List[String], Int] = {
  if (i > 0) Success(i)                                              // <1>
  else Failure(List(s"Nonpositive integer $i"))
}

for {
  i1 <- positive(5)
  i2 <- positive(10 * i1)
  i3 <- positive(25 * i2)
  i4 <- positive(2  * i3)
} yield (i1 + i2 + i3 + i4)
// Returns: scalaz.Validation[List[String],Int] = Success(3805)

for {
  i1 <- positive(5)
  i2 <- positive(-1 * i1)              // EPIC FAIL!
  i3 <- positive(25 * i2)
  i4 <- positive(-2 * i3)              // EPIC FAIL!
} yield (i1 + i2 + i3 + i4)
// Returns: scalaz.Validation[List[String],Int] =
//   Failure(List(Nonpositive integer -5))                           // <2>

// 중간에 실패하면 short circuit이라고 하여 멈춤
// 일단 다 확인해볼 수는 없을까?


// +++의 정의
/**
  * Sums up values inside validation, if both are success or failure. Returns first failure otherwise.
  * {{{
  * success(v1) +++ success(v2) → success(v1 + v2)
  * success(v1) +++ failure(v2) → failure(v2)
  * failure(v1) +++ success(v2) → failure(v1)
  * failure(v1) +++ failure(v2) → failure(v1 + v2)
  * }}}
  */

positive(5) +++ positive(10) +++ positive(25)                        // <3>
// Returns: scalaz.Validation[String,Int] = Success(40)

positive(5) +++ positive(-10) +++ positive(25) +++ positive(-30)     // <4>
// Returns: scalaz.Validation[String,Int] =
//   Failure(Nonpositive integer -10, Nonpositive integer -30)


/*
// 사용자 이름 검증. 알파벳만. 공백X
def validName(key: String, name: String):
Validation[List[String], List[(String,Any)]] = {
  val n = name.trim  // remove whitespace
  if (n.length > 0 && n.matches("""^\p{Alpha}$""")) Success(List(key -> n))
  else Failure(List(s"Invalid $key <$n>"))
}

// 문자열이 정수이고, 0보다 커야함
def positive(key: String, n: String):
Validation[List[String], List[(String,Any)]] = {
  try {
    val i = n.toInt
    if (i > 0) Success(List(key -> i))
    else Failure(List(s"Invalid $key $i"))
  } catch {
    case _: java.lang.NumberFormatException =>
      Failure(List(s"$n is not an integer"))
  }
}

// success의 경우 List[(String, Any)] 형식으로 받는다. String에 key, Any에 value
// failure의 경우 List[String]으로 누적
def validateForm(firstName: String, lastName: String, age: String):
Validation[List[String], List[(String,Any)]] = {
  validName("first-name", firstName) +++ validName("last-name", lastName) +++
    positive("age", age)
}

validateForm("Dean", "Wampler", "29")
// Returns: Success(List((first-name,Dean), (last-name,Wampler), (age,29)))
validateForm("", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <>))
validateForm("D e a n", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <D e a n>))
validateForm("D1e2a3n_", "Wampler", "29")
// Returns: Failure(List(Invalid first-name <D1e2a3n_>))
validateForm("Dean", "", "29")
// Returns: Failure(List(Invalid last-name <>))
validateForm("Dean", "Wampler", "0")
// Returns: Failure(List(Invalid age 0))
validateForm("Dean", "Wampler", "xx")
// Returns: Failure(List(xx is not an integer))
validateForm("", "Wampler", "0")
// Returns: Failure(List(Invalid first-name <>, Invalid age 0))
validateForm("Dean", "", "0")
// Returns: Failure(List(Invalid last-name <>, Invalid age 0))
validateForm("D e a n", "", "29")
// Returns: Failure(List(Invalid first-name <D e a n>, Invalid last-name <>))
*/