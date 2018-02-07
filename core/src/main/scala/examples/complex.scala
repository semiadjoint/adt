package adt
package examples

object complex {
  case class Real(value: Double)
  case class Imaginary(value: Double)
  case class Radians(value: Double)
  case class Magnitude(value: Double)
  type Complex = ADT[ComplexF]

  abstract class ComplexF[S] {
    def make: Real => Imaginary => S
    def add: Complex => S
    def real: Real
    def imag: Imaginary
  }

  def Cartesian(real: Real, imag: Imaginary): Complex = new ADT[ComplexF] {
    type S = (Real, Imaginary)
    def value: S = (real, imag)
    def make: S => ComplexF[S] = {
      case (r,i) =>
        new ComplexF[S] {
          def make = d1 => d2 => (d1,d2)
          def add = {c =>
            val ll = c.make(c.value).real
            val rr = c.make(c.value).imag
            (ll, rr)
          }
          def real = r
          def imag = i
        }
    }
  }

  type Polar = (Magnitude, Radians)
  def c2p(real: Real, imaginary: Imaginary): Polar = {
    val mag = math.sqrt(math.pow(real.value,2) + math.pow(imaginary.value,2))
    val rad = math.atan2(imaginary.value, real.value)
    (Magnitude(mag), Radians(rad))
  }
  type Cartesian = (Real,Imaginary)
  def p2c(mag: Magnitude, ang: Radians): Cartesian = {
    val real = Real(mag.value * math.cos(ang.value))
    val imag = Imaginary(mag.value * math.sin(ang.value))
    (real,imag)
  }

  def Polar(mag: Magnitude, ang: Radians): Complex = new ADT[ComplexF] {
    type S = (Magnitude, Radians)
    def value: S = (mag, ang)
    def make: S => ComplexF[S] = {
      case (mag,ang) =>
        new ComplexF[S] {
          def make = d1 => d2 => c2p(d1,d2)
          def add = {c =>
            val ll = c.make(c.value).real
            val rr = c.make(c.value).imag
            c2p(ll,rr)
          }
          def real = Function.tupled(p2c _)(value)._1
          def imag = Function.tupled(p2c _)(value)._2
        }
    }
  }
}
