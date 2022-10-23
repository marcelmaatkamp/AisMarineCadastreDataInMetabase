package com.maatkamp.example.materialize.aisproducer.service;

import gov.marinecadastre.ais.AisVesselData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * Sends @{@link AisVesselData} to kafka
 *
 * @author Marcel Maatkamp <m.maatkamp@gmail.com>
 */
@Service
@Slf4j
@EnableScheduling
public class SendKafkaMessageService {

    private final KafkaTemplate<String, AisVesselData> kafkaTemplate;

    @Autowired
    public SendKafkaMessageService(KafkaTemplate<String, AisVesselData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(AisVesselData aisVesselData) {
        kafkaTemplate.sendDefault(aisVesselData.getMmsi().toString(), aisVesselData);
        // log.info(String.format(" -> %s", aisVesselData));
    }
}