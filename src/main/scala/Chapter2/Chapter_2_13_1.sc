///////////////////////////////////////
// 2.13 추상 타입(abstract Type)
///////////////////////////////////////

/*
제너릭같은 매개변수화한 타입의 한계를 극복하기 위해 사용
 */

import java.io._

abstract class BulkReader {
  type In // 추상 타입
  val source: In
  def read: String
}

// val source: String는 BulkReader의 abstract memeber를 선언 한 것임
class StringBulkReader(val source: String) extends BulkReader {
  type In = String
  def read: String = source
}

class FileBulkReader(val source: File) extends BulkReader {
  type In = File
  def read: String = {
    val in = new BufferedInputStream(new FileInputStream(source)) //
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
