//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b52-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.12 at 09:14:14 ���� KST 
//


package stw.eai.dhl.xml.datatypes;

import javax.xml.bind.annotation.XmlEnum;


/**
 * <p>Java class for CurrencyRoleTypeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CurrencyRoleTypeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;maxLength value="5"/>
 *     &lt;enumeration value="BILLCU"/>
 *     &lt;enumeration value="PULCL"/>
 *     &lt;enumeration value="INVCU"/>
 *     &lt;enumeration value="BASEC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlEnum
public enum CurrencyRoleTypeCode {

    BASEC,
    BILLCU,
    INVCU,
    PULCL;

    public String value() {
        return name();
    }

    public static CurrencyRoleTypeCode fromValue(String v) {
        return valueOf(v);
    }

}
