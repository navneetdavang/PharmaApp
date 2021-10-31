package junittesting;

import org.junit.Assert;
import org.junit.Test;

import system.beans.Chemist;
import system.services.LoginServices;

public class AdminLoginTest {
  
	@Test 
	public void chemistLoginTest() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		
		Assert.assertNotEquals(null, chemist);
		
	}
}
