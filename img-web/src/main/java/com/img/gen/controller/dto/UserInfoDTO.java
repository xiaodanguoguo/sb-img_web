package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:UserInfo
 * 
 * @author kim
 * @date 2016-6-18
 */
public class UserInfoDTO implements Serializable {
	
	private Integer	userId;		
	private String	userName;		
	private String	phoneNum;		
	private String	email;		
	private String	iconUrl;		
	private Date	createTime;		
	private Date	lastLoginTime;		
	private Boolean	status;		
	private Boolean	sex;		

	// Constructor
	public UserInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public UserInfoDTO(Integer userId, String userName, String phoneNum, String email, String iconUrl, Date createTime, Date lastLoginTime, Boolean status, Boolean sex) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.iconUrl = iconUrl;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.status = status;
		this.sex = sex;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [" + "userId=" + userId + ", userName=" + userName + ", phoneNum=" + phoneNum + ", email=" + email + ", iconUrl=" + iconUrl + ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + ", status=" + status + ", sex=" + sex +  "]";
	}
}
