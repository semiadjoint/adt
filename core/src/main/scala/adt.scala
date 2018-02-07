package adt

abstract class ADT[F[_]] {
  type S
  def value: S
  def make: S => F[S]
}

