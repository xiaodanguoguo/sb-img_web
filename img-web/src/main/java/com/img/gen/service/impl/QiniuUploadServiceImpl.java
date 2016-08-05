package com.img.gen.service.impl;

import java.io.File;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import com.img.gen.service.QiniuUploadService;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

/**
 * Created by Zhengpeng on 2016/7/20.
 */

/**
 * 七牛上传下载实现类
 */
@Service
public class QiniuUploadServiceImpl implements QiniuUploadService {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "IcQKkz7_KydN48NfsJSYVOFOwpt4ebz6qQsRFGqQ";
    String SECRET_KEY = "J_OcTiwawTqGRNdXcpd6DfK-5cElybswNlGn53d0";
    //要上传的空间
    String bucketname = "sb-img";
    //默认文件名称
    String defaultFileName = "defalut" + new Date().toString() + ".png";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken(){
        return auth.uploadToken(bucketname);
    }


    /**
     * 七牛云上传测试
     * @param file
     * @param fileName
     * @return
     */
    @Override
    public String upload(File file,String fileName) {
        try {
            //调用put方法上传
            Response res = uploadManager.put(file, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            JSONObject json = (JSONObject)JSONObject.parse(res.bodyString());
            return (String)json.get("key");
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;

    }


    @Override
    public String upload(byte[] bytes, String fileName) {
        try {
            //调用put方法上传
            Response res = uploadManager.put(bytes, fileName, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
            JSONObject json = (JSONObject)JSONObject.parse(res.bodyString());
            return (String)json.get("key");
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }

    @Override
    public String upload(File file) {
        return null;
    }
}
