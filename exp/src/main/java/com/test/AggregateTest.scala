package com.test

object AggregateTest {
  def main(args: Array[String]):Unit={
    val list = List(1,2,3,4,5,6,7,8,9);
    val (mul, sum, count) = list.par.aggregate((1, 0, 0))((acc, number) => (acc._1 * number, acc._2 + number, acc._3 + 1), (x, y) => (x._1 * y._1, x._2 + y._2, x._3 + y._3))
    println(sum / count, mul);
  }

}
