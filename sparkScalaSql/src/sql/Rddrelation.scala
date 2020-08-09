package sql

import org.apache.spark.sql.SparkSession

import org.apache.spark.SparkConf

import org.apache.spark.sql.types._
import org.apache.spark.sql.Row

object Rddrelation {
  
  
  case class Record(key:Int,value:String)
  
  def main(args: Array[String]):Unit={
    System.setProperty("hadoop.home.dir","c:\\winutils\\")
    val conf=new SparkConf().setMaster("local[*]").set("spark.sql.warehouse.dir", "file:///C:/tmp")
    val spark=SparkSession
              .builder()
              .appName("rdd relation")
              .config(conf)
              .getOrCreate()
              
     import spark.implicits._
     val df=spark.createDataFrame((1 to 100).map(i=>Record(i,s"val_$i")))
     df.createOrReplaceTempView("records");
    //printing results
    
    spark.sql("select * from records").collect().foreach(println)
  val count=spark.sql("select count(*) from records").collect().head.getLong(0)
  println(s"Count(*):$count");
  val rddfromsql=spark.sql("select key, value from records where key<80")
  
   println("Result of RDD.map:")
    rddfromsql.rdd.map(row => s"Key: ${row(0)}, Value: ${row(1)}").collect().foreach(println)
   programaticschema(spark)
  spark.stop()
  }
  
  private def programaticschema(spark: SparkSession):Unit={
    import spark.implicits._
    
    val peopelrdd=spark.sparkContext.textFile("C:\\BigDatapath\\Courses\\spark-scala\\spark-github\\resources\\people.txt")
    
    val schemastring="name age"
    
    val fields=schemastring.split(" ")
               .map(fieldName=>StructField(fieldName,StringType,nullable=true))
               val schema=StructType(fields)
              
             val rowrdd=peopelrdd.map(_.split(","))
                        .map(attributes=>Row(attributes(0),attributes(1).trim))
     val peopledf=spark.createDataFrame(rowrdd, schema)
     peopledf.createOrReplaceTempView("people")
     val queryresult=spark.sql("select * from people")
     queryresult.printSchema();
     
     queryresult.map(attributes=>"Name :"+attributes(0)).show()
             
  }
}