package com.github.erip.adt

sealed trait BinaryTree[+A] {
  def left: BinaryTree[A]
  def right: BinaryTree[A]

  def height: Long

  def isPerfectBad: Boolean = this match {
    case Leaf(_) => true
    case Node(l, r) => l.isPerfectBad && r.isPerfectBad && l.height == r.height
  }

  def isPerfect: Option[Long] = this match {
    case Leaf(_) => Some(0)
    case Node(l, r) => (l.isPerfect, r.isPerfect) match {
      case (Some(leftHeight), Some(rightHeight)) if leftHeight == rightHeight=> Some(leftHeight + 1)
      case _ => None
    }
  }
}

final case class Leaf[A](value: A) extends BinaryTree[A] {
  def height: Long = 1
  def left: BinaryTree[Nothing] = throw new IllegalArgumentException("Leaves have no left child")
  def right: BinaryTree[Nothing] = throw new IllegalArgumentException("Leaves have no right child")
}

final case class Node[A](left: BinaryTree[A], right: BinaryTree[A]) extends BinaryTree[A] {
  def height: Long = Math.max(left.height, right.height) + 1
}

object Main extends App {
  val ex1 = Leaf(7)
  val ex2 = Node(Leaf(3), Leaf(4))
  val ex3 = Node(Node(Leaf(2), Leaf(6)), Node(Leaf(1), Leaf(5)))
  val bad = Node(Node(Leaf(8), Leaf(3)), Leaf(12))


  println(ex1.isPerfect)
  println(ex2.isPerfect)
  println(ex3.isPerfect)
  println(bad.isPerfect)

}