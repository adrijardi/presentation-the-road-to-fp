## Type Driven Development
Or How to let the compiler help you



## Agenda
- Goal
- Total functions



## Goal
- Correctness 
- Make illegal states unrepresentable
- Reduce bugs
- Documentation



## Total functions



## Common issues
- Parameter mistakes
  - Functions with many equally typed parameters are easy to use wrong
  - Unhandled exceptions
    - Exceptions are analogous to `goto` operations and dangerous



## Common issues
- Validation instead of parsing
  - Programs mix validation with logic
  - Risk of partially executed side effects (Data integrity)
  - Logic is much more complicated 



## The problem
```scala
def divide(a: String, b: String): Int = {
  a.toInt / b.toInt
}
```



## Solutions - Represent possible errors
```scala
def divide(a: String, b: String): Option[Int] = {
  a.toIntOption.zip(b.toIntOption).map{ 
    case (pa, 0) => None
    case (pa, pb) => Some(pa/pb)
  }
}
```



## Solutions - Make illegal state irrepresentable

```scala
def divide(a: Int, b: IntNotZero): Int = a/b.value

case class IntNotZero private(value: Int)
object IntNotZero {
  def apply(value: Int): Option[IntNotZero] = {
    if(value == 0) None else Some(value)
  }
}
```

- Refinement libraries: https://github.com/fthomas/refined