
import com.labas.XSLTTransformer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XSLTTransformerTest {

    private static final String XML_FILE_PATH = "src/test/resources/test_traffic.xml";
    private static final String XSLT_FILE_PATH = "src/main/resources/tariff_transform.xslt";
    private static final String OUTPUT_FILE_PATH = "src/test/resources/test_traffic_xslt.xml";



    @AfterEach
    public void tearDown() {
        // Clean up the output file after each test
        File outputFile = new File(OUTPUT_FILE_PATH);
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    @Test
    public void testXSLTTransformation() {
       XSLTTransformer.extracted(XML_FILE_PATH,XSLT_FILE_PATH,OUTPUT_FILE_PATH);
        // Execute the transformation

        // Verify the output file is created
        File outputFile = new File(OUTPUT_FILE_PATH);
        assertTrue(outputFile.exists(), "Output file should be created after transformation");
    }

    @Test
    public void testOutputFileContent() throws Exception {
        // Execute the transformation

        XSLTTransformer.extracted(XML_FILE_PATH,XSLT_FILE_PATH,OUTPUT_FILE_PATH);
        // Verify the output file is created
        File outputFile = new File(OUTPUT_FILE_PATH);
        assertTrue(outputFile.exists(), "Output file should be created after transformation");

        // Read the content of the output file
        String content = new String(Files.readAllBytes(outputFile.toPath()));

        // You can add further assertions based on expected content.
        assertTrue(content.contains("<TariffsByOperator/>"), "Output should contain expected elements");
    }
}
