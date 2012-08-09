package foo
package test

import org.scalatest.{FunSpec, ShouldMatchers}

class FooTest extends FunSpec with ShouldMatchers {

  describe("Foo.foo should") {
    it("always return 5") {
      Foo.foo should equal (5)
    }
  }

}