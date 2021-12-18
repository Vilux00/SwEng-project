package model;

import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

public class Password {
	public static String generateRandomPassword() {
		char[] usableCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*-_=+?")).toCharArray();
		String randomPassword = RandomStringUtils.random(10, 0, usableCharacters.length-1, false, false, usableCharacters, new SecureRandom());
		return randomPassword;
	}
	
	public static boolean checkPassword(String pwd) {
		if(pwd.length() > 8 && pwd.replaceAll("A-Za-z0-9", "").length() > 0 && pwd.replaceAll("A-Za-z/[^\\p{L}\\d\\s@#]/u", "").length() > 0) return true;
		return false;
	}
	//
}
