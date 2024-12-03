package com.labas;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

public class XSLTTransformer {


    public static void extracted( String xmlFilePath,String xsltFilePath,String outputFilePath) {
        try {
            // Load the XML input file
            File xmlFile = new File(xmlFilePath);
            File xsltFile = new File(xsltFilePath); // Use .xsl for XSLT file
            File outputFile = new File(outputFilePath); // Specify output file name

            // Set up the transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(xsltFile);
            Transformer transformer = factory.newTransformer(xslt);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "4"); // Specify indentation size
            // Perform the transformation
            Source xmlSource = new StreamSource(xmlFile);
            Result output = new StreamResult(outputFile);
            transformer.transform(xmlSource, output);

        } catch (TransformerException e) {
        }
    }
}
