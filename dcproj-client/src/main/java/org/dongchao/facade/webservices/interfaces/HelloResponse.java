
package org.dongchao.facade.webservices.interfaces;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>helloResponse complex type�� Java �ࡣ
 * <p>
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;complexType name="helloResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "helloResponse", propOrder = {
        "_return"
})
public class HelloResponse {

    @XmlElement(name = "return")
    protected String _return;

    /**
     * ��ȡreturn���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getReturn() {
        return _return;
    }

    /**
     * ����return���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReturn(String value) {
        this._return = value;
    }

}
