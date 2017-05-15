// Java, C# 과는 다르게 scala.Enumeration 클래스를 상속해서 사용하면
// Value는 Enumeration의 내부 클래스. 이름이 똑같은 함수가 있음.

object Breed extends Enumeration {
  type Breed = Value
  val doberman = Value("Doberman Pinscher")
  val yorkie   = Value("Yorkshire Terrier")
  val scottie  = Value("Scottish Terrier")
  val dane     = Value("Great Dane")
  val portie   = Value("Portuguese Water Dog")
}
import Breed._

// print a list of breeds and their IDs
println("ID\tBreed")
for (breed <- Breed.values) println(s"${breed.id}\t$breed")

// print a list of Terrier breeds
println("\nJust Terriers:")
Breed.values filter (_.toString.endsWith("Terrier")) foreach println

// 왜 에러가 나는 것인가?
//def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

//println("\nTerriers Again??")
//Breed.values filter isTerrier foreach println

object Color extends Enumeration {
  val Red = Value
  val Green = Value
  val Blue = Value
}

println(Color.Blue.id)

println(Color(1))
