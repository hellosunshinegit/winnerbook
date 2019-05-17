package com.winnerbook.base.common.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.coobird.thumbnailator.Thumbnails;

/***
 * 图片压缩
 * @author hxs  2019-04-19
 *
 */
public class ThumbnailatorUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(ThumbnailatorUtil.class);

	public static void generateFixedSizeImage(String localPath){
		logger.info("压缩图片路径："+localPath);
		String minLocalPath = "";
		if(localPath.lastIndexOf(".")>=0){
			minLocalPath = localPath.substring(0,localPath.indexOf("."))+"_min"+localPath.substring(localPath.lastIndexOf("."), localPath.length());
		}
        try {
            Thumbnails.of(localPath).size(200,300).toFile(minLocalPath);
            logger.info("图片压缩成功："+minLocalPath);
        } catch (IOException e) {
            System.out.println("压缩失败原因: " + e.getMessage());
        }
    }
}
