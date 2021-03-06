package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

import java.util.Date;
import java.util.List;

public class PmsCommodityMsgTemp implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_msg_temp.commdity_code
     *
     * @mbggenerated
     */
    private String commdityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_msg_temp.pws_send_date
     *
     * @mbggenerated
     */
    private Date SendDate;
    
    private int type;

    private List<Commodity> ListMsgTemps;
    /**
	 * @return the listMsgTemps
	 */
	public List<Commodity> getListMsgTemps() {
		return ListMsgTemps;
	}

	/**
	 * @param listMsgTemps the listMsgTemps to set
	 */
	public void setListMsgTemps(List<Commodity> listMsgTemps) {
		ListMsgTemps = listMsgTemps;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_msg_temp.commdity_code
     *
     * @return the value of pms_commodity_msg_temp.commdity_code
     *
     * @mbggenerated
     */
    public String getCommdityCode() {
        return commdityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_msg_temp.commdity_code
     *
     * @param commdityCode the value for pms_commodity_msg_temp.commdity_code
     *
     * @mbggenerated
     */
    public void setCommdityCode(String commdityCode) {
        this.commdityCode = commdityCode == null ? null : commdityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_msg_temp.pws_send_date
     *
     * @return the value of pms_commodity_msg_temp.pws_send_date
     *
     * @mbggenerated
     */
    public Date getPwsSendDate() {
        return SendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_msg_temp.pws_send_date
     *
     * @param SendDate the value for pms_commodity_msg_temp.pws_send_date
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

}