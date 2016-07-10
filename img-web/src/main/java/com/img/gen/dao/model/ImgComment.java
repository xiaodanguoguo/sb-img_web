package com.img.gen.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * entity:ImgComment
 * 
 * @author kim
 * @date 2016-6-18
 */
public class ImgComment implements Serializable {
	
	private String	commentId;		
	private String	commentContent;		
	private Integer	userId;		
	private Date	createTime;		
	private Integer	imgId;		
	private Boolean	isLogin;		

	// Constructor
	public ImgComment() {
	}

	/**
	 * full Constructor
	 */
	public ImgComment(String commentId, String commentContent, Integer userId, Date createTime, Integer imgId, Boolean isLogin) {
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.userId = userId;
		this.createTime = createTime;
		this.imgId = imgId;
		this.isLogin = isLogin;
	}

	
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	
	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}

	@Override
	public String toString() {
		return "ImgComment [" + "commentId=" + commentId + ", commentContent=" + commentContent + ", userId=" + userId + ", createTime=" + createTime + ", imgId=" + imgId + ", isLogin=" + isLogin +  "]";
	}
}
