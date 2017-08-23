package com.wujun.learning.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Date;

public class MyConsumer implements Runnable{

    Consumer<String,Object> consumer;

    public MyConsumer(Consumer<String, Object> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        while (true){
            ConsumerRecords<String, Object> poll = consumer.poll(1000);
            if (poll != null){
                for (ConsumerRecord<String, Object> record : poll) {

                    System.out.printf(new Date() + "----------" + Thread.currentThread().getName() + " record.partition() = %d, offset = %d, key = %s, value = %s%n",record.partition(),  record.offset(), record.key(), record.value());
                }
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("poll null...");
            }
        }
    }
}
