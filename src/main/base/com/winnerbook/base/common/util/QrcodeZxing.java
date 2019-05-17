package com.winnerbook.base.common.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QrcodeZxing {
	
	private static final String CHARSET = "utf-8";
	
	private static final String FORMAT_NAME = "png";    
    // 二维码尺寸    
    private static final int QRCODE_SIZE = 300;    


	public static void getQrcodeImg(String content,String dirPath){
		String imgPath = QrcodeZxing.class.getClassLoader().getResource("logo.png").getPath();//获取文件路径
		try {
			encodeOfPath(content, imgPath, dirPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void encodeOfPath(String content, String imgPath,String dirPath) throws Exception {
        BufferedImage image = createImage(content, CheckImageExist(imgPath));    
        mkdirs(dirPath);    
        ImageIO.write(image, FORMAT_NAME, new File(dirPath));    
    }    
	
	 private static BufferedImage createImage(String content,BufferedImage logoImage) throws Exception {    
         Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
         hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); //容错级别 H->30%    
         hints.put(EncodeHintType.CHARACTER_SET, CHARSET);    
         hints.put(EncodeHintType.MARGIN, 1);
         BitMatrix bitMatrix = new MultiFormatWriter().encode(content,    
                 BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);    
         int width = bitMatrix.getWidth();    
         int height = bitMatrix.getHeight();   
         int tempHeight = height;  
        
         BufferedImage image = new BufferedImage(width, tempHeight,    
                 BufferedImage.TYPE_INT_RGB);   
         for (int x = 0; x < width; x++) {    
             for (int y = 0; y < height; y++) {    
                 image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000    
                         : 0xFFFFFFFF);    
             }    
         }    

         if(null==logoImage)    
              return image;    

         // 插入图片    
         insertImage(image, logoImage);
         
         return image;    
     }    

	
	//插入图片
	private static void insertImage(BufferedImage source,BufferedImage logoImage) throws Exception {
        int width = logoImage.getWidth(null) > source.getWidth() * 2 / 10 ? (source.getWidth() * 2 / 10) : logoImage.getWidth(null);
        int height = logoImage.getHeight(null) > source.getHeight() * 2 / 10 ? (source.getHeight() * 2 / 10) : logoImage.getWidth(null);

        Image src = logoImage;    
        Graphics2D graph = source.createGraphics();    
        int x = (QRCODE_SIZE - width) / 2;    
        int y = (QRCODE_SIZE - height) / 2;    
        graph.drawImage(src, x, y, width, height, null);     
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);    
        graph.setStroke(new BasicStroke(3f));    
        graph.draw(shape);    
        graph.dispose();    
    }   
	
	 private static BufferedImage CheckImageExist(String imgPath) throws IOException{    
         File file = new File(imgPath);    
         if (!file.exists()) {    
             System.err.println(""+imgPath+"   该文件不存在！");    
             return null;    
         }    
         BufferedImage src = ImageIO.read(new File(imgPath));    
         return src;    
     }    
	 
	 public static void mkdirs(String destPath) {    
         File file =new File(destPath);       
         //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)    
         if (!file.exists() && !file.isDirectory()) {    
             file.mkdirs();    
         }    
     }    

	 
	 
	 public static void main(String[] args) {
		
	}
	
}
