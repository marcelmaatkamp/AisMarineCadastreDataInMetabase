package com.maatkamp.example.materialize.aisproducer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.maatkamp.example.materialize.aisproducer.service.SendKafkaMessageService;
import com.maatkamp.example.materialize.aisproducer.util.AisHeaderElement;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import gov.marinecadastre.ais.AisVesselData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

import static com.maatkamp.example.materialize.aisproducer.util.AisHeaderElement.*;

/**
 * @author Marcel Maatkamp <m.maatkamp@gmail.com>
 */
@SpringBootApplication
public class AisProducerApplication {
    @Parameter(names={"--file", "-f"}, description = "location of ais csv-file")
    String filename = "data/AIS_2022_06_30.csv";

 	public static void main(String[] args) throws IOException, CsvValidationException {
        AisProducerApplication aisProducerApplication = new AisProducerApplication();
        JCommander.newBuilder()
                .addObject(aisProducerApplication)
                .build()
                .parse(args);

        ConfigurableApplicationContext ctx = SpringApplication.run(AisProducerApplication.class, args);
        sendAisMessages(ctx, aisProducerApplication.filename);
    }

    private static void sendAisMessages(ConfigurableApplicationContext ctx, String filename) throws IOException, CsvValidationException {
        SendKafkaMessageService sendKafkaMessageService = ctx.getBean(SendKafkaMessageService.class);
        CSVReader reader = new CSVReaderBuilder(new FileReader(filename)).build();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String [] nextLine;
        String[] header = reader.readNext();

        while ((nextLine = reader.readNext()) != null) {
            sendKafkaMessageService.send(getAisVesselData(nextLine));
        }
	}

    private static AisVesselData getAisVesselData(String[] nextLine) {
        AisVesselData aisVesselData;
        aisVesselData =
            AisVesselData.newBuilder().
                setMmsi(getIntValue(nextLine[MSSI.position])).
                setBaseDateTime(Instant.parse(nextLine[BASEDATETIME.position]+"Z")).
                setLat(getDoubleValue(nextLine[LAT.position])).
                setLon(getDoubleValue(nextLine[LON.position])).
                setSOG(getDoubleValue(nextLine[SOG.position])).
                setCOG(getDoubleValue(nextLine[COG.position])).
                setHeading(getDoubleValue(nextLine[HEADING.position])).
                setVesselName(nextLine[VESSELNAME.position]).
                setIMO(nextLine[IMO.position]).
                setCallSign(nextLine[CALLSIGN.position]).
                setVesselType(getIntValue(nextLine[VESSELTYPE.position])).
                setStatus(getIntValue(nextLine[STATUS.position])).
                setLength(getIntValue(nextLine[LENGTH.position])).
                setWidth(getIntValue(nextLine[WIDTH.position])).
                setDraft(getDoubleValue(nextLine[DRAFT.position])).
                setCargo(getIntValue(nextLine[CARGO.position])).
                setTransceiverClass(nextLine[TRANSCEIVERCLASS.position]).
            build();
        return aisVesselData;
    }

    private static Integer getIntValue(String value) {
        return ((value != null && !value.isEmpty()) ? Integer.parseInt(value) : null);
    }
    private static Double getDoubleValue(String value) {
        return ((value != null && !value.isEmpty())  ? Double.parseDouble(value) : null);
    }

}
