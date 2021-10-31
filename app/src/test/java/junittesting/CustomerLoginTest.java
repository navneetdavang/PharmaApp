package junittesting;

import org.junit.Assert;
import org.junit.Test;

import system.beans.Chemist;
import system.beans.Customer;
import system.services.LoginServices;

public class CustomerLoginTest {

	// test case if the customer is the valid user
		@Test
		public void customerLoginTest() {
			String username = "jayeshburande02@gmail.com";
			String password = "Jayesh@2oct";
			
			Customer customer = LoginServices.customerLoginService(username, password);
			
			Assert.assertNotEquals(null, customer);
			
		}
		
		
		// login test of the customer if user is invalid
		@Test 
		public void customerInvalidLoginTest() {
			String username = "nav@gmail.com";
			String password = "nav@1234";
			
			Customer customer = LoginServices.customerLoginService(username, password);
			
			Assert.assertEquals(null, customer);
		}
}
