package junittesting;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import system.utils.ValidationUtils;

public class FieldValidationRegxTest {
	
	ValidationUtils validation;
	
	@Before
	public void init() {
		validation = new ValidationUtils();
	}
	
	// if the name field contains only alphabets
	@Test
	public void checkNameFieldTest() {
		String[] names = new String[] {"Navneet", "Jayesh", "nav@46546af","dheeren", "mahesh", "86465489854", "ma13465"};
		boolean[] expected = new boolean[] {true, true, false, true, true, false, false};
		
		for(int i=0; i < names.length; i++) {
			
			boolean actual = validation.validateName(names[i]);

			Assert.assertEquals(expected[i], actual);
		}
	}
	
	// if the emails field contains valid 
	@Test
	public void checkEmailFieldTest() {
		String[] emails = new String[] {"Navneet", "Jayesh@gmail", "nav@gmail.com","dheeren", "mahesh@gmail.com", "864654@", "ma13465.com"};
		boolean[] expected = new boolean[] {false, false, true, false, true, false, false};
		
		for(int i=0; i < emails.length; i++) {
			
			boolean actual = validation.validateEmail(emails[i]);

			Assert.assertEquals(expected[i], actual);
		}
	}
	
	
//	 if the contact field contains valid 
	@Test
	public void checkContactFieldTest() {
		String[] contacts = new String[] {"9874561230", "9874561230@", "nav@gmail.com","9874561.15465", "mahesh@gmail.com", "8794561230", "ma13465.com"};
		boolean[] expected = new boolean[] {true, false, false, false, false, true, false};
		
		for(int i=0; i < contacts.length; i++) {
			
			boolean actual = validation.validateContact(contacts[i]);
//			System.out.println(contacts[i] + " " + actual + " " + expected[i]);

			Assert.assertEquals(expected[i], actual);
		}
	}
	
	
//	 if the contact field contains valid 
	@Test
	public void checkPasswordFieldStrengthTest() {
		String[] passwords = new String[] {"9874561230", "9874561230@", "nav@1234","9874561.15465", "Mahesh@@132#com", "jay@1", "ma13465#com"};
		boolean[] expected = new boolean[] {false, false, true, false, true, false, true};
		
		for(int i=0; i < passwords.length; i++) {
			
			boolean actual = validation.validatePassword(passwords[i]);
//			System.out.println(passwords[i] + " " + actual + " " + expected[i]);

//			Assert.assertEquals(expected[i], actual);
		}
	}

	
	
}
