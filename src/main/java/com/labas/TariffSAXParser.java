package com.labas;

import generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TariffSAXParser extends DefaultHandler {

    private static final Logger logger = LoggerFactory.getLogger(TariffSAXParser.class);
    private List<TariffType> tariffs = new ArrayList<>();
    private TariffType tariff;
    private PayrollType payroll;
    private CallPricesType callPrices;
    private ParametersType parameters;
    private PayrollType currentPrice;
    private StringBuilder data;

    public List<TariffType> parseXML(String xmlFilePath) {
        try {
            logger.info("Starting SAX parsing of tariffs.");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            javax.xml.parsers.SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(xmlFilePath), this);
            logger.info("SAX parsing of tariffs completed successfully.");
        } catch (Exception e) {
            logger.error("SAX parsing error: {}", e.getMessage());
        }
        TariffTypeComparator tariffTypeComparator = new TariffTypeComparator();
        tariffs.sort(tariffTypeComparator);
        return tariffs;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();

        switch (qName) {
            case "Tariff":
                tariff = new TariffType();
                break;
            case "Payroll":
            case "InsideNetwork":
            case "OutsideNetwork":
            case "Landline":
            case "SMSPrice":
            case "ConnectionFee":
                currentPrice = new PayrollType();
                currentPrice.setCurrency(attributes.getValue("currency") != null ? attributes.getValue("currency") : "UAH");
                break;
            case "CallPrices":
                callPrices = new CallPricesType();
                break;
            case "Parameters":
                parameters = new ParametersType();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "Name":
                tariff.setName(data.toString().trim());
                break;
            case "OperatorName":
                tariff.setOperatorName(data.toString().trim());
                break;
            case "Payroll":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                tariff.setPayroll(currentPrice);
                break;
            case "InsideNetwork":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                callPrices.setInsideNetwork(currentPrice);
                break;
            case "OutsideNetwork":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                callPrices.setOutsideNetwork(currentPrice);
                break;
            case "Landline":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                callPrices.setLandline(currentPrice);
                break;
            case "SMSPrice":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                tariff.setSMSPrice(currentPrice);
                break;
            case "CallPrices":
                tariff.setCallPrices(callPrices);
                break;
            case "FavoriteNumber":
                parameters.setFavoriteNumber(BigInteger.valueOf(Long.parseLong(data.toString().trim())));
                break;
            case "Tariffing":
                parameters.setTariffing(data.toString().trim().replace("-", "_"));
                break;
            case "ConnectionFee":
                currentPrice.setValue(new BigDecimal(data.toString().trim()));
                parameters.setConnectionFee(currentPrice);
                break;
            case "Parameters":
                tariff.setParameters(parameters);
                break;
            case "Tariff":
                tariffs.add(tariff);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}
