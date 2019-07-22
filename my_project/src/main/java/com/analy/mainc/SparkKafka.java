package com.analy.mainc;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SparkKafka {
    private static final Logger logger = LoggerFactory.getLogger(SparkKafka.class);

    public static void main(String args[]) throws Exception {
        SparkKafka sparkKafka = new SparkKafka();
        sparkKafka.run();
    }

    public void run() throws InterruptedException {
        SparkConf sparkConf = new SparkConf().setAppName("KafkaConsumer").setMaster("local[2]");

        JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.milliseconds(500));
        //jssc.checkpoint("hdfs://1127.0.0.1:9000/checkpoint/Kafka10Consumer");
        Set topicsSet = new HashSet(Arrays.asList("hello-topic"));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "127.0.0.1:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "Kafka10Consumer");
        kafkaParams.put("auto.offset.reset", "earliest");//earliest : 从最早开始；latest ：从最新开始
        kafkaParams.put("enable.auto.commit", true);

        JavaInputDStream<ConsumerRecord<Object, Object>> messages = KafkaUtils.createDirectStream(jssc, LocationStrategies.PreferConsistent(), ConsumerStrategies.Subscribe(topicsSet, kafkaParams));

        JavaDStream lines = messages.map((Function<ConsumerRecord<Object, Object>, String>) consumerRecord -> consumerRecord.value().toString());

        lines.foreachRDD((VoidFunction<JavaRDD>) rdd -> rdd.foreach(s -> logger.debug(s.toString())));
        jssc.start();
        jssc.awaitTermination();
    }
}
