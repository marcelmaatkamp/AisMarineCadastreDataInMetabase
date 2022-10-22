package gov.marinecadastre.ais;

import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.typeManufacturers.CharSequenceTypeManufacturerImpl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AisVesselDataTest {

    @Test
    public void testAisVesselData() {
        PodamFactory podamFactory = new PodamFactoryImpl();
        podamFactory.getStrategy().addOrReplaceTypeManufacturer(CharSequence.class, new CharSequenceTypeManufacturerImpl());

        AisVesselData aisVesselData = podamFactory.manufacturePojoWithFullData(AisVesselData.class);
        assertThat(aisVesselData).
                isNotNull().
                hasNoNullFieldsOrProperties();
    }


    @Test
    public void baseDateTimeFormatterTest() {
        String baseDateTime = "2022-06-30T00:00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localTime = LocalDateTime.parse(baseDateTime, formatter);
        assertNotNull(localTime);
        System.out.println(localTime);
    }
}