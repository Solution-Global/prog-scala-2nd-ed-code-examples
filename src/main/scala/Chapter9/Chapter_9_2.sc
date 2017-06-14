abstract class Widget

class Button(val label: String) extends Widget {

  def click(): Unit = updateUI()

  def updateUI(): Unit = { /* logic to change GUI appearance */ }
}

class ObservableButton(name: String)
  extends Button(name) with Subject[Button] {

  // Button의 click method를 override
  override def click(): Unit = {
    // updateUI() 실행
    super.click()
    // Subject trait에 정의 된 method, observer 에게 알림(ButtonCountObserver 의 count 1증가)
    notifyObservers(this)
  }
}

trait Observer[-State] {
  def receiveUpdate(state: State): Unit
}

trait Subject[State] {
  private var observers: List[Observer[State]] = Nil

  def addObserver(observer:Observer[State]): Unit =
    observers ::= observer

  def notifyObservers(state: State): Unit =
    observers foreach (_.receiveUpdate(state))
}

////////////////////

class ButtonCountObserver extends Observer[Button] {
  var count = 0
  // receiveUpdate는 Observer trait에서 추상메소드로 선언되었기 때문에 Observer를 상속받은 클래스는 이를 구현해야 함
  def receiveUpdate(state: Button): Unit = count += 1
}

abstract trait ButtonCountObserverTrait extends Observer[Button] { // trait 에서는 추상 멤버가 있더라도 abstract 키워드를 붙일 필요가 없음

}

val button = new ObservableButton("Click Me!")
val bco1   = new ButtonCountObserver
val bco2   = new ButtonCountObserver

// 위의 button 처럼 Button 과 Subject 를 상속받는 class 의 instance 를 선언한 것과는 다르게
// 아래와 같이 원하는 trait 들을 혼합한 클래스(위에서는 ObservableButton)를 생성하지 않고도 trait 를 mixin 할 수 있다

//val button = new Button("Click Me!") with Subject[Button] {
//
//  override def click(): Unit = {
//    super.click()
//    notifyObservers(this)
//  }
//}

button addObserver bco1 //
button addObserver bco2

button.updateUI()

(1 to 5) foreach (_ => button.click())

assert(bco1.count == 5)
assert(bco2.count == 5)

// abstract class 와 trait 의 차이점
// 1. abstract class 는 하나만 상속 가능하지만 trait 는 중복 가능하다.
// 2. abstract class 는 생성자 매개변수를 받을 수 있지만 trait 는 그렇지 않다.
//    ex. abstract class Color(i: String)     (O)
//        trait Color(i: String)              (X)