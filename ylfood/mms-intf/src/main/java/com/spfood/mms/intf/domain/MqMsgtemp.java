package com.spfood.mms.intf.domain;

import java.util.Date;

import com.spfood.kernel.domain.DomainObject;

public class MqMsgtemp implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_commodity_msg_temp.commdity_code
     *
     * @mbggenerated
     */
    private String typeCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mms_commodity_msg_temp.pws_send_date
     *
     * @mbggenerated
     */
    private Date SendDate;
    
    private int type;


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mms_commodity_msg_temp.pws_send_date
     *
     * @return the value of mms_commodity_msg_temp.pws_send_date
     *
     * @mbggenerated
     */
    public Date getPwsSendDate() {
        return SendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mms_commodity_msg_temp.pws_send_date
     *
     * @param SendDate the value for mms_commodity_msg_temp.pws_send_date
     *
     * @mbggenerated
     */
    public void setPwsSendDate(Date SendDate) {
        this.SendDate = SendDate;
    }

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the typeCode
	 */
	public String getTypeCode() {
		return typeCode;
	}

	/**
	 * @param typeCode the typeCode to set
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

}