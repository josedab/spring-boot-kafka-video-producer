package com.utiltube.kafka.video.producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utiltube.kafka.config.ProducerConfigFactory;
import com.utiltube.kafka.video.model.Video;

@Component
public class VideoProducer {
    
    private static final String TOPIC = "video_test";

    @Autowired
    private ProducerConfigFactory producerConfigFactory;
    
    private Producer<String, String> producer;
    private ObjectMapper objectMapper;
    
    public VideoProducer() {
        objectMapper = new ObjectMapper();
    }
    
    @PostConstruct
    public void startProducing() {
        ProducerConfig producerConfig = producerConfigFactory.getProducerConfig();
        producer = new Producer<String, String>(producerConfig);
        
        produce();
    }
    
    private void produce() {
        for (int i = 0; i < 100; i++) {
            Video video = new Video.Builder("ident" + i, "local", "Title for ident_" + i)
                                   .build();
            try {
                
                String messageData = objectMapper.writeValueAsString(video);
                KeyedMessage<String, String> data = 
                        new KeyedMessage<String, String>(
                                TOPIC, 
                                String.valueOf(i), 
                                messageData);
                producer.send(data);
                
            } catch (JsonProcessingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
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
