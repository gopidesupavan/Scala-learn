package com.test
import Utilities._
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.log4j.Level
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.streaming.twitter.TwitterUtils

object learn {
  
  
  def main(args: Array[String]){
    
    System.setProperty("hadoop.home.dir", "c:\\winutils\\")
  
    setupTwitter()
    
    val ssc=new StreamingContext("local[*]","PrintTweets",Seconds(1))

  setupLogging()
  
  val tweets=TwitterUtils.createStream(ssc,None)
  
  val status= tweets.map(status=>status.getText)
  
  val splitdata=status.flatMap(status=>status.split(" "))
  
  val hastagvalues=splitdata.filter(splitdata=>splitdata.startsWith("#"))
  
  hastagvalues.foreachRDD((rdd,time)=>{
      if(rdd.count()>0){
        
        //val repartitionrdd=rdd.rep
        rdd.saveAsTextFile("tweets_"+time.milliseconds.toString())
        
        
      }
      
    })
  
  
  
  ssc.checkpoint("c:\\checkpoint\\")
  
  ssc.start()
  ssc.awaitTermination()
  
  }
  
}