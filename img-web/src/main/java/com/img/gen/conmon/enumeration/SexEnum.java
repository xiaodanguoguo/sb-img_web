package com.img.gen.conmon.enumeration;

public enum SexEnum {
	MAN("1", "男"),
	WOMAN("2", "女");
	
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

	private SexEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	public static SexEnum getSexEnum(String code) {
		for (SexEnum status : SexEnum.values())
			if (status.getCode().equals(code)) 
				return status;
		return null;
	}
}
