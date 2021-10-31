package system.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

	// REGX for validating the strings
	private static final String NAME_REGX = "[a-zA-Z]{0,20}";
	private static final String EMAIL_REGX = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
	private static final String PASSWORD_REGX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,20}$";
	private static final String CONTACT_NO_REGX = "^\\d{0,10}$";
	
		
	// method to validate if the name is correct
	public boolean validateName(String string) {
		return Pattern.matches(NAME_REGX, string);
	}
	
	// method to validate if the email is correct or not
	public boolean validateEmail(String string) {
		return Pattern.matches(EMAIL_REGX, string);
	}
	
	// method to validate if the password is correct or not
	public boolean validatePassword(String string) {
		return Pattern.matches(PASSWORD_REGX, string);
	}
	
	// method to validate if the password is correct or not
	public boolean validateContact(String string) {
		return Pattern.matches(CONTACT_NO_REGX, string);
	}
	
}
