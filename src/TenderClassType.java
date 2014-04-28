
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TenderClassType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TenderClassType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CreditCard"/>
 *     &lt;enumeration value="StoredValue"/>
 *     &lt;enumeration value="BankAccount"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TenderClassType")
@XmlEnum
public enum TenderClassType {

    @XmlEnumValue("CreditCard")CREDIT_CARD("CreditCard"),
    @XmlEnumValue("StoredValue")STORED_VALUE("StoredValue"),
    @XmlEnumValue("BankAccount")BANK_ACCOUNT("BankAccount");
    //@XmlEnumValue("PrivateLabelCreditCard")PRIVATE_LABEL_CREDIT_CARD("PrivateLabelCreditCard");
    private final String value;

    TenderClassType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TenderClassType fromValue(String v) {
        for (TenderClassType c: TenderClassType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
