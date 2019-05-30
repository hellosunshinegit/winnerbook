package com.winnerbook.base.common.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

public class WaterMarkUtils {
	
	/**
	 * 
	 * @param srcImgPath  原图片
	 * @param busName  企业名字
	 * @param busNumber  企业编号
	 * @param busQrCodeImg  企业二维码
	 * @param dateStr  授权日期
	 * @return  访问的路径
	 */
    public static String addWaterMark(String srcImgPath,String busName,String busNumber,String busQrCodeImg,String dateStr) {
    	String result = "";//失败
        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件  
            BufferedImage srcImg = ImageIO.read(srcImgFile);//文件转化为图片 width=8268  height:5906
            System.out.println(srcImg.getWidth()+"===="+srcImg.getHeight());
            //加载二维码图片
    		//BufferedImage imageCode = ImageIO.read(new URL(busQrCodeImg));
    		BufferedImage imageCode = ImageIO.read(new URL(busQrCodeImg));
            
    		// 以原图片为模板
    		Graphics2D g = srcImg.createGraphics();
    		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
    		g.setComposite(ac);
    		g.setBackground(Color.WHITE);
    		
    		//设置公司名称
    		g.setFont(new Font("黑体", Font.BOLD, 290));
    		g.setColor(Color.BLACK);
    		g.drawString("授予："+busName, 520, 1290);//设置公司名称
    		
    		// 设置二维码  在模板上添加用户二维码(地址,左边距,上边距,图片宽度,图片高度,未知)
    		g.drawImage(imageCode, 6555, 695, 1055, 1055, null);
    		
    		// 设置编号
    		g.setFont(new Font("楷体", Font.BOLD, 240));
    		g.setColor(Color.black);
    		g.drawString("编号："+busNumber, 4800, 4870);
    		
    		// 设置日期
    		g.setFont(new Font("楷体", Font.BOLD, 240));
    		g.setColor(Color.black);
    		g.drawString(dateStr, 4800, 5200);
    		
    		String imgName = System.currentTimeMillis()+".jpg";
    	    Map<String, String> pathMap = FileUtils.getDirPath("bus_brand");
    	    String tarImgPath = pathMap.get("dirPath");//存储路径
    	    File localFile = new File(tarImgPath);
	   		  if (!localFile.exists()) {
	   			localFile.mkdirs();
	   		}
	   		tarImgPath+=imgName;
		    FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            ImageIO.write(srcImg, "jpg", outImgStream);
            String urlPath = pathMap.get("urlPath")+imgName;//访问路径
            System.out.println("图片生成成功："+urlPath);
            outImgStream.flush();
            outImgStream.close();
            result = urlPath;
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }
        return result;
    }
}
