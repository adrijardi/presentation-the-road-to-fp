## Getting Started with FP
<img src="https://i.imgur.com/8L0BuEx.png" alt="FP" height="450px"/>



## Agenda
- What is Functional programming?
- Functions
- Referential transparency
- Higher order functions
- Recursion
- Lazy evaluation
- Types
- Immutability
- Functional data structures
- Functional error handling
- Abstraction over types
- Algebraic data types



## What is not Functional Programming
- Only used at universities
- Monads
- Category theory
- Contrary to OOP
- Difficult



## What is Functional Programming

From Wikipedia:

"In computer science, functional programming is a programming paradigm where programs are constructed by applying and composing functions. It is a declarative programming paradigm in which function definitions are trees of expressions that each return a value, rather than a sequence of imperative statements which change the state of the program."



## Let's dive deeper
We use

- Functions as first class citizens
- Referential transparency
- Immutability

We get

- Composability
- Reusability
- Less boilerplate



## Functions as first class citizens
```scala
def add(a: Int, b: Int): Int = a + b

val addVal = add _
def higherOrderFunction(op: (Int, Int) => Int): ???
```
This allows us to do much more



## Pure Functions
- Functions that do not perform any side effects
- Outside state is never modified
- Given a parameter the response is always the same (predictable and testable)
- Memoization, parallelization (always thread safe) and composition is trivial
- Great for abstraction

```scala
def hi(name: String): String = s"Hello $name"

def unsafeHi(name: String): String = {
  save2DB()
  launchMissiles()
  s"Hello $name"
}
```



## Referential transparency
- Any expresion can be replaced with its value while maintaining the behaviour of the program
- Property of pure functions
- Allows for local reasoning

Are these programs the same?
```scala [1-2|4]
val a = fn()
(a, a)

(fn(), fn())
```


## Referential transparency
```scala
val a = fn()
(a, a)

(fn(), fn())
```

``` [1-4|6]
def fn() = {
    println("Launch missiles")
    42
}

def fn() = 42
```


## Referential transparency
Other real life examples
```scala
System.currentTimeMillis() == System.currentTimeMillis()
```
Usually not equal to
```scala
val time = System.currentTimeMillis()
time == time
```



## Let's dive deeper - Composability
- Just think about types
- If your functions are referentially transparent they can always compose
- No side effects to worry about
```
x: A => B
y: B => C
x.compose(y): A => C
```



## Higher Order functions
Function that takes a function as a parameter or returns a function

```scala
def add2(num: Int): Int = num + 2

def twice(num: Int, fn: Int => Int): Int = fn(fn(num))
```


## Higher Order functions
You are tasked with finding all large purchases
```scala
def findLargePurchases(purchases: Array[Int]): mutable.ArrayBuffer[Int] = {
    val res = mutable.ArrayBuffer[Int]()

    for(i <- 0 until purchases.size) {
      if(purchases(i) > 50) res.addOne(purchases(i))
    }

    res
  }
```


## Higher Order functions
Now also small purchases are required
```scala
def findSmallPurchases(purchases: Array[Int]): mutable.ArrayBuffer[Int] = {
  val res = mutable.ArrayBuffer[Int]()

  for(i <- 0 until purchases.size) {
    if(purchases(i) < 10) res.addOne(purchases(i))
  }

  res
}
```
This code rings a bell...


## Higher Order functions
It turns out, sometimes we also need medium purchases... that's enough!
```scala
def findPurchases(purchases: Array[Int], filter: Int => Boolean): mutable.ArrayBuffer[Int] = {
  val res = mutable.ArrayBuffer[Int]()

  for(i <- 0 until purchases.size) {
    if(filter(purchases(i))) res.addOne(purchases(i))
  }

  res
}
```


## Higher Order functions
```scala
def findSmallPurchases(purchases: Array[Int]) = findPurchases(purchases, _ < 10)

def findLargePurchases(purchases: Array[Int]) = findPurchases(purchases, _ > 50)

def findMediumPurchases(purchases: Array[Int]) = findPurchases(purchases, num => num >= 10 && num <= 50)
```


## Higher Order functions
<img src="https://i.imgur.com/DMH0BcM.png" alt="Boilerplate" height="450px"/>


## Higher Order functions
Function that returns function
```scala
def producer(consumer: Int => Unit)

def dynamoDBConsumer(client: DdbClient): Int => Unit = { num =>
  client.saveItem(item(num))
}
def sqsConsumer(client: SqsClient): Int => Unit = { num =>
  client.sendMessage(msg(num))
}
```



## Recursion
FP typically uses recursion

```scala
def add(nums: List[Int]) = 
  if(nums.isEmpty) 0
  else nums.head + add(nums.tail)
```


## Recursion - fold
In reality no one uses recursion
- Use higher order functions instead (fold, map, flatmap)
```scala        
def add(nums: List[Int]) = nums.fold(0)(_ + _)

def addToAll(toAdd: Int, nums: List[Int]) = nums.map(_ + toAdd)
```


## Recursion - fold vs loops
- Principle of least power (similar to foreach)
- Immutable vs mutable
- Tail recursion



## Lazy evaluation
- Optimises computations
- Useful to process infinite collections
- Allows modularity (write a infinite fib generator, later decide how many to take)

```scala
def fibonacci: Iterator[Int] = {
    Iterator.unfold(0, 1) {
        case (a, b) => Some((a, (b, a + b)))
    }
}

fibonacci.drop(5).take(10).toList
// List(5, 8, 13, 21, 34, 55, 89, 144, 233, 377)
```


## Lazy evaluation
- Also good for IO or CPU intensive calculations
```scala
val req1 = IO(Http(url1))
val req2 = IO(Http(url2))
val result = req1.orElse(req2)
```



## Why use types
- Compiler checks and autocomplete
- Make illegal states unrepresentable
- Only has to write tests for the rest



## Immutability
- Cannot be modified after created
- Internal state can change as long as consumers don't see this (example Stream)
- Thread safe
- Easier to reason about
- GC?



## Functional data structures (forget Array)
- Immutable (persistency, thread safety)
  Arrays -> Linked list of tree backed lists
  Immutable sets and trees usually implemented as red/black trees
- Parallelization is trivial
- Performance?
- GC?



## Can this help me write real world AWS Lambdas?
- Absolutely yes!
1. Learn to think about your software functionally
1. Use onion architecture to write your applications
1. Use unit tests and property based testing to validate your application



## Questions?
<img src="https://i.imgur.com/VEVuc0O.png" alt="Use FP please" height="450px"/>



## References
Pictures by https://impurepics.com



## TODO
Dive deeper
add immutability, etc

difference betweend side effects and effects

leave out lazy evaluation?

move types somewhere else






## Why should I NOT learn FP
- Write simple, testable and correct software
- Actually be able to write concurrent code
- Make fewer bugs
- Be more productive
- Work yourself out of your job
