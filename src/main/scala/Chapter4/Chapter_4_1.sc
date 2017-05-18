val bools = Seq(true, false)

// 모든 case에 대해 처리하던가, default를 두던가...그러지 않으면 에러!!
for (bool <- bools) {
  bool match {
    case true => println("got heads")
    case false => println("got tails")
  }
}

// 모든 case를 나열하기 힘들면 if를 사용하라는데...
for (bool <- bools) {
  val which = if (bool) "head" else "tail"
  println("Got " + which)
}