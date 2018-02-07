## tutorial

The generic abstract data type is defined fairly simply:
```scala
scala> abstract class ADT[F[_]] {
     |   type S
     |   def value: S
     |   def make: S => F[S]
     | }
defined class ADT
```

`S` is an existential type representing the hidden state of the ADT. We'll see what the other pieces mean in a minute.

Let's see how the above gets used with a concrete example.

Suppose we want to define an ADT for complex numbers. Implementations of our ADT might use cartesian coordinates internally - or they might use polar coords. The important thing is that they expose the following interface:

```scala
scala> // Some boring type definitions:
     | case class Real(value: Double)
defined class Real

scala> case class Imaginary(value: Double)
defined class Imaginary

scala> case class Radians(value: Double)
defined class Radians

scala> case class Magnitude(value: Double)
defined class Magnitude

scala> object X {
     |   // The important part:
     |   abstract class ComplexF[S] {
     |     def make: Real => Imaginary => S
     |     def add: Complex => S
     |     def real: Real
     |     def imag: Imaginary
     |   }
     | 
     |   // We'll look at this part in a bit:
     |   type Complex = ADT[ComplexF] 
     | }
defined object X

scala> import X._
import X._
```

where `S` is the type of some (to-be) internal state.

(The `object X {...}; import X._` is just a hack that allows us to define the mutually referential types in the repl.)

Given the `ComplexF` signature, we're able to define a proper `Complex` ADT:
```scala
type Complex = ADT[ComplexF]
```
Let's look at `ADT[F[_]]` again, with the `F` type variable instantiated to `ComplexF`:
```scala
// pseudo code
type Complex = {
  type S
  def value: S
  def make: S => ComplexF[S]
}
```

What the above says: "Complex numbers contain a hidden state type,
along with a pair containing a state value and a way to construct an
API for complex numbers."

To be continued...
