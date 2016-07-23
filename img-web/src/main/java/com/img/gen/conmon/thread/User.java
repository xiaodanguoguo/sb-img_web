package com.img.gen.conmon.thread;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 用户登录对象
 * 暂时只放UserId
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -620593124301083782L;
	
	private Long userId;
	
	private String userName;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}
