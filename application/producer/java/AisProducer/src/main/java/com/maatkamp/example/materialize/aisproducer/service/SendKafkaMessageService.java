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

    @Value("${topic.name}")
    private String topic;

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public SendKafkaMessageService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AisVesselData aisVesselData) {
        kafkaTemplate.send(topic, aisVesselData.getIMO().toString(), aisVesselData);
        log.info(String.format(" -> %s", aisVesselData));
    }
}