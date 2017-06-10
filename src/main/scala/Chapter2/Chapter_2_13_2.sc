///////////////////////////////////////
// 2.13 매개변수화한 타입
///////////////////////////////////////

/*
java의 제너릭같은 예제이다.
scala에서는 []을 사용 한다.
 */

import java.io._

// 매개변수화한 타입으로 받는다.
abstract class BulkReader[In] {
  val source: In
  def read: String
}

class StringBulkReader(val source: String) extends BulkReader[String] {
  def read: String = source // In이라는 타을 String으로 정의하면 source 필드도  String으로 정의 해야 한다.
}

class FileBulkReader(val source: File) extends BulkReader[File] {
  def read: String = {
    val in = new BufferedInputStream(new FileInputStream(source))
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

println(new StringBulkReader("Hello Scala!").read)
// 현재 디렉터리가 src/main/scala라고 가정한다.
println(new FileBulkReader(
  new File("./day1/Chapter_2_1.sc")).read)
