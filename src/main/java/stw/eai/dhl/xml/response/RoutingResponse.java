//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.14 at 08:51:59 ���� KST 
//


package stw.eai.dhl.xml.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import stw.eai.dhl.xml.datatypes.*;

/**
 * Routing response root element
 * 
 * <p>Java class for RoutingResponse element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="RoutingResponse">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="Response" type="{http://www.dhl.com/datatypes}Response"/>
 *           &lt;element name="GMTNegativeIndicator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="GMTOffset" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="RegionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="ServiceArea" type="{http://www.dhl.com/datatypes}ServiceArea"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "response",
    "gmtNegativeIndicator",
    "gmtOffset",
    "regionCode",
    "serviceArea"
})
@XmlRootElement(name = "RoutingResponse", namespace = "http://www.dhl.com")
public class RoutingResponse {

    @XmlElement(name = "Response", required = true)
    protected Response response;
    @XmlElement(name = "GMTNegativeIndicator", required = true)
    protected String gmtNegativeIndicator;
    @XmlElement(name = "GMTOffset", required = true)
    protected String gmtOffset;
    @XmlElement(name = "RegionCode", required = true)
    protected String regionCode;
    @XmlElement(name = "ServiceArea", required = true)
    protected ServiceArea serviceArea;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link Response }
     *     
     */
    public Response getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link Response }
     *     
     */
    public void setResponse(Response value) {
        this.response = value;
    }

    /**
     * Gets the value of the gmtNegativeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMTNegativeIndicator() {
        return gmtNegativeIndicator;
    }

    /**
     * Sets the value of the gmtNegativeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMTNegativeIndicator(String value) {
        this.gmtNegativeIndicator = value;
    }

    /**
     * Gets the value of the gmtOffset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGMTOffset() {
        return gmtOffset;
    }

    /**
     * Sets the value of the gmtOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGMTOffset(String value) {
        this.gmtOffset = value;
    }

    /**
     * Gets the value of the regionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * Sets the value of the regionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegionCode(String value) {
        this.regionCode = value;
    }

    /**
     * Gets the value of the serviceArea property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceArea }
     *     
     */
    public ServiceArea getServiceArea() {
        return serviceArea;
    }

    /**
     * Sets the value of the serviceArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceArea }
     *     
     */
    public void setServiceArea(ServiceArea value) {
        this.serviceArea = value;
    }

}
