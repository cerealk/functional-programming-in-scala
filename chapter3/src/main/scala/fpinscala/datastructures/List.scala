package fpinscala.datastructures

import scala.annotation.tailrec

sealed trait List[+A]

case object Nil extends List[Nothing]

case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List {

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(dubs: List[Double]): Double = dubs match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(d, ds) => d * product(ds)
  }


  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def tail[A](as: List[A]): List[A] = as match {
    case Nil => throw new IllegalArgumentException("can't tail an empty list")
    case Cons(x, xs) => xs
  }

  def setHead[A](a: A, as: List[A]): List[A] = {
    Cons(a, as)
  }

  def drop[A](l: List[A], n: Int): List[A] = n match {
    case 0 => l
    case _ => l match {
      case Nil => Nil
      case Cons(_, xs) => drop(xs, n - 1)
    }
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case Cons(x, xs) => if (f(x)) dropWhile(xs)(f) else l
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(_, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def foldRight[A, B](a: List[A], z: B)(f: (A, B) => B): B = a match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def foldRightFL[A, B](a: List[A], z: B)(f: (A, B) => B): B =
    foldLeft(reverse(a),z)((a,b) =>f(b,a))


  @annotation.tailrec
  def foldLeft[A,B](a:List[A], z:B)(f:(B,A)=>B):B =a match {
    case Nil => z
    case Cons(x,xs) => foldLeft(xs, f(z,x)) (f)
  }

  def sumFoldRight(l:List[Int]): Int = {
    foldRight(l, 0)(_+_)
  }

  def sumFoldLeft(l:List[Int]): Int = {
    foldLeft(l, 0)(_+_)
  }

  def productFoldRight(l:List[Double]): Double ={
    foldRight(l, 1.0)(_*_)
  }

  def productFoldLeft(l:List[Double]): Double ={
    foldLeft(l, 1.0)(_*_)
  }

  def length[A](l: List[A]): Int = {
    foldRight(l, 0)((_, acc) => acc + 1)
  }

  def reverse[A](l:List[A]): List[A] = {
    foldLeft(l, Nil:List[A])((acc,e) => Cons(e, acc))
  }

  def append[A](l1:List[A], l2:List[A]):List[A] = {
    foldRight(l1, l2)(Cons(_,_))
  }

  def appendFL[A](l1:List[A], l2:List[A]):List[A] = {
    foldLeft(reverse(l1), l2)((a,b) =>Cons(b,a))
  }

}
