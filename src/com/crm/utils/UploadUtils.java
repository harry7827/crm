package com.crm.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UploadUtils {

	/**
	 * 获得唯一文件名
	 * @param fileName
	 * @return
	 */
	public static String getUUIDFileName(String fileName){
		// aaa.txt  --- xxsekluowerjlsdjr.txt 
		int idx = fileName.lastIndexOf(".");
		// 获得文件的扩展名
		String extention = fileName.substring(idx);
		// 生成唯一的文件名：
		return UUID.randomUUID().toString().replace("-", "")+extention;
	}
	
	/**
	 * 获取日期目录分离的路径:
	 */
	public static String getPath(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String directory = dateFormat.format(new Date());
		return "/"+directory;
	}
	
/*	public static void main(String[] args) {
		// System.out.println("ea7e8c4eb01f4b7796fc8a27f944d845.txt".hashCode());
		String uuidFileName = UploadUtils.getUUIDFileName("1.jpg");
		System.out.println(uuidFileName);
		// a0f587e6ceb04f3b966a0abdb47e703e.jpg(/8/5)
		String directory = UploadUtils.getPath();
		System.out.println(directory);
	}*/
}
