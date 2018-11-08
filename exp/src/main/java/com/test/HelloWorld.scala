package com.test

// 私有构造方法
class Marker private(val color:String) {
  println("创建" + this)
  override def toString(): String = "颜色标记："+ color
}

// 伴生对象，与类共享名字，可以访问类的私有属性和方法
object Marker{

  def values(fun:(Int)=>Int, low:Int, high:Int):List[(Int, Int)] = {
    var col = List[(Int,Int)]();
    for(a<-low to high){
      col = (a,fun(a))::col;
    }
    return col;
  }
  def main(args: Array[String]) {
    println( values(x=>x*x,-5,5) );
  }
}