package Santhosh.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santhosh.AbstractComponent.AbstractComponent;

//Page object is just to store locators and methods data needs to driven 
public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver); 
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	@FindBy(css=".cartSection h3")
	WebElement productText;
	
	public void clickonCheckout() {
		checkout.click();
	}
	
	public String productName() {
		String product=productText.getText();
		return product;
	}
}
