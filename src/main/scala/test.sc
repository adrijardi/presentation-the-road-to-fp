import scala.collection.mutable
import scala.io.Source
import scala.collection.parallel.CollectionConverters._

val filename = "/home/adrian/IdeaProjects/start-functional-programming/text.txt"

{
  val source = Source.fromFile(filename)
  println(source.getLines()
    .flatMap(_.split(' '))
    .count(_ == "nisl"))
  source.close()
}

{
  var numHello = 0
  val source = Source.fromFile(filename)
  val it = source.getLines()
  while(it.hasNext) {
    val line = it.next()
    val words = line.split(' ')
    words.foreach { word =>
      if (word == "nisl")
        numHello += 1
    }
  }
  println(numHello)
  source.close()
}

{
  (1 to 100).map {
    case n if n % 3 == 0 && n % 5 == 0 => "FizzBuzz"
    case n if n % 3 == 0 => "Fizz"
    case n if n % 5 == 0 => "Buzz"
    case n => n
  }.foreach(println)
}


{
  def findPurchases(purchases: Array[Int], filter: Int => Boolean): mutable.ArrayBuffer[Int] = {
    val res = mutable.ArrayBuffer[Int]()

    for(i <- 0 until purchases.size) {
      if(filter(purchases(i))) res.addOne(purchases(i))
    }

    res
  }

  def findSmallPurchases(purchases: Array[Int]) = findPurchases(purchases, _ < 10)

  def findLargePurchases(purchases: Array[Int]) = findPurchases(purchases, _ > 50)

  def findMediumPurchases(purchases: Array[Int]) = findPurchases(purchases, num => num >= 10 && num <= 50)

  println(findSmallPurchases(Array(1,2,3,4,5,60, 70)))
  println(findLargePurchases(Array(1,2,3,4,5,60, 70)))
  println(findMediumPurchases(Array(1,2,3,4,5,20, 30,60, 70)))
}

{
  def fibonacci: Iterator[Int] = {
    Iterator.unfold(0, 1) {
      case (a, b) => Some((a, (b, a + b)))
    }
  }

  println(fibonacci.drop(5).take(10).toList)
}


{
  val cities = List(
    "Paris" -> "Lion",
    "Lion" -> "Marselle",
    "Marselle" -> "Tous"
  ).toMap

  val destCities = cities.values.toSet
  val startCity = cities.keySet.diff(destCities).head

  startCity :: List.unfold(startCity)(current => {
    val next = cities.get(current)
    next.map(c => (c,c))
  })

}

//{
//  sealed trait List[+A]
//
//  case object Nil extends List[Nothing]
//  case class Cons[+A](head: A, tail: List[A]) extends List[A]
//
//  Cons(1, Cons(2, Cons(3, Nil)))
//}
{
  def add(nums: Vector[Int]) = nums.par.fold(0)(_ + _)
}