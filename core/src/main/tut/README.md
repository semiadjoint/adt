## tutorial

The generic abstract data type is defined fairly simply:
```tut
abstract class ADT[F[_]] {
  type S
  def value: S
  def make: S => F[S]
}
```

`S` is an existential type representing the hidden state of the ADT. We'll see what the other pieces mean in a minute.

Let's see how the above gets used with a concrete example.

Suppose we want to define an ADT for complex numbers. Implementations of our ADT might use cartesian coordinates internally - or they might use polar coords. The important thing is that they expose the following interface:

```scala
// Some boring type definitions:
case class Real(value: Double)
case class Imaginary(value: Double)
case class Radians(value: Double)
case class Magnitude(value: Double)
type Complex = ADT[ComplexF] // We'll look at this part in a bit.

// The important part:
abstract class ComplexF[S] {
  def make: Real => Imaginary => S
  def add: Complex => S
  def real: Real
  def imag: Imaginary
}

```

where `S` is the type of some (to-be) internal state.

Given the `ComplexF` signature, we're able to define a proper `Complex` ADT:
```
type Complex = ADT[ComplexF]
```
Let's look at `ADT` again with the type variable instantiated:
```
// pseudo-scala
type Complex = {
  type S
  def value: S
  def make: S => Complex[S]
}
```

What the above says: "Complex numbers contain a hidden state type, along with a pair containing a state value and a way to construct an API for complex numbers."

To be continued...
