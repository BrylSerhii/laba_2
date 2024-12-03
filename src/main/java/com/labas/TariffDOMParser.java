package com.labas;

import generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TariffDOMParser {
    private static final Logger logger = LoggerFactory.getLogger(TariffDOMParser.class);
    private List<TariffType> tariffs = new ArrayList<>();

    public List<TariffType> parseXML(String xmlFilePath) {
        try {
            logger.info("Starting DOM parsing of tariffs.");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();

            NodeList tariffList = document.getElementsByTagName("Tariff");
            for (int i = 0; i < tariffList.getLength(); i++) {
                Element tariffElement = (Element) tariffList.item(i);
                TariffType tariff = new TariffType();

                tariff.setName(tariffElement.getElementsByTagName("Name").item(0).getTextContent().trim());
                tariff.setOperatorName(tariffElement.getElementsByTagName("OperatorName").item(0).getTextContent().trim());

                // Get Payroll
                Element payrollElement = (Element) tariffElement.getElementsByTagName("Payroll").item(0);
                PayrollType payroll = new PayrollType();
                payroll.setValue(new BigDecimal(payrollElement.getTextContent().trim()));
                payroll.setCurrency(payrollElement.getAttribute("currency").isEmpty() ? "UAH" : payrollElement.getAttribute("currency"));
                tariff.setPayroll(payroll);

                // Get CallPrices
                Element callPricesElement = (Element) tariffElement.getElementsByTagName("CallPrices").item(0);
                CallPricesType callPrices = new CallPricesType();

                if (callPricesElement.getElementsByTagName("InsideNetwork").getLength() > 0) {
                    Element insideNetwork = (Element) callPricesElement.getElementsByTagName("InsideNetwork").item(0);
                    PayrollType insideNetworkPrice = new PayrollType();
                    insideNetworkPrice.setValue(new BigDecimal(insideNetwork.getTextContent().trim()));
                    callPrices.setInsideNetwork(insideNetworkPrice);
                }

                if (callPricesElement.getElementsByTagName("OutsideNetwork").getLength() > 0) {
                    Element outsideNetwork = (Element) callPricesElement.getElementsByTagName("OutsideNetwork").item(0);
                    PayrollType outsideNetworkPrice = new PayrollType();
                    outsideNetworkPrice.setValue(new BigDecimal(outsideNetwork.getTextContent().trim()));
                    callPrices.setOutsideNetwork(outsideNetworkPrice);
                }

                if (callPricesElement.getElementsByTagName("Landline").getLength() > 0) {
                    Element landline = (Element) callPricesElement.getElementsByTagName("Landline").item(0);
                    PayrollType landlinePrice = new PayrollType();
                    landlinePrice.setValue(new BigDecimal(landline.getTextContent().trim()));
                    callPrices.setLandline(landlinePrice);
                }
                tariff.setCallPrices(callPrices);

                // Get SMSPrice
                Element smsPriceElement = (Element) tariffElement.getElementsByTagName("SMSPrice").item(0);
                PayrollType smsPrice = new PayrollType();
                smsPrice.setValue(new BigDecimal(smsPriceElement.getTextContent().trim()));
                tariff.setSMSPrice(smsPrice);

                // Get Parameters
                Element parametersElement = (Element) tariffElement.getElementsByTagName("Parameters").item(0);
                ParametersType parameters = new ParametersType();

                if (parametersElement.getElementsByTagName("FavoriteNumber").getLength() > 0) {
                    parameters.setFavoriteNumber(BigInteger.valueOf(Long.parseLong(parametersElement.getElementsByTagName("FavoriteNumber").item(0).getTextContent().trim())));
                }

                Element tariffingElement = (Element) parametersElement.getElementsByTagName("Tariffing").item(0);
                parameters.setTariffing(tariffingElement.getTextContent().trim().replace("-", "_"));

                Element connectionFeeElement = (Element) parametersElement.getElementsByTagName("ConnectionFee").item(0);
                PayrollType connectionFee = new PayrollType();
                connectionFee.setValue(new BigDecimal(connectionFeeElement.getTextContent().trim()));
                parameters.setConnectionFee(connectionFee);

                tariff.setParameters(parameters);
                TariffTypeComparator tariffTypeComparator = new TariffTypeComparator();
                tariffs.sort(tariffTypeComparator);
                tariffs.add(tariff);
            }
            logger.info("DOM parsing of tariffs completed successfully.");
        } catch (Exception e) {
            logger.error("DOM parsing error: {}", e.getMessage());

        }
        return tariffs;
    }
}
