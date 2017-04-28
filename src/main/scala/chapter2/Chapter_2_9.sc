///////////////////////////////////////
// 2.9 리터럴 값
///////////////////////////////////////

/*
  null의 사용을 피하기 위해 Option을 제공
  Option은 Some, None 2가지만 있다.
  None 값을 get으로 가져오면 NoSucheElementException 발생
  null 검사는 잊기 싶지만 명확히 Option으로 전달 하면 값을 사용 하기 위해 Option 검사를 한다.
 */

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska"  -> "Juneau",
  // ...
  "Wyoming" -> "Cheyenne")

println( "Get the capitals wrapped in Options:" )
println( "Alabama: " + stateCapitals.get("Alabama") )
println( "Wyoming: " + stateCapitals.get("Wyoming") )
println( "Unknown: " + stateCapitals.get("Unknown") )

println( "Get the capitals themselves out of the Options:" )
println( "Alabama: " + stateCapitals.get("Alabama").get )
println( "Wyoming: " + stateCapitals.get("Wyoming").getOrElse("Oops!") )
println( "Unknown: " + stateCapitals.get("Unknown").getOrElse("Oops2!") ) // getOrElse를 사용 하여 방어직 코드 가능


