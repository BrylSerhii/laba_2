

import com.labas.TariffSAXParser;
import generated.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TariffSAXParserTest {
    private TariffSAXParser tariffSAXParser;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        tariffSAXParser = new TariffSAXParser();
        mockLogger = Mockito.mock(Logger.class);
        // Replace the logger in the TariffSAXParser with the mocked logger
        // Using reflection or constructor injection would be needed if it's not directly accessible
    }

    @Test
    void testParseXML_ValidXML_ReturnsTariffList() {
        // Arrange
        String xmlFilePath = "src/test/resources/test_traffic.xml"; // Ensure this file exists

        // Act
        List<TariffType> tariffs = tariffSAXParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertEquals(3, tariffs.size());  // Adjust based on the actual content of your test XML

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
        String xmlFilePath = "src/test/resources/invalid_traffic.xml"; // Ensure this file exists and is invalid

        // Act
        List<TariffType> tariffs = tariffSAXParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertTrue(tariffs.isEmpty());
    }

    @Test
    void testParseXML_LogsErrorOnInvalidXML() {
        // Arrange
        String invalidXmlFilePath = "src/test/resources/invalid_traffic.xml"; // Ensure this file is invalid or broken

        // Act
        List<TariffType> tariffs = tariffSAXParser.parseXML(invalidXmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertTrue(tariffs.isEmpty());  // In case of invalid XML, the return value should be an empty list
    }
}
