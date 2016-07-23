package com.img.gen.conmon;

import java.io.Serializable;

/**
 * Desc: web层JsonStr 封装类
 */
public class JsonResult<T> implements Serializable
{
	private static final long serialVersionUID = 7752202537142677838L;
	
	// 失败状态码
    public static final String ERROR = "0";
    // 成功状态码
    public static final String SUCCESS = "1";
    // 无权限
    public static final String FORBID = "-1";

    // 状态码
    private String status = JsonResult.ERROR;

    // 返回结果描述
    private String msg;

    // 返回结果
    private T results;

    public JsonResult() {
        super();
    }

    /**
     * @param status
     */
    public JsonResult(String status) {
        super();
        this.status = status;
    }

    /**
     * @param status
     */
    public JsonResult(String status,String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
    
    public static JsonResult fromJson(String jsonString) {
        return JsonMapper.nonEmptyMapper().fromJson(jsonString, JsonResult.class);
    }
    
    public boolean isSuccessful() {
		return SUCCESS.equals(getStatus());
	}
    
}
