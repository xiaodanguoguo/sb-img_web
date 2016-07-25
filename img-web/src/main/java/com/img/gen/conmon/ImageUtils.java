package com.img.gen.conmon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Administrator on 2016/7/25 0025.
 * 图片工具类
 */
public class ImageUtils {


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


}
