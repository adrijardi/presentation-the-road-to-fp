## The road to Functional Programming
<img src="https://i.imgur.com/8L0BuEx.png" alt="FP" height="450px"/>



## Why should I learn FP
- Fun
- Power
- Compiler works for you
- Bragging privileges



## Agenda
- What is Functional programming?
- Pure Functions
- Referential transparency
- Higher order functions
- Recursion
- Lazy evaluation
- Immutability
- Functional data structures



## What is FP?
- An style, not a language
- We use
    - Functions as first class citizens
    - Referential transparency
    - Immutability
- We get
    - Composability
    - Reusability
    - Testability
    - Less boilerplate



## Functions as first class citizens

```scala [1 | 3 | 4]
def add(a: Int, b: Int): Int = a + b

val addVal = add _
def higherOrderFunction(op: (Int, Int) => Int): ???
```
This allows us to do much more



## Pure Functions
- Do not perform any side effects
- Outside state is never modified
- Given a parameter the response is always the same (predictable and testable)
- Composition is trivial, great for abstraction
- Memoization, parallelization (always thread safe)

```scala [ 1 | 3-7 ]
def hi(name: String): String = s"Hello $name"

def unsafeHi(name: String): String = {
  save2DB()
  launchMissiles()
  s"Hello $name"
}
```



## Referential transparency
- Property of pure functions
- Any expresion can be replaced with its value while maintaining the behaviour of the program
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


## Referential transparency
- Local reasoning
- Composable & Reusable code
- Testable
- Thread safe
- Helps refactoring



## Higher Order functions
Function that takes a function as a parameter or returns a function

```scala [1 | 3]
def add2(num: Int): Int = num + 2

def twice(num: Int, fn: Int => Int): Int = fn(fn(num))
```


## Higher Order functions
You are tasked with finding all large purchases

```scala
def findLargePurchases(purchases: Array[Int]): ArrayBuffer[Int] = {
    val res = ArrayBuffer[Int]()

    for(i <- 0 until purchases.size) {
      if(purchases(i) > 50) res.addOne(purchases(i))
    }

    res
  }
```


## Higher Order functions
Now also small purchases are required

```scala
def findSmallPurchases(purchases: Array[Int]): ArrayBuffer[Int] = {
  val res = ArrayBuffer[Int]()

  for(i <- 0 until purchases.size) {
    if(purchases(i) < 10) res.addOne(purchases(i))
  }

  res
}
```

This code rings a bell... what's next? medium purchases?


## Higher Order functions
We can do better

```scala
def findPurchases(purchases: Array[Int], filter: Int => Boolean): ArrayBuffer[Int] = {
  val res = ArrayBuffer[Int]()

  for(i <- 0 until purchases.size) {
    if(filter(purchases(i))) res.addOne(purchases(i))
  }

  res
}
```


## Higher Order functions

```scala
def findSmallPurchases(p: Array[Int]) = findPurchases(p, _ < 10)

def findLargePurchases(p: Array[Int]) = findPurchases(p, _ > 50)

def findMediumPurchases(p: Array[Int]) = findPurchases(p, n => n >= 10 && n <= 50)
```


## Higher Order functions
<img src="https://i.imgur.com/DMH0BcM.png" alt="Boilerplate" height="450px"/>



## Recursion
FP typically uses recursion

```scala
def add(nums: List[Int]) = 
  if(nums.isEmpty) 0
  else nums.head + add(nums.tail)
```


## Recursion - fold
In reality no one uses recursion
- Use higher order functions instead (fold, map, flatmap, filter)

```scala        
def add(nums: List[Int]) = nums.fold(0)(_ + _)
//
def addToAll(toAdd: Int, nums: List[Int]) = nums.map(_ + toAdd)
```


## Recursion - folds vs loops
- Principle of least power (similar to foreach)
- Immutable vs mutable
    - Parallelization is trivial
    
```scala
def add(nums: Vector[Int]) = nums.par.fold(0)(_ + _)
```



## Lazy evaluation
- Optimises computations
- Useful to process infinite collections
- Allows for modularity and reusability

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



## Immutability
- Cannot be modified after created
- Internal state can change as long as encapsulated
- Thread safe
- Easier to reason about
- GC?



## Functional data structures
- Immutable: persistency, thread safety
- Parallelization is trivial
- Performance & GC?



## "Real world" examples
- Live coding, wish me luck



## What's next?
- Workshop?
- Functional error handling
- Abstraction over types
- Algebraic data types



## Further reading
**The red book** 

(Functional programming in scala)

<img src="https://images-na.ssl-images-amazon.com/images/I/314lFaf1nsL._SX397_BO1,204,203,200_.jpg" alt="Functional programming in scala" height="300px"/>



Pictures by https://impurepics.com



## Questions?
<img src="https://i.imgur.com/VEVuc0O.png" alt="Use FP please" height="450px"/>
