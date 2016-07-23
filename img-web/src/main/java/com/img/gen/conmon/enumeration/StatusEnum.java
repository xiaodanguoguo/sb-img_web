package com.img.gen.conmon.enumeration;

public enum StatusEnum {
	ENABLED("1", "可用"),
	DISABLE("2", "不可用");
	
	private String code;
	private String desc;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private StatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static StatusEnum getStatusEnum(String code) {
		for (StatusEnum status : StatusEnum.values())
			if (status.getCode().equals(code)) 
				return status;
		return null;
	}
}
