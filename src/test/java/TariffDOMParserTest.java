

import com.labas.TariffDOMParser;
import generated.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TariffDOMParserTest {
    private TariffDOMParser tariffDOMParser;

    @BeforeEach
    void setUp() {
        tariffDOMParser = new TariffDOMParser();
    }

    @Test
    void testParseXML_ValidXML_ReturnsTariffList() {
        // Arrange
        String xmlFilePath = "src/test/resources/test_traffic.xml"; // Ensure this file exists

        // Act
        List<TariffType> tariffs = tariffDOMParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertEquals(3, tariffs.size());

        // Validate first tariff
        TariffType firstTariff = tariffs.get(0);
        assertEquals("SuperTariff", firstTariff.getName());
        assertEquals("Мобільний Оператор A", firstTariff.getOperatorName());
        assertEquals(new BigDecimal("250.00"), firstTariff.getPayroll().getValue());
        assertEquals("UAH", firstTariff.getPayroll().getCurrency());

        // Check call prices
        assertNotNull(firstTariff.getCallPrices());
        assertEquals(new BigDecimal("1.50"), firstTariff.getCallPrices().getInsideNetwork().getValue());
        assertEquals(new BigDecimal("2.00"), firstTariff.getCallPrices().getOutsideNetwork().getValue());
        assertEquals(new BigDecimal("3.00"), firstTariff.getCallPrices().getLandline().getValue());

        // Validate SMS price
        assertEquals(new BigDecimal("0.75"), firstTariff.getSMSPrice().getValue());

        // Validate Parameters
        assertEquals(BigInteger.valueOf(3), firstTariff.getParameters().getFavoriteNumber());
        assertEquals("12_second", firstTariff.getParameters().getTariffing());
        assertEquals(new BigDecimal("50.00"), firstTariff.getParameters().getConnectionFee().getValue());
    }

    @Test
    void testParseXML_InvalidXML_ReturnsEmptyList() {
        // Arrange
        String xmlFilePath = "src/test/resources/invalid_tariff.xml"; // Ensure this file exists and is invalid

        // Act
        List<TariffType> tariffs = tariffDOMParser.parseXML(xmlFilePath);

        // Assert
        assertNotNull(tariffs);
        assertTrue(tariffs.isEmpty());
    }
}
