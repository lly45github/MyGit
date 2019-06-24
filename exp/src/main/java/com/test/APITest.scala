package com.test

object APITest {

  def main(args:Array[String]):Unit={
    val list0 = List(2,6,5,3,8,4,1,9,0,7);

    val list1 = list0.map(_*2);
    println("list1 "+list1.toString());

    val list2 = list0.filter(x=>x%2==0);
    println("list2 "+list2.toString());

    val list3 = list0.sorted;
    println("list3 "+list3.toString());

    val list4 = list0.reverse;
    println("list4 "+list4.toString());

    val itr = list0.grouped(4);
    println("itr "+itr.toString());

    val list5 = itr.toList;
    println("list5 "+list5.toString());

    val list6 = list5.flatten;
    println("list6 "+list6.toString());

    val lines = List("hello java ", "hello scala", "hello python");
    println("lines "+lines.toString());

    val res =lines.flatMap(_.split(" "));
    println("res "+res.toString());

    val arr = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("arr "+arr.toString());

    //    val res1 = arr.sum
    //和线程有关，会每个线程计算一部分，例如：（（1+2+3+4）+（5+6+7+8）+（9+10））
    val res1 = arr.par.sum
    println("res1 "+res1.toString());

    val res2 = arr.reduce(_ + _);
    println("res2 "+res2.toString());

    val res3 = arr.reduceLeft(_+_)
    println("res3 "+res3.toString());

    val res4 = arr.reduceRight(_+_)
    println("res4 "+res4.toString());

    val res5 = arr.par.reduce(_+_)     //并行执行，如果执行减（_-_），每次的结果是不一样的。
    println("res5 "+res5.toString());

    val res6 = arr.fold(0)(_ + _) //聚合时要将初始值累加进去
    println("res6 "+res6.toString());

    val res7 = arr.par.fold(10)(_ + _) //并行执行，初始值每个线程都加10，每次运行结果不一样。
    println("res7 "+res7.toString());

    //折叠：有初始值（有特定顺序）
    val res8 = arr.foldLeft(10)(_ + _);
    println("res8 "+res8.toString());

    val res9 = arr.foldRight(10)(_ + _);
    println("res9 "+res9.toString());

    val res10 = arr.foldLeft(10)(_ - _);
    println("res10 "+res10.toString());

    //该语句中每次运行结果都一样，是-45，因为foldLeft左折叠优先，从左向右顺序运行，par可以省略。
    val res11 = arr.par.foldLeft(10)(_ - _);
    println("res11 "+res11.toString());

    //聚合
    val list7 = List(List(1,2,3),List(3,4,5),List(2),List(0));
    println("list7 "+list7.toString());

    val res12 = list7.flatten.reduce(_+_);
    println("res12 "+res12.toString());

    //第一个参数，第一个_表示括号内的初始值，第二个_表示每一个小的List，.sum表示每个小的List局部聚合。
    //第二个参数，表示全局聚合。
    val res13 = list7.aggregate(1)(_+_.sum,_+_);
    println("res13 "+res13.toString());


    val l1 = List(5,6,4,7)
    val l2 = List(1,2,3,4)
    //求并集
    val res14 = l1 union l2;
    println("res14 "+res14.toString());

    //求交集
    val res15 = l1 intersect l2;
    println("res15 "+res15.toString());

    //求差集
    val res16 = l1 diff l2;
    println("res16 "+res16.toString());

  }

}
