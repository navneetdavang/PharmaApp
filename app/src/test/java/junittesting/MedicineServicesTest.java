package junittesting;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import system.beans.Chemist;
import system.beans.Medicine;
import system.services.LoginServices;
import system.services.MedicineServices;
import system.utils.ValidationUtils;

public class MedicineServicesTest {

	@Test
	public void testAddMedicine() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		String medicine_name = "Aciloc";
		int stock = 100;
		int price = 10;
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		Assert.assertNotEquals(null, chemist);
		
		Medicine medicine = new Medicine(medicine_name,stock,price);
		
		boolean actual = MedicineServices.addMedicine(medicine);
		Assert.assertEquals(true, actual);
		
		MedicineServices.deleteMedicine(medicine);
		
	}
	
//	@Test
//	public void testAddedInvalidMedicine() {
//		String username = "admin@chemist";
//		String password = "chemist@1234";
//		String medicine_name = "Aciloc";
//		String stock = "100";
//		String price = "-10";
//		
//		Chemist chemist = LoginServices.chemistLoginService(username, password);
//		Assert.assertNotEquals(null, chemist);
//		
//		//Medicine medicine = new Medicine(medicine_name,stock,price);
//		
//		
//		
//		boolean actual = MedicineServices.addMedicine(medicine);
//		Assert.assertEquals(true, actual);
//		
//		boolean test = new ValidationUtils().validateContact();
//	}
	
	@Test
	public void testRemoveMedicine() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		String medicine_name = "Metfill VG1";
		int stock = 100;
		int price = 10;
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		Assert.assertNotEquals(null, chemist);
		
		Medicine medicine = new Medicine(medicine_name,stock,price);

		boolean isAdded = MedicineServices.addMedicine(medicine);
		Assert.assertEquals(true, isAdded);
		
		boolean isRemoved = MedicineServices.deleteMedicine(medicine);
		Assert.assertEquals(true, isRemoved);
		
		
	}
	
	@Test 
	public void testRemoveInvalidMedicine() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		String medicine_name = "Augpen 625";
		int stock = 100;
		int price = 10;
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		Assert.assertNotEquals(null, chemist);
		
		Medicine medicine = new Medicine(medicine_name,stock,price);
		
		boolean actual = MedicineServices.deleteMedicine(medicine);
		Assert.assertEquals(false, actual);
		System.out.println(actual);
	}
	
	@Test
	public void testUpdateMedicine() {
		String username = "admin@chemist";
		String password = "chemist@1234";
		String medicine_name = "Calcifit 500";
		int stock = 100;
		int price = 10;
		
		Random rand = new Random();
		int new_stock = rand.nextInt(100-10+1)+10;
		int new_price = rand.nextInt(500-10+1)+10;
		
		Chemist chemist = LoginServices.chemistLoginService(username, password);
		Assert.assertNotEquals(null, chemist);
		
		Medicine medicine = new Medicine(medicine_name,stock,price);
		Medicine medicine2 = new Medicine(medicine_name,new_stock,new_price);

		boolean isAdded = MedicineServices.addMedicine(medicine);
		Assert.assertEquals(true, isAdded);
		
		boolean isUpdated = MedicineServices.updateMedicine(medicine2);
		System.out.println(isUpdated + "- isUpdated");
		Assert.assertEquals(true, isUpdated);
		
	}
}
