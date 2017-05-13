package com.oauth2.tool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenProcessor {
	 static public String makeToken(String str) {
		 try {
			 byte s[] = str.getBytes();
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 md.update(s);
			 String token = toHex(md.digest());
			 return token;
		 } catch (NoSuchAlgorithmException e) {
			 return null;
		 }
	 }
	 /**
	  * 将一个字节数转换成十六进制得字符串
	  */
	 static public String toHex(byte buffer[]) {
	  StringBuffer sb = new StringBuffer(buffer.length * 2);
	  for (int i = 0; i < buffer.length; i++) {
	   sb.append(Character.forDigit((buffer[i] & 0x60) >> 4, 16));
	   sb.append(Character.forDigit(buffer[i] & 0x0f, 16));
	  }
	  return sb.toString();
	 }
	}
