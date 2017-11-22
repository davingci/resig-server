/**
 *@author huangdongxu
 *@Date Nov 21, 2017
*/

package org.davingci.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtil {
	public static String sha256Hex(String originalString) {
		String hashText = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] encodedhash = digest.digest(
					  originalString.getBytes(StandardCharsets.UTF_8));
			hashText = bytesToHex(encodedhash);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		return hashText;

	}
	
	private static String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    String hex = Integer.toHexString(0xff & hash[i]);
	    if(hex.length() == 1) hexString.append('0');
	        hexString.append(hex);
	    }
	    return hexString.toString();
	}
	
	public static void main(String[] args) {
		PrintUtil.println(DigestUtil.sha256Hex("huangdongxu"));
		PrintUtil.println(DigestUtil.sha256Hex("123456"));
		PrintUtil.println(DigestUtil.sha256Hex("admin"));
		PrintUtil.println(DigestUtil.sha256Hex("test"));
	}
}
