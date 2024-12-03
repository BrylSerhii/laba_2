package com.labas;

import generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.EndElement;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TariffStAXParser {
    private static final Logger logger = LoggerFactory.getLogger(TariffStAXParser.class);
    private List<TariffType> tariffs = new ArrayList<>();

    public List<TariffType> parseXML(String xmlFilePath) {
        try {
            logger.info("StAX parsing of tariffs started.");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(xmlFilePath));

            TariffType tariff = null;
            CallPricesType callPrices = null;
            ParametersType parameters = null;
            PayrollType payroll = null;
            StringBuilder data = new StringBuilder();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    if (qName.equalsIgnoreCase("Tariff")) {
                        tariff = new TariffType();
                    } else if (qName.equalsIgnoreCase("CallPrices")) {
                        callPrices = new CallPricesType();
                    } else if (qName.equalsIgnoreCase("Parameters")) {
                        parameters = new ParametersType();
                    } else if (qName.equalsIgnoreCase("Payroll")) {
                        payroll = new PayrollType();
                    }
                } else if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    String qName = endElement.getName().getLocalPart();

                    if (qName.equalsIgnoreCase("Name")) {
                        tariff.setName(data.toString().trim());
                    } else if (qName.equalsIgnoreCase("OperatorName")) {
                        tariff.setOperatorName(data.toString().trim());
                    } else if (qName.equalsIgnoreCase("Payroll")) {
                        payroll.setValue(new BigDecimal(data.toString().trim()));
                        payroll.setCurrency("UAH"); // Default currency
                        tariff.setPayroll(payroll);
                    } else if (qName.equalsIgnoreCase("InsideNetwork")) {
                        PayrollType insideNetwork = new PayrollType();
                        insideNetwork.setValue(new BigDecimal(data.toString().trim()));
                        callPrices.setInsideNetwork(insideNetwork);
                    } else if (qName.equalsIgnoreCase("OutsideNetwork")) {
                        PayrollType outsideNetwork = new PayrollType();
                        outsideNetwork.setValue(new BigDecimal(data.toString().trim()));
                        callPrices.setOutsideNetwork(outsideNetwork);
                    } else if (qName.equalsIgnoreCase("Landline")) {
                        PayrollType landline = new PayrollType();
                        landline.setValue(new BigDecimal(data.toString().trim()));
                        callPrices.setLandline(landline);
                    } else if (qName.equalsIgnoreCase("SMSPrice")) {
                        PayrollType smsPrice = new PayrollType();
                        smsPrice.setValue(new BigDecimal(data.toString().trim()));
                        tariff.setSMSPrice(smsPrice);
                    } else if (qName.equalsIgnoreCase("FavoriteNumber")) {
                        parameters.setFavoriteNumber(BigInteger.valueOf(Long.parseLong(data.toString().trim())));
                    } else if (qName.equalsIgnoreCase("Tariffing")) {
                        parameters.setTariffing(data.toString().trim());
                    } else if (qName.equalsIgnoreCase("ConnectionFee")) {
                        PayrollType connectionFee = new PayrollType();
                        connectionFee.setValue(new BigDecimal(data.toString().trim()));
                        parameters.setConnectionFee(connectionFee);
                    } else if (qName.equalsIgnoreCase("CallPrices")) {
                        tariff.setCallPrices(callPrices);
                    } else if (qName.equalsIgnoreCase("Parameters")) {
                        tariff.setParameters(parameters);
                    } else if (qName.equalsIgnoreCase("Tariff")) {
                        tariffs.add(tariff);
                    }
                    data.setLength(0); // Clear StringBuilder after processing each element
                } else if (event.isCharacters()) {
                    data.append(event.asCharacters().getData());
                }
            }
            logger.info("StAX parsing of tariffs completed successfully.");
        } catch (Exception e) {
            logger.error("StAX parsing failed: {}", e.getMessage());
        }
        TariffTypeComparator tariffTypeComparator = new TariffTypeComparator();
        tariffs.sort(tariffTypeComparator);
        return tariffs;
    }
}
