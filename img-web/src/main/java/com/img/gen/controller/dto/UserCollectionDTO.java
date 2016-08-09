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
	private Long	userId;
	private Long	imgId;
	private Date	collectTime;		

	// Constructor
	public UserCollectionDTO() {
	}

	/**
	 * full Constructor
	 */
	public UserCollectionDTO(String collectId, Long userId, Long imgId, Date collectTime) {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
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
