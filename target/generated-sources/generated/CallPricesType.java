//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.26 at 11:09:08 PM EET 
//


package generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CallPricesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallPricesType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="InsideNetwork" type="{}PayrollType" minOccurs="0"/&gt;
 *         &lt;element name="OutsideNetwork" type="{}PayrollType" minOccurs="0"/&gt;
 *         &lt;element name="Landline" type="{}PayrollType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPricesType", propOrder = {
    "insideNetwork",
    "outsideNetwork",
    "landline"
})
public class CallPricesType {

    @XmlElement(name = "InsideNetwork")
    protected PayrollType insideNetwork;
    @XmlElement(name = "OutsideNetwork")
    protected PayrollType outsideNetwork;
    @XmlElement(name = "Landline")
    protected PayrollType landline;

    /**
     * Gets the value of the insideNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link PayrollType }
     *     
     */
    public PayrollType getInsideNetwork() {
        return insideNetwork;
    }

    /**
     * Sets the value of the insideNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayrollType }
     *     
     */
    public void setInsideNetwork(PayrollType value) {
        this.insideNetwork = value;
    }

    /**
     * Gets the value of the outsideNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link PayrollType }
     *     
     */
    public PayrollType getOutsideNetwork() {
        return outsideNetwork;
    }

    /**
     * Sets the value of the outsideNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayrollType }
     *     
     */
    public void setOutsideNetwork(PayrollType value) {
        this.outsideNetwork = value;
    }

    /**
     * Gets the value of the landline property.
     * 
     * @return
     *     possible object is
     *     {@link PayrollType }
     *     
     */
    public PayrollType getLandline() {
        return landline;
    }

    /**
     * Sets the value of the landline property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayrollType }
     *     
     */
    public void setLandline(PayrollType value) {
        this.landline = value;
    }

}
