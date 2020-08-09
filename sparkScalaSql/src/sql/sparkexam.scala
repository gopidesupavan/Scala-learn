package sql
import org.apache.spark.sql.SparkSession

import org.apache.spark.SparkConf
import org.apache.spark.sql._
import org.apache.spark.sql.types._
import org.apache.spark.sql.Row
object sparkexam {
  
  
  def main(args:Array[String]):Unit={
    
     System.setProperty("hadoop.home.dir","c:\\winutils\\")
    val conf=new SparkConf().setMaster("local[*]").set("spark.sql.warehouse.dir", "file:///C:/tmp")
    val spark=SparkSession
              .builder()
              .appName("spark exam")
              .config(conf)
              .getOrCreate()
              
    //question1
              
//     val prodDF=spark.read.format("avro").load("C:\\BigDatapath\\Courses\\exam\\products_avro\\")
//     prodDF.show()
//     prodDF.filter("$product_price" > 1000.0)
//     .write.option("compression","snappy")
//     .parquet("C:\\BigDatapath\\Courses\\exam\\output")
//     
              
  //question2
              
   val data=spark.read.option("inferschema",true)
    .csv("C:\\BigDatapath\\Courses\\exam\\products")
    .toDF("product_id","product_category_id","product_name","product_description","product_price","product_image")
    
    data.filter($"product_price"<1000)
    .filter($"product_name".like("%Vector Series%")).show()
    
     spark.stop()
    
    
  }
}