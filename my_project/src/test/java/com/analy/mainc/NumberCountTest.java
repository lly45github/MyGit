package com.analy.mainc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.junit.Test;
import scala.Tuple2;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NumberCountTest {

    @Test
    public void run() {
        String filePath = "file:\\E:\\test.txt";
        SparkConf sparkConf = new SparkConf().setAppName("number-count").setMaster("local[2]");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = jsc.textFile(filePath,1);
        lines.persist(StorageLevel.MEMORY_AND_DISK());
        JavaPairRDD<String, Integer> finalRDD = lines.flatMap(f -> Arrays.asList(f.split(" ")).iterator())
                .mapToPair(word->new Tuple2<>(word, 1)).reduceByKey((a, b)->a+b).mapToPair(sw -> sw.swap())
                .sortByKey(false).mapToPair(sw->sw.swap());
        finalRDD.collect().forEach(t->System.out.println(t._1()+" "+t._2()));
        /*List<Tuple2<String, Integer>> finalList = finalRDD.collect();
        for(Tuple2<String, Integer > tmp: finalList){
            System.out.print();
        }*/

        jsc.stop();
    }
}