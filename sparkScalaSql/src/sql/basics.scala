package sql

import org.apache.spark.sql.SparkSession

import org.apache.spark.SparkConf

import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
object basics {
  
  def main(args: Array[String]):Unit={
   println({
     val x:Int=1+1
     x
   })
   
   val Addone=(x:Int) => x+1
   println(Addone(1))
   
   val Twoadd=(x:Int,y:Int) => x+y
   
   println(Twoadd(2,2))
   
   def method1(x:Int,y:Int):Int=x+y
   
   println(method1(1,4))
   
   def method2(x:Int,y:Int):Int= x+y
   
   def multipleparameter(x:Int,y:Int)(Mult:Int):Int=(x+y) * Mult
   
   println(multipleparameter(2, 3)(4))
   
   def square(x: Double):String={
     val y=x*x
     y.toString()
     
   }
   square(1)
   
  
 val point1=new Point
  point1.x
  point1.x=100
  
  val values=("one",3)
  println(values._1)
}
 
  
  class Point {
  private var _x = 0
  private var _y = 0
  private val bound = 100
  
  def x=_x 
  println(x)

//  def x = _x
  def x_= (newValue: Int): Unit = {
//    if (newValue < bound) _x = newValue else printWarning
    _x=newValue
    println(_x);
  }
//
//  def y = _y
//  def y_= (newValue: Int): Unit = {
//    if (newValue < bound) _y = newValue else printWarning
//  }
//
//  private def printWarning = println("WARNING: Out of bounds")
}
  
}