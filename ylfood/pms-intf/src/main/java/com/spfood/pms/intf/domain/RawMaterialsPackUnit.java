package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;

public class RawMaterialsPackUnit implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_raw_materials_pack_unit.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_raw_materials_pack_unit.raw_materials_code
     *
     * @mbggenerated
     */
    private String rawMaterialsCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_raw_materials_pack_unit.unit_code
     *
     * @mbggenerated
     */
    private String unitCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_raw_materials_pack_unit.unit_name
     *
     * @mbggenerated
     */
    private String unitName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_raw_materials_pack_unit.unit_value
     *
     * @mbggenerated
     */
    private Integer unitValue;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_raw_materials_pack_unit.id
     *
     * @return the value of pms_raw_materials_pack_unit.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_raw_materials_pack_unit.id
     *
     * @param id the value for pms_raw_materials_pack_unit.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_raw_materials_pack_unit.raw_materials_code
     *
     * @return the value of pms_raw_materials_pack_unit.raw_materials_code
     *
     * @mbggenerated
     */
    public String getRawMaterialsCode() {
        return rawMaterialsCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_raw_materials_pack_unit.raw_materials_code
     *
     * @param rawMaterialsCode the value for pms_raw_materials_pack_unit.raw_materials_code
     *
     * @mbggenerated
     */
    public void setRawMaterialsCode(String rawMaterialsCode) {
        this.rawMaterialsCode = rawMaterialsCode == null ? null : rawMaterialsCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_raw_materials_pack_unit.unit_code
     *
     * @return the value of pms_raw_materials_pack_unit.unit_code
     *
     * @mbggenerated
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_raw_materials_pack_unit.unit_code
     *
     * @param unitCode the value for pms_raw_materials_pack_unit.unit_code
     *
     * @mbggenerated
     */
    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode == null ? null : unitCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_raw_materials_pack_unit.unit_name
     *
     * @return the value of pms_raw_materials_pack_unit.unit_name
     *
     * @mbggenerated
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_raw_materials_pack_unit.unit_name
     *
     * @param unitName the value for pms_raw_materials_pack_unit.unit_name
     *
     * @mbggenerated
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_raw_materials_pack_unit.unit_value
     *
     * @return the value of pms_raw_materials_pack_unit.unit_value
     *
     * @mbggenerated
     */
    public Integer getUnitValue() {
        return unitValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_raw_materials_pack_unit.unit_value
     *
     * @param unitValue the value for pms_raw_materials_pack_unit.unit_value
     *
     * @mbggenerated
     */
    public void setUnitValue(Integer unitValue) {
        this.unitValue = unitValue;
    }
}