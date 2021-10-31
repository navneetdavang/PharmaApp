package junittesting;

import org.junit.Assert;
import org.junit.Test;

import system.beans.Chemist;
import system.services.LoginServices;

public class AdminLoginTest {
  
	// test case if the admin is the valid user
	@Test
	public void chemistLoginTest() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		
		Assert.assertNotEquals(null, chemist);
		
	}
	
	
	// login test of the admin if user is invalid
	@Test 
	public void chemistInvalidLoginTest() {
		String username = "nav@chemist";
		String password = "nav@1234";
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		
		Assert.assertEquals(null, chemist);
	}
	
	// logging in with no credentials 
	@Test
	public void chemistLoginNoCredentials() 
	{
		
		String username = "";
		String password = "";
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		
		Assert.assertEquals(null,chemist);
	}
	
	
}
