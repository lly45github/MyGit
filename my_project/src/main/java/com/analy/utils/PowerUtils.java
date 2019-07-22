package com.analy.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.Properties;

public class PowerUtils {
    private static final String ZK_CONNECT = "127.0.0.1:2181";
    private static final int SESSION_TIMEOUT = 30000;
    private static final int CONNTECT_TIMEOUT = 30000;


    public static String getStr(){
        return "1";
    }

    public static void sendMsg(String topic, Properties properties){
        Producer producer = new KafkaProducer(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic,"analy","time 20190721");
        producer.send(record);
        producer.close();
    }

    public static ConsumerRecord<String, String> getMsg(String topic, Properties properties){
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList(topic));
        try {
            while(true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                    System.out.println();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            kafkaConsumer.close();
        }

        return null;
    }
}
