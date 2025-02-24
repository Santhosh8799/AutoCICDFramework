package Cucumber.StepDefinition;

import java.io.IOException;

import org.testng.Assert;

import Santhosh.PageObject.CheckoutPage;
import Santhosh.PageObject.ConfirmationPage;
import Santhosh.PageObject.LoginPage;
import Santhosh.PageObject.PaymentPage;
import Santhosh.PageObject.ProductCatalogue;
import Santhosh.TestComponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {
	LoginPage login;
	
	 @Given("I landed on Ecommerce Website")
		 public void I_landed_on_Ecommerce_Website() throws IOException{
		 launch();
		 }
	 
	 
	 @Given("^Logged in with username (.+) and password (.+)$")
	 public void LogIn_with_username_and_password(String username,String password) {
		 login=new LoginPage(driver);
		 login.LoginApplication(username,password);
	 }
	 
	 @When("^I added the product (.+) to cart and click on Checkout$")
	 public void I_added_the_product_to_cart_and_click_on_Checkout(String pro) {
		 ProductCatalogue product=new ProductCatalogue(driver);
		 product.clickontProduct(pro);
		 product.viewCart();
		 CheckoutPage checkout=new CheckoutPage(driver);
		 System.out.println(checkout.productName());
		 checkout.clickonCheckout();
	 }
	 
	 @When("Click on Place Order")
	 public void Click_on_Place_Order() {
		PaymentPage payment=new PaymentPage(driver);
		payment.selectCountry();
		payment.clickOnPlaceOrder();
	 }
	 
	 @Then ("Verify {string} message is displayed")
	 public void verify_message_is_displayed(String message) {
		ConfirmationPage confirm=new ConfirmationPage(driver);
		String messageretreive=confirm.verifyOrderConfirmation();
		Assert.assertEquals(messageretreive, message);
		driver.close();
	 }
	 
	  @Then("{string} message is displayed")
	  public void verify_error_message_displayed(String error) {
		  Assert.assertEquals(login.ErrorValidation(),error);
		  driver.close();
	  }
}

