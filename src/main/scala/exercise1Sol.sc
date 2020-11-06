case class Product(name: String, price: Int)

case class CreditCard(name: String)

case class Charge(card: CreditCard, amount: Int) {
  def combine(other: Charge) = {
    if(card == other.card)
      Charge(card, amount + other.amount)
    else
      throw new Exception("Can't combine")
  }
}

case class Purchase(product: Product)

def charge(card: CreditCard, amount: Int): Unit = println(s"Charged $amount to $card")

def buy(product: Product, card: CreditCard): (Purchase, Charge) =
  (Purchase(product), Charge(card, product.price))

def buyMultiple(product: Product, card: CreditCard, num: Int): (List[Purchase], Charge) = {
  val (purchases, charges) = List.fill(num)(buy(product, card)).unzip
  (purchases, charges.reduce((a,b) => a.combine(b)))
}

val coffee = Product("Coffee", 3)
var card = CreditCard("My Card")

val (purchases, charge) = buyMultiple(coffee, card, 10)

charge(charge.card, charge.amount)
