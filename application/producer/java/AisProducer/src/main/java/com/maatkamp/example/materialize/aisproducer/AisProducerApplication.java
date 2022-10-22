package com.maatkamp.example.materialize.aisproducer;

import com.maatkamp.example.materialize.aisproducer.service.SendKafkaMessageService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import gov.marinecadastre.ais.AisVesselData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class AisProducerApplication {

	public static void main(String[] args) throws IOException, CsvValidationException {
        ConfigurableApplicationContext ctx = SpringApplication.run(AisProducerApplication.class, args);
        sendAisMessages(ctx, "data/AIS_2022_06_30.csv");
    }

    private static void sendAisMessages(ConfigurableApplicationContext ctx, String filename) throws IOException, CsvValidationException {
        SendKafkaMessageService sendKafkaMessageService = ctx.getBean(SendKafkaMessageService.class);
        CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).build();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String [] nextLine;
        AisVesselData aisVesselData = null;
        String[] header = reader.readNext();

        while ((nextLine = reader.readNext()) != null) {
            // MMSI,BaseDateTime,LAT,LON,SOG,COG,Heading,VesselName,IMO,CallSign,VesselType,Status,Length,
            // Width,Draft,Cargo,TransceiverClass
            aisVesselData = AisVesselData.newBuilder().
                    setMmsi(getIntValue(nextLine[0])).
                    setBaseDateTime(LocalDateTime.parse(nextLine[1], formatter)).
                    setLat(getDoubleValue(nextLine[2])).
                    setLon(getDoubleValue(nextLine[3])).
                    setSOG(getDoubleValue(nextLine[4])).
                    setCOG(getDoubleValue(nextLine[5])).
                    setHeading(getDoubleValue(nextLine[6])).
                    setVesselName(nextLine[7]).
                    setIMO(nextLine[8]).
                    setCallSign(nextLine[9]).
                    setVesselType(getIntValue(nextLine[10])).
                    setStatus(getIntValue(nextLine[11])).
                    setLength(getIntValue(nextLine[12])).
                    setWidth(getIntValue(nextLine[13])).
                    setDraft(getDoubleValue(nextLine[14])).
                    setCargo(getIntValue(nextLine[15])).
                    setTransceiverClass(nextLine[16]).
                    build();
            sendKafkaMessageService.send(aisVesselData);
        }
	}

    private static Integer getIntValue(String value) {
        return ((value != null && !value.isEmpty()) ? Integer.parseInt(value) : null);
    }
    private static Double getDoubleValue(String value) {
        return ((value != null && !value.isEmpty())  ? Double.parseDouble(value) : null);
    }

}
