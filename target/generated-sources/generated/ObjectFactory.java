//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.08 at 10:38:12 PM EET 
//


package generated;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Tariffs_QNAME = new QName("", "Tariffs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TariffsType }
     * 
     */
    public TariffsType createTariffsType() {
        return new TariffsType();
    }

    /**
     * Create an instance of {@link TariffType }
     * 
     */
    public TariffType createTariffType() {
        return new TariffType();
    }

    /**
     * Create an instance of {@link PayrollType }
     * 
     */
    public PayrollType createPayrollType() {
        return new PayrollType();
    }

    /**
     * Create an instance of {@link CallPricesType }
     * 
     */
    public CallPricesType createCallPricesType() {
        return new CallPricesType();
    }

    /**
     * Create an instance of {@link ParametersType }
     * 
     */
    public ParametersType createParametersType() {
        return new ParametersType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TariffsType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TariffsType }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "Tariffs")
    public JAXBElement<TariffsType> createTariffs(TariffsType value) {
        return new JAXBElement<TariffsType>(_Tariffs_QNAME, TariffsType.class, null, value);
    }

}
