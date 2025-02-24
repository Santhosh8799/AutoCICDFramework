package Santhosh.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Santhosh.PageObject.CheckoutPage;
import Santhosh.PageObject.ConfirmationPage;
import Santhosh.PageObject.LoginPage;
import Santhosh.PageObject.OrderPage;
import Santhosh.PageObject.PaymentPage;
import Santhosh.PageObject.ProductCatalogue;
import Santhosh.TestComponent.BaseTest;

public class FrameWorkBuildTest extends BaseTest {
	
//Just added the comment to check whether CICD integration works 
	String product="ADIDAS ORIGINAL";
	//Tmmrw Try amazon Application 
	@Test(dataProvider="hasdata",groups={"Purchasedataprovider"})
	public void submitOrder(HashMap<String,String> input) {
	
    // Set the path to the ChromeDriver executable
    LoginPage login=new LoginPage(driver);
    login.LoginApplication(input.get("Email"), input.get("password"));
	ProductCatalogue product=new ProductCatalogue(driver);
    product.clickontProduct(input.get("product"));
    product.viewCart();
	CheckoutPage checkout=new CheckoutPage(driver);
	System.out.println(checkout.productName());
	checkout.clickonCheckout();
	PaymentPage payment=new PaymentPage(driver);
	payment.selectCountry();
	payment.clickOnPlaceOrder();
	ConfirmationPage confirm=new ConfirmationPage(driver);
	String messageretreive=confirm.verifyOrderConfirmation();
    Assert.assertEquals(messageretreive, "THANKYOU FOR THE ORDER.");
}
	//After order is placed then only it will be visible on order Details Page So we create an annotation with dependson
	//dependsonmethod after submit order is executed VerifyorderDetails will execute 
	@Test(dependsOnMethods= {"submitOrder"})
	public void VerifyOrderDetails() {
		LoginPage login=new LoginPage(driver);
		login.LoginApplication("santhosh42@gmail.com", "Sanrahul324@#");
		OrderPage order=new OrderPage(driver);
		order.goTOOrders();
		order.getOrderDetails(product);
		Assert.assertTrue(order.getOrderDetails(product));		
	}
	
	//Simple Data Provider Driving the data 
	@DataProvider
	public Object[][] readData() 
	{
		return new Object[][] {{"santhosh42@gmail.com","Sanrahul324@#","ADIDAS ORIGINAL"},{"santhosh62@gmail.com","Sanrahul324@#","IPHONE 13 PRO"}};
	}
	
	
	//Driving the data through External File and the method as been written in BaseTest class as Reusable
	@DataProvider
	public Object[][] hasdata() throws IOException {
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Santhosh//Testdata//purchaseData.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
