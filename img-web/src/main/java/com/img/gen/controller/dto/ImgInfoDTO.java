package com.img.gen.controller.dto;

/**
 * Created by Zhengpeng on 2016/8/6.
 */
public class ImgInfoDTO {
    private Integer length;//大小
    private String url;//路径

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
