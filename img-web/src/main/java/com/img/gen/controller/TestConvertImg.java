package com.img.gen.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 图片表情包测试类
 */
public class TestConvertImg {

    /**
     *
     * @return
     */
    private static Integer testgeneratorImg() throws Exception{
        String src = "http://7xweel.com1.z0.glb.clouddn.com/F%60%29%609%7D40ML6X$NJ%7DC%5D%7DPVAR.jpg";
        String x = "0";
        String y = "0";
        String text = "李四表示不服气";

        String fileAddr = "F:\\workspace\\idea_workspace1\\sb-img_web\\img-web\\src\\main\\webapp\\temp\\img";
        String fileName = "testImg.jpg";
        String targetName = "targetImg.jpg";
        //下载图片
        if (downloadImg(src,fileAddr,fileName) > 0){
            convertImg((fileAddr +File.separator+ fileName) , text, Integer.valueOf(x),Integer.valueOf(y),(fileAddr +File.separator+ targetName));
        }


        return null;
    }

    /**
     * 下载图片
     * @param imgUrl
     * @param fileAddr
     * @param imgName
     * @return
     * @throws Exception
     */
    public static Integer downloadImg(String imgUrl, String fileAddr, String imgName) throws Exception {
        HttpClient client = new HttpClient();
        HttpMethod get = new GetMethod(imgUrl);
        client.executeMethod(get);
        File storeFile = new File(fileAddr);
        if (!storeFile.exists())
            storeFile.mkdirs();
        storeFile = new File(fileAddr + "\\" + imgName);
        FileOutputStream output = new FileOutputStream(storeFile);
        //得到网络资源的字节数组,并写入文件
        output.write(get.getResponseBody());
        output.close();
        return 1;
    }

    /**
     * 转换图片，生成表情包
     * @return
     * @param  sourceFile 源位置
     * @param  targetFile 目标位置
     * @param text 文字
     * @param x 文字的x位置
     * @param y 文字的y位置
     * @throws Exception
     */
    public static Integer convertImg(String sourceFile,String text,Integer x,Integer y,String targetFile) throws  Exception{

        File tFile = new File(targetFile);//目标文件
        File sFile = new File(sourceFile);//源文件
        BufferedImage source_img = ImageIO.read(sFile);
        BufferedImage tagert_Img = new BufferedImage(source_img.getWidth(),source_img.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = tagert_Img.createGraphics();
        g2.drawImage(source_img,0,0,source_img.getWidth(),source_img.getHeight(),null);//拷贝图片
        Font font = new Font("宋体", Font.PLAIN, 12);
        g2.setFont(font);
        g2.setColor(Color.red);//红色
        g2.drawString(text,x,y+font.getSize());//写字   *y+font.getSize()*表示字体的起始位置
        ImageIO.write(tagert_Img,"jpg",tFile);

        return 1;
    }


    public static void main(String[] args) throws  Exception{
        testgeneratorImg();
    }
}
