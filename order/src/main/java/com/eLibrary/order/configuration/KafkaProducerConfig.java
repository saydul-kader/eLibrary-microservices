package com.eLibrary.order.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.logging.Level;

@Configuration
public class KafkaProducerConfig {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void publishMessage(String topic, Object message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonMessage = objectMapper.writeValueAsString(message);
        LoggerConfig.log(Level.INFO, String.format("** Publishing message -> %s", jsonMessage));
        kafkaTemplate.send(topic, jsonMessage).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                LoggerConfig.log(Level.SEVERE, "Unable to publish message " + jsonMessage);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                LoggerConfig.log(Level.INFO, "Message published successfully " + jsonMessage);
            }
        });

    }
}
