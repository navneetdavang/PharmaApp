package junittesting;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AdminLoginTest.class,
	CustomerLoginTest.class,
	CustomerRegisterTest.class,
	FieldValidationRegxTest.class,
	MedicineServicesTest.class
})
public class TestAll {

}
