///////////////////////////////////////
// 2.5.3 Future 맛보기 (장점 3 : 암시적 인자 처리)
///////////////////////////////////////

/*
  Future란?
  수행할 일부를 Future를 감싸면 그 작업을 비동기로 수행한다.
  결과가 완성되면 callback을 호출하는 등 결과를 처리 할 수 있는 방법( 자세한건 17장 스터디에서)
 */

/* 아래 예제에서 중요 사항은
Future.apply와, onSuccess, onFailure가 인자 목록마지막에 암시적 인자를 사용 하고 있다.
별도로 지정 하지 않아도 동시 연산을 처리 하기 위해 ExecutionContext import하여 처리 한다.
 */
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

def sleep(millis: Long) = {
  Thread.sleep(millis)
}

def doWork(index: Int) = {
  sleep((math.random * 1000).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  } // 비동기 실행
  future onSuccess {
    case answer: Int => println(s"Success! returned: $answer")
  } // 결과과 성공적으로 완성되면 이 callback 부분 함수 사용
  future onFailure {
    case th: Throwable => println(s"FAILURE! returned: $th")
  } // 결과과 실패면 이 callback 부분 함수 사용
}

sleep(1000)  // '작업' 이 끝날 때까지 충분히 기다린다.
println("Finito!")

// 실행
// scala src/main/scala/day1/Chapter_2_5_3.sc
