// universal trait 범용 트레이트

/*
class USPhoneNumber(val s: String) extends AnyVal {

  override def toString = {
    val digs = digits(s)
    val areaCode  = digs.substring(0,3)
    val exchange  = digs.substring(3,6)
    val subnumber = digs.substring(6,10)  // "subscriber number"
    s"($areaCode) $exchange-$subnumber"
  }

  private def digits(str: String): String = str.replaceAll("""\D""", "")
}

val number = new USPhoneNumber("987-654-3210")
// Result: number: USPhoneNumber = (987) 654-3210
*/

// universal trait는
// 1. Any를 상속
// 2. 메서드만 정의
// 3. 자체 초기화 없음

// 위 코드의 digits 함수만 정의
trait Digitizer extends Any {
  def digits(s: String): String = s.replaceAll("""\D""", "")
}

// 위 코드의 formatting을 처리
trait Formatter extends Any {
  def format(areaCode: String, exchange: String, subnumber: String): String =
    s"($areaCode) $exchange-$subnumber"
}

class USPhoneNumber(val s: String) extends AnyVal
  with Digitizer with Formatter {

  override def toString = {
    val digs = digits(s)
    val areaCode = digs.substring(0,3)
    val exchange = digs.substring(3,6)
    val subnumber  = digs.substring(6,10)
    format(areaCode, exchange, subnumber)
  }
}

val number = new USPhoneNumber("987-654-3210")
// Result: number: USPhoneNumber = (987) 654-3210

