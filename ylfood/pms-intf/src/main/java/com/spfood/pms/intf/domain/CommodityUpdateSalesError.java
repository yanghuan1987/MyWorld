package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;
import java.util.Date;

public class CommodityUpdateSalesError implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_update_sales_error.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_update_sales_error.error_date
     *
     * @mbggenerated
     */
    private Date errorDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_update_sales_error.info
     *
     * @mbggenerated
     */
    private String info;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_update_sales_error.error_status
     *
     * @mbggenerated
     */
    private Integer errorStatus;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_update_sales_error.id
     *
     * @return the value of pms_commodity_update_sales_error.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_update_sales_error.id
     *
     * @param id the value for pms_commodity_update_sales_error.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_update_sales_error.error_date
     *
     * @return the value of pms_commodity_update_sales_error.error_date
     *
     * @mbggenerated
     */
    public Date getErrorDate() {
        return errorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_update_sales_error.error_date
     *
     * @param errorDate the value for pms_commodity_update_sales_error.error_date
     *
     * @mbggenerated
     */
    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_update_sales_error.info
     *
     * @return the value of pms_commodity_update_sales_error.info
     *
     * @mbggenerated
     */
    public String getInfo() {
        return info;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_update_sales_error.info
     *
     * @param info the value for pms_commodity_update_sales_error.info
     *
     * @mbggenerated
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_update_sales_error.error_status
     *
     * @return the value of pms_commodity_update_sales_error.error_status
     *
     * @mbggenerated
     */
    public Integer getErrorStatus() {
        return errorStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_update_sales_error.error_status
     *
     * @param errorStatus the value for pms_commodity_update_sales_error.error_status
     *
     * @mbggenerated
     */
    public void setErrorStatus(Integer errorStatus) {
        this.errorStatus = errorStatus;
    }
}