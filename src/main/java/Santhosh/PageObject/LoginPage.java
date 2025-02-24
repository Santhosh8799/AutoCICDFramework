package Santhosh.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Santhosh.AbstractComponent.AbstractComponent;

//Page object is just to store locators and methods data needs to driven 
public class LoginPage extends AbstractComponent {

	WebDriver driver;
	
	//initially driver value will be null so we use constructor to get the driver from parent
	//constructor will execute first when object is created
	public LoginPage(WebDriver driver) {
		super(driver);
		//we are carefully assigned driver value to current class driver using this keyword constructor driver is local variable  
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//initelements helps to construct driver.findElement(By.id("")) structure using this keyword we refer to current driver 
		
	}
	
	@FindBy(xpath="//input[@type='email']")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement userpassword;
	
	@FindBy(name="login")
	WebElement login;
	
	@FindBy(css="div[role='alert']")
	WebElement error;
	
	public void LoginApplication(String username1,String password) {
		username.sendKeys(username1);
		userpassword.sendKeys(password);
		login.click();
	}
	
	public void launchsite() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String ErrorValidation() {
		waitForWebElementToAppear(error);
		String errorText=error.getText();
		return errorText;
		
		
	}
}
