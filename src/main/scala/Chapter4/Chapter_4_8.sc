import scala.util.matching.Regex
// src/main/scala/progscala2/patternmatching/match-regex.sc

val BookExtractorRE: Regex = """Book: title=([^,]+),\s+author=(.+)""".r     // <1>
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

// capture group : () 안의 regex 패턴과 일치하는 문자열을 extract 할 수 있음

val ExtractChars = "([a-z]+)".r
val ExtractNums = "([0-9]+)".r

val catalog = Seq(
  "Book: title=Programming Scala Second Edition, author=Dean Wampler",
  "Magazine: title=The New Yorker, issue=January 2014",
  "Unknown: text=Who put this here??",
  "abcdefghijklmnop",
  "0192837465"
)

for (item <- catalog) {
  item match {
    case BookExtractorRE(title, author) =>                           // <2>
      println(s"""Book "$title", written by $author""")
    case MagazineExtractorRE(title, issue) =>
      println(s"""Magazine "$title", issue $issue""")
    case ExtractChars(str) =>
      println("chars : " + str)
    case ExtractNums(nums) =>
      println("nums : " + nums)
    case entry => println(s"Unrecognized entry: $entry")
  }
}
