package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:Joke
 * 
 * @author kim
 * @date 2016-6-18
 */
public class JokeDTO implements Serializable {
	
	private String	jokeId;		
	private Long	userId;		
	private String	jokeName;		
	private String	content;		
	private Date	createTime;		
	private Integer likeCnt;
	
	// Constructor
	public JokeDTO() {
	}

	/**
	 * full Constructor
	 */
	public JokeDTO(String jokeId, Long userId, String jokeName, String content, Date createTime) {
		this.jokeId = jokeId;
		this.userId = userId;
		this.jokeName = jokeName;
		this.content = content;
		this.createTime = createTime;
	}

	public Integer getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(Integer likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getJokeId() {
		return jokeId;
	}

	public void setJokeId(String jokeId) {
		this.jokeId = jokeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getJokeName() {
		return jokeName;
	}

	public void setJokeName(String jokeName) {
		this.jokeName = jokeName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "JokeDTO [" + "jokeId=" + jokeId + ", userId=" + userId + ", jokeName=" + jokeName + ", content=" + content + ", createTime=" + createTime +  "]";
	}
}
