package adt
package tests

import org.scalatest._

class ExampleSpec extends FreeSpec with MustMatchers {
  "adt" - {
    "ex1" in {
      import dogs._
      import dogs.syntax.all._


      true mustBe true
    }
  }
}
