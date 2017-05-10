package com.spfood.pms.intf.domain;

import com.spfood.kernel.domain.DomainObject;
import java.util.Date;
import java.util.List;

/**
 * 产品品类表
 * @author fengjunchao
 * 2016-12-06
 *
 */
public class ProductCategory implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.category_code
     *
     * @mbggenerated
     */
    private String categoryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.category_name
     *
     * @mbggenerated
     */
    private String categoryName;
    
    /**
     * 品类别名
     */
    private String categoryAnotherName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.parent_category_code
     *
     * @mbggenerated
     */
    private ProductCategory parentCategory;
    
    /**
     * 父类编码
     */
    private String parentCode;
    
    /**
	 * 子类集合
	 */
	private List<ProductCategory> productCategorys;
	
	/**
	 * 属性集合
	 */
	private List<ProductCategoryProperty> productCategoryPropertys;
	
	/**
	 * 商品评论
	 */
	private CommodityComment commodityComment;

    /**
	 * @return the commodityComment
	 */
	public CommodityComment getCommodityComment() {
		return commodityComment;
	}

	/**
	 * @param commodityComment the commodityComment to set
	 */
	public void setCommodityComment(CommodityComment commodityComment) {
		this.commodityComment = commodityComment;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.category_commnet
     *
     * @mbggenerated
     */
    private String categoryComment;
    
    /**
     * 是否标准产品品类 0表示否，1表示是
     */
    private int categoryTypeFlag = 0;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.last_udpate_date
     *
     * @mbggenerated
     */
    private Date lastUpdateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_product_category.last_udpate_user
     *
     * @mbggenerated
     */
    private String lastUpdateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.id
     *
     * @return the value of pms_product_category.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.id
     *
     * @param id the value for pms_product_category.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.category_code
     *
     * @return the value of pms_product_category.category_code
     *
     * @mbggenerated
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.category_code
     *
     * @param categoryCode the value for pms_product_category.category_code
     *
     * @mbggenerated
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.category_name
     *
     * @return the value of pms_product_category.category_name
     *
     * @mbggenerated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.category_name
     *
     * @param categoryName the value for pms_product_category.category_name
     *
     * @mbggenerated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.category_commnet
     *
     * @return the value of pms_product_category.category_commnet
     *
     * @mbggenerated
     */
    public ProductCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public String getCategoryAnotherName() {
		return categoryAnotherName;
	}

	public int getCategoryTypeFlag() {
		return categoryTypeFlag;
	}

	public void setCategoryTypeFlag(int categoryTypeFlag) {
		this.categoryTypeFlag = categoryTypeFlag;
	}

	public void setCategoryAnotherName(String categoryAnotherName) {
		this.categoryAnotherName = categoryAnotherName;
	}

	public List<ProductCategory> getProductCategorys() {
		return productCategorys;
	}

	public void setProductCategorys(List<ProductCategory> productCategorys) {
		this.productCategorys = productCategorys;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.category_commnet
     *
     * @param categoryCommnet the value for pms_product_category.category_commnet
     *
     * @mbggenerated
     */

    public List<ProductCategoryProperty> getProductCategoryPropertys() {
		return productCategoryPropertys;
	}

	public void setProductCategoryPropertys(
			List<ProductCategoryProperty> productCategoryPropertys) {
		this.productCategoryPropertys = productCategoryPropertys;
	}

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.create_date
     *
     * @return the value of pms_product_category.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    public String getCategoryComment() {
		return categoryComment;
	}

	public void setCategoryComment(String categoryComment) {
		this.categoryComment = categoryComment;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.create_date
     *
     * @param createDate the value for pms_product_category.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.create_user
     *
     * @return the value of pms_product_category.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_product_category.create_user
     *
     * @param createUser the value for pms_product_category.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_product_category.last_udpate_user
     *
     * @return the value of pms_product_category.last_udpate_user
     *
     * @mbggenerated
     */

    public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}