///////////////////////////////////////
// 3.4 도메인 특화 언어 (DSL)
///////////////////////////////////////

// 도메인 특화 언어는 특정 문제 영역을 위해 만들어진 언어로,
// 해당 영역의 개념을 간결하고 직관적으로 표현할 수 있게 돕는 것이 목적이다.

// scalatest library를 이용한 행동 주도 개발(Behavior-Driven Development) 방식의 테스트 예제
import org.scalatest.{FunSpec, ShouldMatchers}

class NerdFinderSpec extends FunSpec with ShouldMatchers {
    describe ("nerd finder") {
        it("identify nerds from a List") {
            val actors = List("Rick Moranis", "James Dean", "Woody Allen")
            val finder = new NerdFinder(actors)
            finder.findNerds shouldEqual List("Rick Moranis", "Woody Allen")
        }
    }
}

(new NerdFinderSpec).execute()