package Santhosh.Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Santhosh.PageObject.LoginPage;
import Santhosh.TestComponent.BaseTest;
import Santhosh.TestComponent.Retry;


public class ErrorValidation extends BaseTest {
	
	
	//We can Create multiple testcases in each class .@Test represents one test
	//Few people create one test  in each class but it's not good practice when 150 testcases to be writen then 150 classes will created 
	//Instead we can we can divide and write in one class like mutliple testcase Example Login error validation(EX:Testcase 1-Invalid username,TC-2Invalid Password etc)  can written on Login Test class
	//Similarly Adding product and doing some action can be written in Adding product cart Test class and so
	//We can Divide 25 cases in each test class and write So 150 can written in 6 Test Class.Just an Example Provided  
	@Test(groups= {"Smoke"},retryAnalyzer=Retry.class)
	//When we doubt any testcase might fail and it needs a retry then provide RetryAnalyzer with class name  on @Test
	public void verifyLoginError() throws IOException {
		   LoginPage login=new LoginPage(driver);
		   login.LoginApplication("santhosh@gmail.com","Sanrahul324@#");
		   Assert.assertEquals(login.ErrorValidation(),"Incorrect email or password.");
	}

}
