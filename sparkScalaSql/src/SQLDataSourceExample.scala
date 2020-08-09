import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import java.util.Properties
object SQLDataSourceExample {
  
  def main(args:Array[String]){
    
    System.setProperty("hadoop.home.dir","c:\\winutils\\")
    val conf=new SparkConf().setMaster("local[*]").set("spark.sql.warehouse.dir", "file:///C:/tmp")
    val spark =SparkSession
               .builder()
               .appName("spark sql examples")
               .config(conf)
               .getOrCreate()
               
               
    runBasicDataSourceExample(spark)
    
    spark.stop()
  }
   private def runBasicDataSourceExample(spark: SparkSession): Unit = {
    val peopleDF=spark.read.format("json").load("C:\\BigDatapath\\Courses\\spark-scala\\spark-github\\thresponse.json")
    
    peopleDF.show()
    
//    peopleDF.printSchema()
//    peopleDF.write.format("parquet").save("people.parquet")
//  val peopleParq=spark.read.parquet("./people.parquet")
//  peopleParq.createOrReplaceTempView("temppeople")
//  spark.sql("select * from temppeople").collect().foreach(println)
   }
  
  
}