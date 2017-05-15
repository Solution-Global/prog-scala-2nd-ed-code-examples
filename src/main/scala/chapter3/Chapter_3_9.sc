///////////////////////////////////////
// 3.9 try, catch, finally 사용하기
///////////////////////////////////////

// 스칼라는 예외처리의 필요성을 줄이는 코딩을 장려하지만 여전히 예외처리를 사용
// 예외처리를 빈번하게 사용하는 자바 코드와 상호작용하는 경우에는 더욱

object TryCatch {
  def main(args: Array[String]) = {
    args.foreach(arg => countLines(arg))
  }

  import scala.io.Source
  import scala.util.control.NonFatal

  def countLines(fileName: String) = {
    println()
    var source: Option[Source] = None
    try {
      source = Some(Source.fromFile(fileName))
      val size = source.get.getLines().size
      println(s"file $fileName has $size lines")
    } catch {
      case NonFatal(ex) => println(s"Non fatal exception! $ex")
    } finally {
      for (s <- source) {
        println(s"Closing $fileName...")
        s.close
      }
    }
  }
}

try {
  10 / 10
} catch {
  case e: ArithmeticException => println(e)
}

try {
  10 / 0
} catch {
  case e: ArithmeticException => println(e)
}
