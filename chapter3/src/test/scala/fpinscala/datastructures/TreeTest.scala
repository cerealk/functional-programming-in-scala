package fpinscala.datastructures

import fpinscala.datastructures.Tree._
import minitest.SimpleTestSuite

object TreeTest extends SimpleTestSuite {

  test("count tree size") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    assertEquals(size(tree), 5)
  }

  test("maximum element") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    def maximum(t: Tree[Int]): Int = t match {
      case Leaf(v) => v
      case Branch(l, r) => maximum(l) max maximum(r)
    }

    assertEquals(maximum(tree), 3)
  }

  test("depth") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    assertEquals(depth(tree), 3)
  }

  test("map") {
    val t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    val expected = Branch(Branch(Leaf("1"), Leaf("2")), Leaf("3"))

    assertEquals(map(t)(String.valueOf), expected)

  }

  test("fold") {
    val t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    assertEquals(fold(t)(a => a)(_ + _), 6)
  }

  test("count tree size via Fold") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    def sizeF[A](t: Tree[A]) = fold(t)(_ => 1)(1 + _ + _)

    assertEquals(sizeF(tree), 5)
  }

  test("maximum element via fold") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    def maximum(t: Tree[Int]): Int = fold(t)(a => a)(_ max _)

    assertEquals(maximum(tree), 3)
  }

  test("depth via fold") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))

    def depthF[A](t: Tree[A]) = fold(t)(_ => 1)((l, r) => 1 + (l max r))

    assertEquals(depthF(tree), 3)
  }

  test("map via fold") {
    val t = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    val expected = Branch(Branch(Leaf("1"), Leaf("2")), Leaf("3"))

    def mapF[A, B](t: Tree[A])(f: A => B): Tree[B] =
      fold(t)(a=> Leaf(f(a)):Tree[B])(Branch(_, _))

    assertEquals(mapF(t)(String.valueOf), expected)

  }

}
