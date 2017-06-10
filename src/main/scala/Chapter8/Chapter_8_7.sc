///////////////////////////////////////
// 8.7 입력 검증하기
///////////////////////////////////////

// src/main/scala/progscala2/basicoop/Zipcode.scala

case class ZipCode(zip: Int, extension: Option[Int] = None) {
  // require: 입력된 인자가 valid한지 체크할 수 있게 하는 Predef 메소드
  // case class의 인스턴스가 생성될 때마다 호출 된다.
  require(valid(zip, extension),                                     // <1>
    s"Invalid Zip+4 specified: $toString")

  // zipcode가 5자리인지 extension이 있다면 4자리인지 체크
  protected def valid(z: Int, e: Option[Int]): Boolean = {
    if (0 < z && z <= 99999) e match {
      case None    => validUSPS(z, 0)
      case Some(e) => 0 < e && e <= 9999 && validUSPS(z, e)
    }
    else false
  }

  /** Is it a real US Postal Service zip code? */
  protected def validUSPS(i: Int, e: Int): Boolean = true            // <2>

  override def toString =                                            // <3>
    if (extension != None) s"$zip-${extension.get}" else zip.toString
}

object ZipCode {
  def apply (zip: Int, extension: Int): ZipCode =
    new ZipCode(zip, Some(extension))
}

ZipCode(12345)
// Result: ZipCode = 12345

ZipCode(12345, Some(6789))
// Result: ZipCode = 12345-6789

ZipCode(12345, 6789)
// Result: ZipCode = 12345-6789

try {
  ZipCode(0, 6789)  // Invalid Zip+4 specified: 0-6789
} catch {
  case e: java.lang.IllegalArgumentException => e
}

try {
  ZipCode(12345, 0)  // Invalid Zip+4 specified: 12345-0
} catch {
  case e: java.lang.IllegalArgumentException => e
}

