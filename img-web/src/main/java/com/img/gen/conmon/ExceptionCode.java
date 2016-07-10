package com.img.gen.conmon;

/**
 * 异常类编码 128099
 * @author lzw
 *
 */
public enum ExceptionCode {

	SQL_UPDATE_ERROR("128099", "数据库更新操作失败，更新条数为0")
	;
	
	private String code;
	private String desc;

	private ExceptionCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
