package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:UserCollection
 * 
 * @author kim
 * @date 2016-6-18
 */
public class UserCollectionDTO implements Serializable {
	
	private String	collectId;		
	private Integer	userId;		
	private String	imgId;		
	private Date	collectTime;		

	// Constructor
	public UserCollectionDTO() {
	}

	/**
	 * full Constructor
	 */
	public UserCollectionDTO(String collectId, Integer userId, String imgId, Date collectTime) {
		this.collectId = collectId;
		this.userId = userId;
		this.imgId = imgId;
		this.collectTime = collectTime;
	}

	public String getCollectId() {
		return collectId;
	}

	public void setCollectId(String collectId) {
		this.collectId = collectId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	@Override
	public String toString() {
		return "UserCollectionDTO [" + "collectId=" + collectId + ", userId=" + userId + ", imgId=" + imgId + ", collectTime=" + collectTime +  "]";
	}
}
