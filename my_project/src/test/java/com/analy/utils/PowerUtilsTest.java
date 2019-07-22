package com.analy.utils;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Properties;

public class PowerUtilsTest {

    @Test
    public void getStr() {
        System.out.print(PowerUtils.getStr());
    }

    @Test
    public void sendMsg() {
        String topic = "hello-topic";
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("request.required.acks","-1");

        PowerUtils.sendMsg(topic, properties);
    }

    @Test
    public void getMsg() {
        String topic = "hello-topic";
        Properties props = new Properties();
        // 服务器ip:端口号，集群用逗号分隔
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 消费者指定组，名称可以随意，注意相同消费组中的消费者只能对同一个分区消费一次
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "test");
        // 是否启用自动提交，默认true
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 自动提交间隔时间1s
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        // key反序列化指定类
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value反序列化指定类，注意生产者与消费者要保持一致，否则解析出问题
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        ConsumerRecord<String, String> kfStream = PowerUtils.getMsg(topic, props);
    }
}