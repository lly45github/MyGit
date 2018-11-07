package com.test

import scala.collection.immutable.Range.Int

trait TraitTest {
  def isEqual(x:Any,y:Any): Boolean;
  def isNoEqual(x:Any,y:Any): Boolean = !isEqual(x,y);
}

class AssertEqual() extends TraitTest {
  override def isEqual(x: Any, y: Any): Boolean = {
    if(x.getClass == y.getClass){
      if( x.asInstanceOf[Integer] == y.asInstanceOf[Integer] ){
        return true;
      }
    }
    return false;
  }
}

object Test{
  def main(args: Array[String]): Unit ={
    val aE = new AssertEqual();
    println(aE.isEqual( new Integer(6),new Integer(6) ));
    println(aE.isNoEqual( new Integer(6),new Integer(6) ));
  }
}