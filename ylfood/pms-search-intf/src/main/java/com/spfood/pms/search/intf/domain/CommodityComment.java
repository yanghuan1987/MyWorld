package com.spfood.pms.search.intf.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spfood.kernel.domain.DomainObject;

public class CommodityComment implements DomainObject {
	private static final long serialVersionUID = 1L;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.comment_usercode
     *
     * @mbggenerated
     */
    private String commentUsercode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.commodity_code
     *
     * @mbggenerated
     */
    private String commodityCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.category_code
     *
     * @mbggenerated
     */
    private String categoryCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.content_content
     *
     * @mbggenerated
     */
    private String commentContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.comment_grade
     *
     * @mbggenerated
     */
    private Integer commentGrade;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.order_no
     *
     * @mbggenerated
     */
    private String orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.comment_time
     *
     * @mbggenerated
     */
    private Date commentTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.comment_status
     *
     * @mbggenerated
     */
    private Integer commentStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pms_commodity_comment.comment_type_code
     *
     * @mbggenerated
     */
    private Integer commentTypeCode;
    
    /**
     * 用户昵称 
     */
    private String nickname;
    
    /**
     * 头像URL 
     */
    private String headUrl;
    
    /**
     * 用户手机号 
     */
    private String mobilePhone;
    
    /**
     * 好评条数 
     */
    private Long goodComment;
    
    /**
     * 中评数
     */
    private Long mediumComment;
    
    /**
     * 评价总数 
     */
    private Long countComment;
    
    
    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.id
     *
     * @return the value of pms_commodity_comment.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.id
     *
     * @param id the value for pms_commodity_comment.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.comment_usercode
     *
     * @return the value of pms_commodity_comment.comment_usercode
     *
     * @mbggenerated
     */
    public String getCommentUsercode() {
        return commentUsercode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.comment_usercode
     *
     * @param commentUsercode the value for pms_commodity_comment.comment_usercode
     *
     * @mbggenerated
     */
    public void setCommentUsercode(String commentUsercode) {
        this.commentUsercode = commentUsercode == null ? null : commentUsercode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.commodity_code
     *
     * @return the value of pms_commodity_comment.commodity_code
     *
     * @mbggenerated
     */
    public String getCommodityCode() {
        return commodityCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.commodity_code
     *
     * @param commodityCode the value for pms_commodity_comment.commodity_code
     *
     * @mbggenerated
     */
    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode == null ? null : commodityCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.category_code
     *
     * @return the value of pms_commodity_comment.category_code
     *
     * @mbggenerated
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.category_code
     *
     * @param categoryCode the value for pms_commodity_comment.category_code
     *
     * @mbggenerated
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.content_content
     *
     * @return the value of pms_commodity_comment.content_content
     *
     * @mbggenerated
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.content_content
     *
     * @param contentContent the value for pms_commodity_comment.content_content
     *
     * @mbggenerated
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.comment_grade
     *
     * @return the value of pms_commodity_comment.comment_grade
     *
     * @mbggenerated
     */
    public Integer getCommentGrade() {
        return commentGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.comment_grade
     *
     * @param commentGrade the value for pms_commodity_comment.comment_grade
     *
     * @mbggenerated
     */
    public void setCommentGrade(Integer commentGrade) {
        this.commentGrade = commentGrade;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.order_no
     *
     * @return the value of pms_commodity_comment.order_no
     *
     * @mbggenerated
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.order_no
     *
     * @param orderNo the value for pms_commodity_comment.order_no
     *
     * @mbggenerated
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.comment_time
     *
     * @return the value of pms_commodity_comment.comment_time
     *
     * @mbggenerated
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.comment_time
     *
     * @param commentTime the value for pms_commodity_comment.comment_time
     *
     * @mbggenerated
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.comment_status
     *
     * @return the value of pms_commodity_comment.comment_status
     *
     * @mbggenerated
     */
    public Integer getCommentStatus() {
        return commentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.comment_status
     *
     * @param commentStatus the value for pms_commodity_comment.comment_status
     *
     * @mbggenerated
     */
    public void setCommentStatus(Integer commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pms_commodity_comment.comment_type_code
     *
     * @return the value of pms_commodity_comment.comment_type_code
     *
     * @mbggenerated
     */
    public Integer getCommentTypeCode() {
        return commentTypeCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pms_commodity_comment.comment_type_code
     *
     * @param commentTypeCode the value for pms_commodity_comment.comment_type_code
     *
     * @mbggenerated
     */
    public void setCommentTypeCode(Integer commentTypeCode) {
        this.commentTypeCode = commentTypeCode;
    }

    
	public Long getGoodComment() {
		return goodComment;
	}

	public void setGoodComment(Long goodComment) {
		this.goodComment = goodComment;
	}

	public Long getCountComment() {
		return countComment;
	}

	public void setCountComment(Long countComment) {
		this.countComment = countComment;
	}

	public Long getMediumComment() {
		return mediumComment;
	}

	public void setMediumComment(Long mediumComment) {
		this.mediumComment = mediumComment;
	}
    
    
}