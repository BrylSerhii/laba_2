

import com.labas.XMLValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class XMLValidatorTest {
    XMLValidator validator = new XMLValidator();

    @Test
    void testValidXML() {
        String xsdPath = "xsd/tariff.xsd";
        String xmlPath = "tariff.xml";

        assertTrue(validator.validateXMLSchema(xsdPath, xmlPath));
    }

    @Test
    void testInvalidXML() {
        String xsdPath = "xsd/tariff.xsd";
        String xmlPath = "invalid_traffic.xml";

        assertFalse(validator.validateXMLSchema(xsdPath, xmlPath));
    }
}