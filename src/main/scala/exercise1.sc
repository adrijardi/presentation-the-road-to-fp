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

def charge(card: CreditCard, amount: Int): Unit =
  println(s"Charged $amount to $card")

// --------------------------------------------------------------------

def buy(product: Product, card: CreditCard): Purchase = {
  charge(card, product.price)
  Purchase(product)
}

val coffee = Product("Coffee", 3)
var card = CreditCard("My Card")

buy(coffee, card)
