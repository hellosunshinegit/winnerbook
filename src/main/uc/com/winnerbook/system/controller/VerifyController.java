package com.winnerbook.system.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(value="/verifyController")
public class VerifyController {
	
	private char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };
	
	@RequestMapping(value="/verifyCode.do")
	public void verifyCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		int width=60;
		int height=25;
		BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// 取随机产生的认证码
		String strEnsure = "";
		// 4代表4位验证码,如果要生成更多位的认证码,则加大数值
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[(int) (mapTable.length * Math.random())];
		}
		// 　　将认证码显示到图像中,如果要生成更多位的认证码,增加drawString语句
		g.setColor(Color.black);
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 20));
		String str = strEnsure.substring(0, 1);
		g.drawString(str, 8, 17);
		str = strEnsure.substring(1, 2);
		g.drawString(str, 20, 15);
		str = strEnsure.substring(2, 3);
		g.drawString(str, 35, 18);
		str = strEnsure.substring(3, 4);
		g.drawString(str, 45, 15);
		// 随机产生10个干扰点
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		// 释放图形上下文
		g.dispose();
		
		 // 将四位数字的验证码保存到Session中。         
        HttpSession session = request.getSession();         
        session.setAttribute("validateCode", strEnsure);   
        System.out.println("验证码："+strEnsure);
        // 禁止图像缓存。         
        response.setHeader("Pragma", "no-cache");         
        response.setHeader("Cache-Control", "no-cache");         
        response.setDateHeader("Expires", 0);         
        response.setContentType("image/jpeg");         
        // 将图像输出到Servlet输出流中。         
        ServletOutputStream sos = response.getOutputStream();         
        ImageIO.write(image, "jpeg", sos);
        sos.close();         
	}
}
