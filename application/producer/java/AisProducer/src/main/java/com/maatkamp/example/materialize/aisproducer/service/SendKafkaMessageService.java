package com.maatkamp.example.materialize.aisproducer.service;

import gov.marinecadastre.ais.AisVesselData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@EnableScheduling
public class SendKafkaMessageService {
    private final KafkaTemplate<String, AisVesselData> kafkaTemplate;
    private final String topicName;

    @Autowired
    public SendKafkaMessageService(
            KafkaTemplate<String, AisVesselData> kafkaTemplate,
            @Value(value = "${monitor.topic.name}") String topicName,
            @Value(value = "${monitor.producer.simulate}") String enabled) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void send(AisVesselData aisVesselData) {
        kafkaTemplate.send(topicName, aisVesselData);
    }

}