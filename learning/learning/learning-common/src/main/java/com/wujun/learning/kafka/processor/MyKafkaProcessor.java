package com.wujun.learning.kafka.processor;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

public class MyKafkaProcessor<K, V> implements Processor<K, V> {
    @Override
    public void init(ProcessorContext context) {
        System.out.println("init......");
    }

    @Override
    public void process(K key, V value) {
        System.out.println("key = [" + key + "], value = [" + value + "]");
    }

    @Override
    public void punctuate(long timestamp) {
        System.out.println("timestamp = [" + timestamp + "]");
    }

    @Override
    public void close() {
        System.out.println("close...");
    }
}
