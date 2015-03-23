package com.utiltube.kafka.config;

import java.util.Properties;

import javax.annotation.PostConstruct;

import kafka.producer.ProducerConfig;

import org.springframework.stereotype.Component;

@Component
public class ProducerConfigFactory {

    private static final String BROKER_LIST = "localhost:9092";
    
    private ProducerConfig producerConfig;

    @PostConstruct
    private void createProducerConfig() {
        Properties props = new Properties();
        props.put("metadata.broker.list", BROKER_LIST);
        props.put("serializer.class", "kafka.serializer.StringEncoder");
//        props.put("partitioner.class", "example.producer.SimplePartitioner");
        props.put("request.required.acks", "1");

        producerConfig = new ProducerConfig(props);
    }

    public ProducerConfig getProducerConfig() {
        return producerConfig;
    }
}