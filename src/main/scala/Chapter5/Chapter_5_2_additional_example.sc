// * 암시적 변환이란?
// 기존 객체에 없는 메소드를 추가한 것 처럼 보이게 하는 기법
// 서로를 고려하지 않고 독립적으로 개발한 두 덩어리의 소프트웨어를 한데 묶을 수 있음
// -> 한 타입(외부 라이브러리)을 다른 타입(자체 개발 코드)으로 변경하는데 필요한 변환의 숫자를 줄일 수 있다

// implicit로 선언된 함수나 값은 지역적이지 않다.(클래스나 패키지 제한을 범위에 존재하지 않을 경우 모든 프로젝트에 적용)
// implicit를 찾는 연산으로 인해 컴파일 시간이 길어질 수 있다.

// 예제 1) implicit로 선언된 형변환 메소드를 통해 auto casting 하는 암시적 변환(implicit conversion) 예제
case class Paper(content: String)
case class Book(name: String, code: Int)

// book 객체를 paper 객체로 바꾸는 메소드
implicit def bookToPaper(book: Book): Paper = Paper(s"${book.code} - ${book.name}")

val book: Book = Book("Scala", 1234)
val paper: Paper = book // 암시적으로 bookToPaper 함수를 이용해 Paper 객체로 변환

// 예제 2) implicit parameter(암시적 인자)
// 마지막 인자 목록에만 암시적 인자가 들어갈 수 있다
// implicit 키워드는 인자 목록의 맨 처음에 와야 하며 오직 한 번만 나타날 수 있다.
// 인자 목록이 implicit로 시작하면 그 인자 목록 안의 모든 인자가 암시적 인자가 된다.
class PreferredPrompt(val preference: String)

object Greeter {
  def greet(name: String) (implicit prompt: PreferredPrompt): Unit = {
    println("Welcome, " + name + ". The system is ready.")
    println(prompt.preference)
  }
}

object JoesPrefs {
  // implicit 구문이 없으면 컴파일러가 빠진 parameter 목록을 찾을 때 고려하지 않음
  implicit val prompt = new PreferredPrompt("Yes, master> ")
}

import JoesPrefs._

// greet 함수에 prompt 값을 전달하지 않았으나 implicit 선언에 의해 컴파일러가 자동으로 JoesPrefs의 prompt 값을 전달
Greeter.greet("Joe")

// Java swing 예제를 scala로 구현
// Button에 actionevent를 설정하기 위한 코드가 많다
import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent

val button = new JButton
button.addActionListener(
  new ActionListener {
    def actionPerformed(event: ActionEvent) {
      println("pressed!")
    }
  }
)

// 암시적 변환을 위한 함수 정의
implicit def function2ActionListener(f: ActionEvent => Unit) =
  new ActionListener {
    def actionPerformed(event: ActionEvent) = f(event)
  }

// step1) addActionListener를 호출하는 방식으로 변경(단순히 외부 함수를 호출하는 방식과 동일)
button.addActionListener(
  function2ActionListener(
    (_: ActionEvent) => println("pressed!")
  )
)

// step2) function2ActionListener 함수를 명시적으로 호출하지 않고도 동작
button.addActionListener(
  (_: ActionEvent) => println("pressed!")
)
