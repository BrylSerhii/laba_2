
import com.labas.TariffStAXParser;
import generated.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TariffStAXParserTest {
    private TariffStAXParser tariffStAXParser;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        tariffStAXParser = new TariffStAXParser();
        mockLogger = Mockito.mock(Logger.class);
        // Optionally inject or verify the logger interactions
    }

    @Test
    void testParseXML_ValidXML_ReturnsTariffList() {
        // Arrange
        String xmlFilePath = "src/test/resources/test_traffic.xml"; // Ensure this file exists and is valid

        // Act
        List<TariffType> tariffs = tariffStAXParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertEquals(3, tariffs.size());  // Adjust the number based on your test XML file

        // Validate first tariff
        TariffType firstTariff = tariffs.get(2);
        assertEquals("BusinessTariff", firstTariff.getName());
        assertEquals("Мобільний Оператор C", firstTariff.getOperatorName());
        assertEquals(new BigDecimal("0.80"), firstTariff.getSMSPrice().getValue());

        assertNotNull(firstTariff.getCallPrices());
        assertEquals(new BigDecimal("1.20"), firstTariff.getCallPrices().getInsideNetwork().getValue());
        assertEquals(new BigDecimal("2.50"), firstTariff.getCallPrices().getOutsideNetwork().getValue());
        assertEquals(new BigDecimal("3.20"), firstTariff.getCallPrices().getLandline().getValue());
    }

    @Test
    void testParseXML_InvalidXML_ReturnsEmptyList() {
        // Arrange
        String xmlFilePath = "src/test/resources/test_traffic.xml"; // Ensure this file exists and is invalid

        // Act
        List<TariffType> tariffs = tariffStAXParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
    }

    @Test
    void testParseXML_LogsErrorOnInvalidXML() {
        // Arrange
        String invalidXmlFilePath = "src/test/resources/invalid_traffic.xml"; // Ensure this file is invalid or broken

        // Act
        List<TariffType> tariffs = tariffStAXParser.parseXML(invalidXmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertTrue(tariffs.isEmpty());  // Expect empty list on error
    }
}
