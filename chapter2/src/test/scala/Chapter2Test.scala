import minitest.SimpleTestSuite

object Chapter2Test extends SimpleTestSuite {

  test("fibonacci ") {

    def fib(n: Int): Int = {
      @annotation.tailrec
      def loop(n: Int, prev: Int, cur: Int): Int =
        if (n == 0) prev
        else loop(n - 1, cur, prev + cur)

      loop(n, 0, 1)
    }

    assertEquals(fib(0), 0)
    assertEquals(fib(1), 1)
    assertEquals(fib(2), 1)
    assertEquals(fib(3), 2)
  }

  test("List sorting") {
    def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean = {
      @annotation.tailrec
      def loop(position: Int): Boolean = {
        if (position == as.size - 1) true
        else if (!ordered(as(position), as(position + 1))) false
        else loop(position + 1)
      }

      loop(0)
    }

    assertEquals(isSorted(Array(1, 2, 3, 4, 5, 6), (x: Int, y: Int) => x < y), true)
    assertEquals(isSorted(Array(1, 2, 8, 4, 5, 6), (x: Int, y: Int) => x < y), false)
  }

  def curry[A, B, C](f: (A, B) => C): A => (B => C) = a => b => f(a, b)

  def uncurry[A, B, C](f: A => B => C): (A, B) => C = (a, b) => f(a)(b)

  def compose[A, B, C](f: A => B, g: B => C): A => C = {
    a => g(f(a))
  }

}
