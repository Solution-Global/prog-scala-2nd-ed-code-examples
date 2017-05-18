// match-guard
for (i <- Seq(1, 2, 3, 4)) {
  i match {
    case _ if i%2 == 0 => println(s"even: $i")
    case _              => println(s"odd: $i")
  }
}

// match-deep
case class Address(street: String, city: String, country: String)
case class Person(name: String, age: Int, address: Address)

val alice   = Person("Alice",   25, Address("1 Scala Lane", "Chicago", "USA"))
val bob     = Person("Bob",     29, Address("2 Java Ave.",  "Miami",   "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct.", "Boston",   "USA"))

for (person <- Seq(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25, Address(_, "Chicago", _)) => println("Hi Alice!")
    case Person("Bob",   29, Address("2 Java Ave.", "Miami", "USA")) => println("Hi Bob!")
    case Person(name, age, _) => println(s"Who are you, $age year-old personal named $name?")
  }
}

// match-deep-tuple example
val itemCosts = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemCostsIndices = itemCosts.zipWithIndex
for (itemCostIndex  <- itemCostsIndices) {
  itemCostIndex match {
    case ((item, cost), index) => println(s"$index: $item costs $cost each")
  }
}