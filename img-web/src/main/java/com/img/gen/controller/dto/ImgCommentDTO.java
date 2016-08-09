package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:ImgComment
 * 
 * @author kim
 * @date 2016-6-18
 */
public class ImgCommentDTO implements Serializable {
	
	private String	commentId;		
	private String	commentContent;		
	private Long	userId;
	private Date	createTime;		
	private Long	imgId;
	private Boolean	isLogin;		

	// Constructor
	public ImgCommentDTO() {
	}

	/**
	 * full Constructor
	 */
	public ImgCommentDTO(String commentId, String commentContent, Long userId, Date createTime, Long imgId, Boolean isLogin) {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getImgId() {
		return imgId;
	}

	public void setImgId(Long imgId) {
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
		return "ImgCommentDTO [" + "commentId=" + commentId + ", commentContent=" + commentContent + ", userId=" + userId + ", createTime=" + createTime + ", imgId=" + imgId + ", isLogin=" + isLogin +  "]";
	}
}
