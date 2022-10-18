package com.maatkamp.example.materialize.aisproducer;

import com.maatkamp.example.materialize.aisproducer.service.SendKafkaMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AisProducerApplication {
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

	public static void main(String[] args) {
		var ctx = SpringApplication.run(AisProducerApplication.class, args);
        SendKafkaMessageService sendKafkaMessageService = ctx.getBean(SendKafkaMessageService.class);
        sendKafkaMessageService.
	}

}
