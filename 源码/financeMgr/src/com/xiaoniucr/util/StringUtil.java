package com.xiaoniucr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author Lenovo
 *
 */
public class StringUtil {
	
	//数据库字段驼峰命名转换
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	private static Pattern humpPattern = Pattern.compile("[A-Z]");

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	 
	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
 
	/** 驼峰转下划线 **/
	public static String humpToLine(String str) {
		return str.replaceAll("[A-Z]", "_$0").toLowerCase();
	}
 
	
 
	/** 驼峰转下划线,效率比上面高 */
	public static String humpToLine2(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	
}
