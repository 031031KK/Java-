package com.xiaoniucr.entity;

import java.util.Date;

/**
 * 收支账单
 * @author Lenovo
 *
 */
public class Bill {
	
	/**
	 * 账单ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	/**
	 * 账单主题
	 */
	private String title;
	
	/**
	 * 金额
	 */
	private Double amount;
	
	/**
	 * 银行卡ID
	 */
	private Integer cardId;
	
	
	/**
	 * 账单类型：0收入，1支出
	 */
	private Integer billType;
	
	
	/**
	 * 账单时间
	 */
	private String payDate;
	
	
	/**
	 * 备注
	 */
	private String remark;
	
	
	/**
	 * 添加时间
	 */
	private Date createTime;
	
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	
	/**
	 * 冗余字段-- 卡号
	 */
	private String cardNo;
	
	
	/**
	 * 冗余字段-- 姓名
	 */
	private String nickname;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}

	
	
	public Integer getBillType() {
		return billType;
	}

	public void setBillType(Integer billType) {
		this.billType = billType;
	}

	
	
	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
