package junittesting;


import org.junit.Assert;
import org.junit.Test;

import system.beans.Customer;
import system.services.RegistrationServices;


public class CustomerRegisterTest {
	
	
	// Registering Customer with no credentials  
	@Test
	public void registerWithNoCredentials()
	{
		
		String fName = "";
		String lName = "";
		String phone = "";
		String email = "";
		String pass = "";
		//String cPass = "";
		
		
		Customer cust = RegistrationServices.customerRegService(fName , lName , phone , email , pass);
		Assert.assertEquals(null, cust);
	}
	
	
	// Re-registering using same email-id
	
	@Test 
	public void registerWithSameEmail()
	{
		String fName = "Navneet";
		String lName = "Davang";
		String phone = "9370422898";
		String email = "snkar1969@gmail.com";
		String pass = "Nav@1234"; 
		
		Customer cust = RegistrationServices.customerRegService(fName , lName , phone , email , pass);
		Assert.assertEquals(null, cust);
		
	}
	

}
