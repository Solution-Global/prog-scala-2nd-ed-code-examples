///////////////////////////////////////
// 4.4. 튜플에 일치시키기
///////////////////////////////////////

// 튜플의 리터럴 문법을 활용하면 패턴 매칭이 쉽다.
val langs = Seq(
    ("Scala", "Martin", "Odersky"),
    ("Clojure", "Rich", "Hickey"),
    ("Lisp", "John", "McCarthy")
)

for (tuple <- langs) {
    tuple match {
        case ("Scala", _, _) => println("Found Scala") // 첫번째 원소가 'Scala' 문자열인 3원소 튜플
        case (lang, first, last) => println(s"Found other language: $lang ($first, $last)") // 원소 타입과 관계없이 3원소 튜플
    }
}