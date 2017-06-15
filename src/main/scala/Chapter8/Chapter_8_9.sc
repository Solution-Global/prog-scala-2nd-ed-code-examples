// 8.9 내포된 타입
// 타입 선언과 정의를 내포시킬수 있다.
// 특정 타입에만 있는 예외나 다른 유용한 타입을 객체 안에 정의할 수 있다.

object Database {
    case class ResultSet(/*...*/)
    case class Connection(/*...*/)

    case class DatabaseException(message: String, cause: Throwable) extends RuntimeException(message, cause)

    sealed trait Status
    case object Disconnected extends Status // 인스턴스가 실제로는 아무런 상태 정보를 저장하지 않는다면 case object를 사용하라.
    case class Connected(connection: Connection) extends Status
    case class QuerySuccess(results: ResultSet) extends Status
    case class QueryFailed(e: DatabaseException) extends Status
}

class Database {
    import Database._

    def connect(server: String): Status = ???

    def disconnect(): Status = ???

    def query(/*...*/): Status = ???
}

// ???
// Predef에 정의된 메서드로 예외를 리턴하며 구현하지 않은 메서드를 표시하기 위해 사용한다.
// 코드는 컴파일할 수 있지만, ???로 지정한 메서드를 호출할 수는 없다.

// case object 의 구멍?
// case object hashCode 는 객체의 이름을 해시에 사용한다. 객체의 패키지나 객체 안의 필드는 무시한다.
// hashCode가 강력해야 하는 경우에 case object를 사용하는 것은 위험하다.
case object Foo

Foo.hashCode

"Foo".hashCode