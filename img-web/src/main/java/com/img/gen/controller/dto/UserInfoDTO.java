package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;

import com.img.gen.conmon.enumeration.SexEnum;
import com.img.gen.conmon.enumeration.StatusEnum;


/**
 * DTO:UserInfo
 * 
 * @author kim
 * @date 2016-6-18
 */
public class UserInfoDTO implements Serializable {
	
	private Long	userId;		
	private String	userName;		
	private String	phoneNum;		
	private String	email;		
	private String	iconUrl;		
	private Date	createTime;		
	private Date	lastLoginTime;		
	private StatusEnum	status;		
	private SexEnum	sex;		
	private String password;
	
	// Constructor
	public UserInfoDTO() {
	}

	/**
	 * full Constructor
	 */
	public UserInfoDTO(Long userId, String userName, String phoneNum, String email, String iconUrl, Date createTime, Date lastLoginTime, String status, String sex) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNum = phoneNum;
		this.email = email;
		this.iconUrl = iconUrl;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.status = StatusEnum.getStatusEnum(status);
		this.sex = SexEnum.getSexEnum(sex);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public String getStatus() {
		return status.getCode();
	}

	public void setStatus(String status) {
		this.status = StatusEnum.getStatusEnum(status);
	}

	
	public String getSex() {
		return sex.getCode();
	}

	public void setSex(String sex) {
		this.sex = SexEnum.getSexEnum(sex);
	}

	@Override
	public String toString() {
		return "UserInfo [" + "userId=" + userId + ", userName=" + userName + ", phoneNum=" + phoneNum + ", email=" + email + ", iconUrl=" + iconUrl + ", createTime=" + createTime + ", lastLoginTime=" + lastLoginTime + ", status=" + status + ", sex=" + sex +  "]";
	}
}
