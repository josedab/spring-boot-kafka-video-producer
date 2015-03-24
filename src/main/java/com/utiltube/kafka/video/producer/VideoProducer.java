package com.utiltube.kafka.video.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.utiltube.kafka.config.ProducerConfigFactory;

@Component
public class VideoProducer {
    
    private static final String TOPIC = "test";

    @Autowired
    private ProducerConfigFactory producerConfigFactory;
    
    private Producer<String, String> producer;
    @PostConstruct
    public void startProducing() {
        ProducerConfig producerConfig = producerConfigFactory.getProducerConfig();
        producer = new Producer<String, String>(producerConfig);
        
        produce();
    }
    
    private void produce() {
        for (int i = 0; i < 100; i++) {
            KeyedMessage<String, String> data = 
                            new KeyedMessage<String, String>(
                                    TOPIC, 
                                    String.valueOf(i), 
                                    "Testing producer message " + i);
            producer.send(data);
        }
        producer.close();
    }

    @PreDestroy
    public void destroy() {
        if (producer != null) {
            producer.close();
        }
    }
}
