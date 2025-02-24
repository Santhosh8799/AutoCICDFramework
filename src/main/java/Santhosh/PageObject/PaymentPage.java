package Santhosh.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santhosh.AbstractComponent.AbstractComponent;

//Page object is just to store locators and methods data needs to driven 
public class PaymentPage extends AbstractComponent {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver); 
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement clickcountry;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement placeorder;
	
	By visible=By.cssSelector(".ta-results");
	
	public void selectCountry() {
		mouseOver(country, "india");
		waitForElementToAppear(visible);
		clickcountry.click();

	}
	
	public void clickOnPlaceOrder() {
		placeorder.click();
	}
}
