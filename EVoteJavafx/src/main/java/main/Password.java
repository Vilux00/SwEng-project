package main;

import java.security.SecureRandom;
import org.apache.commons.lang3.RandomStringUtils;

public class Password {
	public static String generaPasswordRandom() {
		char[] caratteriUsabili = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&*-_=+?")).toCharArray();
		String pwdRandom = RandomStringUtils.random( 10, 0, caratteriUsabili.length-1, false, false, caratteriUsabili, new SecureRandom() );
		return pwdRandom;
	}
	
	public static boolean controlloPassword(String pwd) {
		if(pwd.length() > 8 && pwd.replaceAll("A-Za-z0-9", "").length() > 0 && pwd.replaceAll("A-Za-z/[^\\p{L}\\d\\s@#]/u", "").length() > 0) return true;
		return false;
	}
}
