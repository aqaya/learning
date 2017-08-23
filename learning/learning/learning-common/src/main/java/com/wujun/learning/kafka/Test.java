package com.wujun.learning.kafka;

import com.google.common.collect.Sets;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class Test {

    public static void main(String[] args) throws InterruptedException {


        String topicName = "WordsWithCountsTopic";//"my-partition-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
        props.put("metrics.recording.level", "DEBUG");
        //for consumers
        props.put("group.id", "test");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");

        Producer<String, Object> producer = new KafkaProducer<>(props);

        Consumer<String, Object> consumer1 = new KafkaConsumer<>(props);
        consumer1.subscribe(Sets.newHashSet(topicName));
        Thread produceThread = new Thread(new MyProducer(topicName, producer));
        produceThread.setName("producer");
        Thread consumeThread1 = new Thread(new MyConsumer(consumer1));
        consumeThread1.setName("consumer1");

        produceThread.start();
        consumeThread1.start();

        Thread.sleep(5000L);

        Consumer<String, Object> consumer2 = new KafkaConsumer<>(props);
        consumer2.subscribe(Sets.newHashSet(topicName));
        Thread consumeThread2 = new Thread(new MyConsumer(consumer2));
        consumeThread2.setName("consumer2");
        consumeThread2.start();


    }

}
