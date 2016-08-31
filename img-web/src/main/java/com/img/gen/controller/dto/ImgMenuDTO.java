package com.img.gen.controller.dto;

import java.io.Serializable;
import java.util.Date;


/**
 * DTO:ImgMenu
 * 
 * @author king
 * @date 2016-8-31
 */
public class ImgMenuDTO implements Serializable {
	
	private Integer id;          /***主键****/
	private String	name;		 /* 栏目名称 */ 
	private String	state;		 /* 状态1启用0禁用 */ 
	private Date	createdate;		 /* 创建时间 */ 

	// Constructor
	public ImgMenuDTO() {
	}

	public ImgMenuDTO(Integer id, String name, String state, Date createdate) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.createdate = createdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	@Override
	public String toString() {
		return "ImgMenuDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", state='" + state + '\'' +
				", createdate=" + createdate +
				'}';
	}
}
