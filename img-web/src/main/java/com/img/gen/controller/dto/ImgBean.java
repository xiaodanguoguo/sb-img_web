package com.img.gen.controller.dto;

/**
 * Created by jinhangzhan on 2016/7/31.
 */
public class ImgBean {
    private String url;
    private Integer size;

    public ImgBean() {}

    public ImgBean(String url, Integer size) {
        this.url = url;
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
