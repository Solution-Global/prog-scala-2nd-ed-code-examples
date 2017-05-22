// 5.2 암시적 인자를 사용하는 시나리오

// * 암시적 인자를 사용하는 이유
// 준비를 위한 코드를 없애는 것이다. 예를들어 맥락 정보를 명시적으로 제공하는 대신 암시적으로 제공 할 수 있다.
// 매개변수화한 타입을 받는 메서드에 사용해서 버그를 줄이거나 허용되는 타입을 제한하기 위한 제약사항으로 사용하는 것이다.

// 5.2.1 ExecutionContent 제공 -> Future 사용 예제에서 두 번째 인자로 ExcutionContext를 전달했으나 import 문으로 대체 가능
// Transaction, database 연결, thread pool, user session 등에서 암시적 인자 사용 가능
// apply[T](body: => T)(implicit executor: ExecutionContext): Future[T]
import scala.concurrent.ExecutionContext.Implicits.global

// 5.2.2 사용 가능한 기능 제어
// 암시적 사용자 세션 인자를 사용해서 특정 API를 호출하거나 접근 가능한 메뉴를 제한
// 암시적으로 처리되기 때문에 user 정보를 매번 parameter로 주고 받을 필요 없이 접근 가능
//class UserInfo
//{
//  implicit var permssion: String = "No permission"
//  def getPermission: String = {permssion}
//  def setPermssion(perm: String) : Unit = {permssion = perm}
//}

class UserInfo(val preference: String)

def getMenu(implicit userInfo: UserInfo) = userInfo.preference match {
  case "Admin" => println("AdminMenu")
  case _ => println("UserMenu")
}

object userInfo {
  implicit val userInfo = new UserInfo("UserInfo")
}

import userInfo._
getMenu
val adminInfo = new UserInfo("Admin")
getMenu(adminInfo)
