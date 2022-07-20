package cl.bci.api.rest.utils;

import java.util.regex.Pattern;

public class General {
	
	private General() {
		throw new IllegalStateException("Utility class");
	}
	public static String emailRegex = "^(.+)@(.+)$";
	 
	public static Pattern emailPattern = Pattern.compile(emailRegex);
	
	public static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
	 
	public static Pattern passwordPattern = Pattern.compile(passwordRegex);
}
