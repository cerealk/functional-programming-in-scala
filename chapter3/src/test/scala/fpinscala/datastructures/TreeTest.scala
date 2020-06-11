package fpinscala.datastructures

import minitest.SimpleTestSuite
import fpinscala.datastructures.Tree._

object TreeTest extends SimpleTestSuite {

  test("count tree size") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    assertEquals(size(tree), 5)
  }

  test("maximum element") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    def maximum(t:Tree[Int]): Int = t match {
      case Leaf(v) => v
      case Branch(l,r) => maximum(l) max maximum(r)
    }

    assertEquals(maximum(tree), 3)
  }

  test ("depth") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    assertEquals(depth(tree), 3)
  }

  test("map"){
    val t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    val expected = Branch(Branch(Leaf("1"), Leaf("2")), Leaf("3"))

    assertEquals(map(t)(String.valueOf(_)), expected)

  }
}
