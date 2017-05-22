///////////////////////////////////////
// 5.1 암시적 인자
///////////////////////////////////////

// src/main/scala/progscala2/implicits/implicit-args.sc

// 명시적으로 제공할 필요가 없는 인자를 제공하기 위해 사용

// Never use Floats for money:
def calcTax(amount: Float)(implicit rate: Float): Float = amount * rate

object SimpleStateSalesTax {
  implicit val rate: Float = 0.05F
}

{
  // implicit parameter 가 선언된 scope 내에서 단일 식별자로 존재(type 으로 구분)
  import SimpleStateSalesTax.rate // implicit val rate: Float

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}

{ // explicit
  val amount = 100F
  val rate = 0.1F

  // calcTax의 두번째 parameter는 암시적 인자이지만 아래와 같이 명시적으로 전달해도 당연히 잘 동작한다.
  println(s"Tax on $amount = ${calcTax(amount)(rate)}")
}

case class ComplicatedSalesTaxData(
                                    baseRate: Float,
                                    isTaxHoliday: Boolean,
                                    storeId: Int)

object ComplicatedSalesTax {
  private def extraTaxRateForStore(id: Int): Float = {
    // From id, determine location, then extra taxes...
    0.0F
  }

  implicit def rate(implicit cstd: ComplicatedSalesTaxData): Float =
    if (cstd.isTaxHoliday) 0.0F
    else cstd.baseRate + extraTaxRateForStore(cstd.storeId)
}

{
  import ComplicatedSalesTax.rate // implicit def rate(implicit ComplicatedSalesTaxData): Float
  implicit val myStore = ComplicatedSalesTaxData(0.06F, false, 1010)

  val amount = 100F
  println(s"Tax on $amount = ${calcTax(amount)}")
}

///////////////////////////////////////
// 5.1.1 implicitly 사용하기
///////////////////////////////////////

// src/main/scala/progscala2/implicits/implicitly-args.sc
import math.Ordering

case class MyList[A](list: List[A]) {
  def sortBy1[B](f: A => B)(implicit ord: Ordering[B]): List[A] =
    list.sortBy(f)(ord)

  def sortBy2[B : Ordering](f: A => B): List[A] =
    list.sortBy(f)(implicitly[Ordering[B]])
}

val list = MyList(List(1,3,5,2,4))

list sortBy1 (i => -i)
list sortBy2 (i => -i)

////////////////////////////////////////////////////////////

def plus(first: Int)(second: Int) = first + second

implicit val secondNum: Int = 17

plus(2000)(implicitly[Int]) // implicitly[Int] : 두번째 인자로 implicit Int를 받겠다
