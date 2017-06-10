class ServiceImportante(name: String) {
  def work(i: Int): Int = {
    println(s"ServiceImportante: Doing important work! $i")
    i+1
  }
}

var service1 = new ServiceImportante("uno")
(1 to 3) foreach (i => println(s"Result: ${service1.work(i)}"))

trait Logging {
  def info    (message: String): Unit
  def warning (message: String): Unit
  def error   (message: String): Unit
}

// 자바의 인터페이스 같은데, 메서드를 선언하고 정의할 수도 있음
trait StdoutLogging extends Logging {
  def info    (message: String) = println(s"INFO:   $message")
  def warning (message: String) = println(s"WARNING:$message")
  def error   (message: String) = println(s"ERROR:  $message")
}

// 트레이트를 혼합하기 위해 with를 사용
val service2 = new ServiceImportante("dos") with StdoutLogging {
  override def work(i: Int): Int = {
    info(s"Starting work: i = $i")
    val result = super.work(i)
    info(s"Ending work: i = $i, result = $result")
    result
  }
}
(1 to 3) foreach (i => println(s"Result: ${service2.work(i)}"))

class LoggedServiceImportante(name: String)
  extends ServiceImportante(name) with StdoutLogging {

}