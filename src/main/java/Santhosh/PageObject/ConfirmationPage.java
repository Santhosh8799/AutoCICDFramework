package Santhosh.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santhosh.AbstractComponent.AbstractComponent;

//Page object is just to store locators and methods data needs to driven 
public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver); 
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationText;
	
	public String verifyOrderConfirmation() {
		    String confirmation=confirmationText.getText();
		    return confirmation;
	}

	public void closeBrowser() {
		driver.close();
	}
}
