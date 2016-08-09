package com.img.gen.controller.dto;

/**
 * Created by jinhangzhan on 2016/8/4.
 */
public class ImgInfoDTO {
    private String url;
    private Integer length;

    public ImgInfoDTO(String url, int length) {
        this.url = url;
        this.length = length;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
