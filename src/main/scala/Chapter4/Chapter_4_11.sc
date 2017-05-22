// 4.11 봉인된 클래스 계층과 매치의 완전성

sealed abstract class HttpMethod() {
def body: String
def bodyLength = body.length
}

// val
case class Connect(body: String) extends HttpMethod
case class Delete (body: String) extends HttpMethod
case class Get    (body: String) extends HttpMethod
case class Head   (body: String) extends HttpMethod
case class Options(body: String) extends HttpMethod
case class Post   (body: String) extends HttpMethod
case class Put    (body: String) extends HttpMethod
case class Trace  (body: String) extends HttpMethod

def handle (method: HttpMethod) = method match {
  case Connect (body) => s"connect: (length: ${method.bodyLength}) $body"
  case Delete  (body) => s"delete:  (length: ${method.bodyLength}) $body"
  case Get     (body) => s"get:     (length: ${method.bodyLength}) $body"
  case Head    (body) => s"head:    (length: ${method.bodyLength}) $body"
  case Options (body) => s"options: (length: ${method.bodyLength}) $body"
  case Post    (body) => s"post:    (length: ${method.bodyLength}) $body"
  case Put     (body) => s"put:     (length: ${method.bodyLength}) $body"
  case Trace   (body) => s"trace:   (length: ${method.bodyLength}) $body"
}

val methods = Seq(
  Connect("connect body..."),
  Delete ("delete body..."),
  Get    ("get body..."),
  Head   ("head body..."),
  Options("options body..."),
  Post   ("post body..."),
  Put    ("put body..."),
  Trace  ("trace body..."))

methods foreach (method => println(handle(method)))

// 봉인된 기반 클래스에 대해 패턴 매칭을 시도할 때는 case 절이 '같은 파일 안에 정의된 모든 파생 타입'을
// 다루면 매치가 완전해짐. 사용자가 다른 파생 타입을 만들 수 없기 때문

// 부모 타입에 있는 '인자가 없는 추상 메서드' 선언은 서브타입에서 val로 구현할 수 있음
// 추상 부모 타입에서는 val 대신 '인자가 없는 메서드'를 정의해서 서브타입을 구현하는 사람이
// 이를 메서드로 구현할지 val로 구현할지 선택하도록 자유를 부여하는 것을 권장

// 패턴 매칭이 필요한 곳에 열거형을 사용하지 말것, 매치가 완전한지 컴파일러가 알려줄 수 없음

// 4.11 에서 우리가 배울수 있는 내용은???
// 1. sealed 클래스에 대한 패턴 매칭을 시도할 때는 같은 파일 안에 정의된 모든 파생 타입을 다뤄야 완전한 패턴 매칭이 된다.
// 2. 부모 타입에 인자가 없는 추상 메서드 선언은 서브 타입에서 이를 메서드로 구현할지 val로 구현할지 선택권을 부여하는 것이다.
