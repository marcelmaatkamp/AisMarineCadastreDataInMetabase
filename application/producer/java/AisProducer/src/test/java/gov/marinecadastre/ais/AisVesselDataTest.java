package gov.marinecadastre.ais;

import org.junit.jupiter.api.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import uk.co.jemos.podam.typeManufacturers.CharSequenceTypeManufacturerImpl;

import static org.assertj.core.api.Assertions.assertThat;

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
}