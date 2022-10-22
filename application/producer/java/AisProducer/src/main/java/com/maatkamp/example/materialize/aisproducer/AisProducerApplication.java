package com.maatkamp.example.materialize.aisproducer;

import com.maatkamp.example.materialize.aisproducer.service.SendKafkaMessageService;
import gov.marinecadastre.ais.AisVesselData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalTime;

@SpringBootApplication
public class AisProducerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AisProducerApplication.class, args);
        SendKafkaMessageService sendKafkaMessageService = ctx.getBean(SendKafkaMessageService.class);

        AisVesselData aisVesselData =
                AisVesselData.
                    newBuilder().
                        setVesselType(1).
                        setVesselName("vesselName").
                        setBaseDateTime(LocalTime.now()).
                        setCOG(0.0).
                        setCallSign("callsign").
                        setCargo(0).
                        setDraft(0.0).
                        setHeading(0.0).
                        setIMO("IMO").
                        setLat(0.0).
                        setLon(0.0).
                        setLength(0).
                        setMmsi(123456).
                        setSOG(0.0).
                        setStatus(0).
                        setWidth(0).
                        setStatus(0).
                        setLon(0).
                        setTransceiverClass("transceiverClass").
                    build();

        sendKafkaMessageService.send(aisVesselData);
	}

}
