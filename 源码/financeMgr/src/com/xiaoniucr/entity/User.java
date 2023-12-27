package com.xiaoniucr.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 */
public class User implements Serializable{
	
	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * 账号
	 */
	private String username;
	
	
	/**
	 * 密码
	 */
	private String password;
	
	
	/**
	 * 姓名
	 */
	private String nickname;
	
		
	/**
	 * 性别: 0男，1女
	 */
	private Integer sex;
	
	
	/**
	 * 生日
	 */
	private String birthday;

	
	/**
	 * 电话
	 */
	private String telephone;
	
	
	/**
	 * 邮箱
	 */
	private String email;
	
	
	/**
	 * 职业
	 */
	private String profession;

	
	/**
	 * 添加时间
	 */
	private Date createTime;
	
	
	/**
	 * 修改时间
	 */
	private Date updateTime;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getProfession() {
		return profession;
	}


	public void setProfession(String profession) {
		this.profession = profession;
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nickname;
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == null){
			return false;
		}
		User user = (User) obj;
		if(this.id == user.getId() && this.nickname.equals(user.getNickname())){
			return true;
		}
		return false;
	}
	
	

}
