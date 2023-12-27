package com.xiaoniucr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 银行卡
 * @author Lenovo
 *
 */
public class Card implements Serializable{
	
	/**
	 * 卡ID
	 */
	private Integer id;
	
	/**
	 * 用户ID
	 */
	private Integer userId;
	
	
	/**
	 * 卡号
	 */
	private String cardNo;
	
	/**
	 * 银行
	 */
	private String bank;
	
	/**
	 * 余额
	 */
	private Double balance;
	
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
	 * 冗余字段 -- 用户姓名
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return cardNo;
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null){
			return false;
		}
		Card card = (Card) obj;
		if(this.id == card.getId() && this.cardNo.equals(card.getCardNo())){
			return true;
		}
		return false;
	}
	

}
