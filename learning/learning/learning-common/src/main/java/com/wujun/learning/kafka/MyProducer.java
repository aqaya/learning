package com.wujun.learning.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyProducer implements Runnable{

    Producer<String,Object> producer;
    private String topicName;

    public MyProducer(String topicName, Producer<String, Object> producer) {
        this.producer = producer;
        this.topicName = topicName;
    }

    @Override
    public void run() {
        for (long i = 0; i < 100; i++){
            producer.send(new ProducerRecord<String, Object>(topicName, "Counts", i));
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
