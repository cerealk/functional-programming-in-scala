package fpinscala.datastructures

import minitest.SimpleTestSuite

object ListTest extends SimpleTestSuite {

  import List._

  test("sum") {
    val ints = List(1, 2, 3, 4, 5)
    assertEquals(sum(ints), 15)
  }

  test("product") {
    val dubs = List(1.0, 2.0, 3.0)
    assertEquals(product(dubs), 6.0)
  }

  test("product with 0") {
    val dubs = List(0.0, 1.0, 4738165974869.0)
    assertEquals(product(dubs), 0)
  }

  test("tail") {
    val as = List(1, 2, 3)
    assertEquals(tail(as), List(2, 3))
  }

  test("Tail an empty List") {
    val as = List()
    intercept[Exception] {
      tail(as);
      ()
    }
    ()
  }

  test("setHead") {
    assertEquals(setHead(1, List()), List(1))
  }

  test("Drop") {
    assertEquals(drop(List(1, 2, 3), 2), List(3))
    assertEquals(drop(List(), 1), Nil)
    assertEquals(drop(List(1, 2, 3), 0), List(1, 2, 3))
  }


  test("dropWhile") {
    assertEquals(dropWhile(List(1, 2, 3))((s: Int) => s < 2), List(2, 3))
    assertEquals(dropWhile(List(2, 3, 4))((s: Int) => s < 2), List(2, 3, 4))
    assertEquals(dropWhile(List(1))((s: Int) => s < 2), List())
  }

  test("init") {
    assertEquals(init(List(1, 2, 3, 4)), List(1, 2, 3))
  }

  test("foldRight with Nil and Cons") {
    assertEquals(foldRight(List(1, 2, 3, 4), Nil: List[Int])(Cons(_, _)), List(1, 2, 3, 4))
  }

  test("length of a list") {
    assertEquals(length(List(1, 2, 3, 4)), 4)
  }

  test("sum in term of foldRight") {
    assertEquals(sumFoldRight(List(1, 2, 3, 4, 5)), 15)
  }

  test("product in term of fold right") {
    val dubs = List(1.0, 2.0, 3.0)
    assertEquals(productFoldRight(dubs), 6.0)
  }

  test("sum in term of foldLeft") {
    assertEquals(sumFoldLeft(List(1, 2, 3, 4, 5)), 15)
  }

  test("product in term of foldLeft") {
    val dubs = List(1.0, 2.0, 3.0)
    assertEquals(productFoldLeft(dubs), 6.0)
  }


  test("Reverse list") {
    assertEquals(reverse(List(1, 2, 3, 4)), List(4, 3, 2, 1))
  }

  test("foldLeft with Nil and Cons") {
    assertEquals(foldLeft(List(1, 2, 3, 4), Nil: List[Int])((acc, i) => Cons(i, acc)), List(4, 3, 2, 1))
  }

  test("fold Right In terms of fold left") {
    assertEquals(foldRightFL(List(1, 2, 3, 4), Nil: List[Int])(Cons(_, _)), List(1, 2, 3, 4))
  }

  test("append") {
    assertEquals(append(List(1, 2), List(3, 4)), List(1, 2, 3, 4))
    assertEquals(appendFL(List(1, 2), List(3, 4)), List(1, 2, 3, 4))
  }

  test("concatenate a list of lists") {
    val l1 = List(1, 2, 3)
    val l2 = List(4, 5)
    val l3 = List(6, 7)
    assertEquals(concatenate(List(l1, l2, l3)), List(1, 2, 3, 4, 5, 6, 7))
  }

  test("add 1 to a list of integers") {
    val l = List(1, 2, 3, 4, 5)

    def add1(list: List[Int]): List[Int] = list match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x + 1, add1(xs))
    }

    assertEquals(add1(l), List(2,3,4,5,6))
  }


  test("double to string") {
    val l = List(1.0,2.0,3.0)

    def toString(ds:List[Double]):List[String] = ds match {
      case Nil => Nil
      case Cons(x, xs) => Cons(x.toString, toString(xs))
    }

    assertEquals(toString(l), List("1.0", "2.0", "3.0"))
  }

  test("map") {
    val l = List(1, 2, 3, 4, 5)

    def add1(list: List[Int]): List[Int] = map(list)(_ + 1)

    assertEquals(add1(l), List(2,3,4,5,6))
  }

}
